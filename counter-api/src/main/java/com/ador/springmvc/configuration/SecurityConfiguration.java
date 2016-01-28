package com.ador.springmvc.configuration;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
 
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
 
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      auth.inMemoryAuthentication().withUser("admin").password("123456").roles("ADMIN");
      auth.inMemoryAuthentication().withUser("optus").password("candidates").roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

      http
        .csrf().disable()
        .authorizeRequests()
        .antMatchers("/top/**").access("hasRole('ROLE_ADMIN')")
        .antMatchers("/search/**").access("hasRole('ROLE_ADMIN')")
        .and().httpBasic();
    }
}