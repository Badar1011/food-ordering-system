package com.uppercrust.foodorderingsystem.Authentication;

import com.uppercrust.foodorderingsystem.Authentication.User.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Bean()
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/category").hasRole("ADMIN")
                .mvcMatchers(HttpMethod.POST, "/category").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .and()
                .logout();


    }
//TODO password encode bean
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // could remove the password encoder bit to see if default is applied
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
