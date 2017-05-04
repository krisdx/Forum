package com.forum.config;

import com.forum.areas.user.service.LoginRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String USER_ROLE = "USER";

    private final String[] USERS_URLS = {"/questions/add", "users/edit/bio/**"};

    private final String[] PUBLIC_URLS = {"/", "/register", "/login",
            "/questions/**", "/tags/**", "/categories/**",
            "/users/**",
            "/bootstrap/**", "/jquery/**", "/css/**", "/js/**"};

    @Autowired
    private LoginRegisterService loginRegisterService;

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.loginRegisterService).passwordEncoder(this.getBCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security
                .authorizeRequests()
                    .antMatchers(USERS_URLS).hasRole(USER_ROLE)
                    .antMatchers(PUBLIC_URLS).permitAll()
                    .anyRequest().authenticated()
                .and()
                    .formLogin().loginPage("/login").permitAll()
                    .usernameParameter("username")
                    .passwordParameter("password")
                .and()
                    .rememberMe()
                    .rememberMeCookieName("rememberMe")
                    .rememberMeParameter("remember-me")
                    .key("rememberMe")
                    .alwaysRemember(true)
                .and()
                    .logout().logoutSuccessUrl("/").permitAll()
                .and()
                    .exceptionHandling().accessDeniedPage("/unauthorized")
                .and()
                    .csrf().disable();
    }
}