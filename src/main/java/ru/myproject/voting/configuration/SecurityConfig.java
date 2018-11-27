package ru.myproject.voting.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private BCryptPasswordEncoder passwordEncoder;

    private DataSource dataSource;

    @Autowired
    public SecurityConfig(BCryptPasswordEncoder passwordEncoder, DataSource dataSource) {
        this.passwordEncoder = passwordEncoder;
        this.dataSource = dataSource;
    }

    private String usersQuery = "select email, password, enabled from users where email=?";

    private String rolesQuery = "select u.email, r.role from user u inner join user_role ur on(u.user_id=ur.user_id) " +
            "inner join role r on(ur.role_id=r.role_id) where u.email=?";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery(usersQuery)
                .authoritiesByUsernameQuery(rolesQuery)
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/voting/rest/users").permitAll()
                .antMatchers("/voting/rest/login").permitAll()
                .antMatchers("/voting/rest/registration").permitAll()
                .antMatchers("/voting/rest/admin/**").hasAuthority("ADMIN").anyRequest()
                .authenticated().and().csrf().disable().formLogin()
                .loginPage("/voting/rest/login").failureUrl("/voting/rest/login?error=true")
                .defaultSuccessUrl("/voting/rest/admin/home")
                .usernameParameter("email")
                .passwordParameter("password")
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/voting/rest/logout"))
                .logoutSuccessUrl("/voting/rest/").and().exceptionHandling()
                .accessDeniedPage("/voting/rest/access-denied");
    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//    }
}
