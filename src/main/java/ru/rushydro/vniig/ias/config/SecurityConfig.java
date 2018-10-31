package ru.rushydro.vniig.ias.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by yazik on 04.03.2017.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/ws/*").permitAll()
                .antMatchers("/rest/*").permitAll()
                .antMatchers("/").access("hasRole('ROLE_USER')")
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/", false)
                .and().csrf().disable();

    }

}
