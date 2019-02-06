package ru.myproject.voting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.myproject.voting.model.Restaurant;
import ru.myproject.voting.service.RestaurantServiceImpl;
import ru.myproject.voting.service.UserServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.myproject.voting.TestDataRestaurant.REST_URL;
import static ru.myproject.voting.TestDataRestaurant.createNew;

@RunWith(SpringRunner.class)
@WebMvcTest(RestaurantController.class)
public class RestaurantControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @MockBean
    public RestaurantServiceImpl service;

    @MockBean
    public UserServiceImpl userService;

    @Test
    @WithMockUser(value = "admin", roles = "ADMIN")
    public void testCreate() throws Exception {
        Restaurant create = createNew;
        mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(create)))
                .andExpect(status().isCreated())
                .andDo(print());
    }

    @Test
    @WithMockUser(value = "admin", roles = "ADMIN")
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + "/100005")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

    }
}