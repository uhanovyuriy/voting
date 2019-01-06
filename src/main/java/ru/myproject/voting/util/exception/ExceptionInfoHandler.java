package ru.myproject.voting.util.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.myproject.voting.util.ValidationUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

import static ru.myproject.voting.util.exception.ErrorType.*;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE + 5)
public class ExceptionInfoHandler {

    private final static Logger log = LoggerFactory.getLogger(ExceptionInfoHandler.class);

    private static final String EXCEPTION_DUPLICATE_EMAIL = "exception.user.duplicateEmail";

    private static final String EXCEPTION_DUPLICATE_NAME_RESTAURANT = "exception.restaurant.duplicateName";

    private static final Map<String, String> CONSTRAINS_MAP = Collections.unmodifiableMap(
            new HashMap<String, String>() {
                {
                    put("users_unique_email_idx", EXCEPTION_DUPLICATE_EMAIL);
                    put("restaurants_unique_name_idx", EXCEPTION_DUPLICATE_NAME_RESTAURANT);
                }
            });

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ErrorInfo conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        String rootMsg = ValidationUtil.getRootCause(e).getMessage();
        if (rootMsg != null) {
            String lowerCaseMsg = rootMsg.toLowerCase();
            Optional<Map.Entry<String, String>> entry = CONSTRAINS_MAP.entrySet().stream()
                    .filter(it -> lowerCaseMsg.contains(it.getKey()))
                    .findAny();
            if (entry.isPresent()) {
                return logAndGetErrorInfo(req, e, false, VALIDATION_ERROR, entry.get().getValue());
            }
        }
        return logAndGetErrorInfo(req, e, true, DATA_ERROR);
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorInfo notValidArgument(HttpServletRequest req, Exception e) {
        BindingResult result = ((MethodArgumentNotValidException) e).getBindingResult();
        String[] details = result.getFieldErrors().stream()
                .map(FieldError::getField)
                .toArray(String[]::new);

        return logAndGetErrorInfo(req, e, false, VALIDATION_ERROR, details);
    }

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)
    @ExceptionHandler(IncorrectTimeVoting.class)
    public ErrorInfo endTimeVoting(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true, INCORRECT_TIME_VOTING);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(NotFoundException.class)
    public ErrorInfo notFound(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true, DATA_NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorInfo handleError(HttpServletRequest req, Exception e) {
        return logAndGetErrorInfo(req, e, true, APP_ERROR);
    }

    private ErrorInfo logAndGetErrorInfo(HttpServletRequest req, Exception e, boolean logException, ErrorType errorType, String... details) {
        Throwable rootCause = ValidationUtil.logAndGetRootCause(log, req, e, logException, errorType);
        return new ErrorInfo(req.getRequestURL(), errorType,
                errorType.getErrorCode(),
                details.length != 0 ? details : new String[]{ValidationUtil.getMessage(rootCause)});
    }
}
