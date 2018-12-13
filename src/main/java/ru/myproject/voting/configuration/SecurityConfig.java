package ru.myproject.voting.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import ru.myproject.voting.service.CustomUserDetailsService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private BCryptPasswordEncoder passwordEncoder;

    private CustomUserDetailsService userDetailsService;

    @Autowired
    public SecurityConfig(BCryptPasswordEncoder passwordEncoder, CustomUserDetailsService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("voting/rest").permitAll()
                .antMatchers("voting/rest/registration").permitAll()
                .antMatchers("voting/rest/users").hasAuthority("USER")
                .antMatchers("voting/rest/admin").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
//                .loginPage("/voting/rest/login").failureUrl("/voting/rest/login?error=true")
                .defaultSuccessUrl("/", false)
                .usernameParameter("email")
                .passwordParameter("password");
//                .and().logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/voting/rest/logout"))
//                .logoutSuccessUrl("/voting/rest/").and().exceptionHandling()
//                .accessDeniedPage("/voting/rest/access-denied");
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//    }
}
