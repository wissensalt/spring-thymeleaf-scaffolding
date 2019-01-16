package com.wissensalt.rnd.sts.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import java.util.Arrays;

/**
 * Created on 1/3/19.
 *
 * @author <a href="mailto:fauzi.knightmaster.achmad@gmail.com">Achmad Fauzi</a>
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityBasicAuthenticationProvider basicAuthenticationProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login/**", "/").permitAll()
                .antMatchers("/admin*").hasRole("ADMIN")
                .and()
                    .formLogin()
                    .loginPage("/")
                    .defaultSuccessUrl("/admin")
                .and()
                    .logout()
                    .logoutUrl("/logout/perform")
                    .logoutSuccessUrl("/")
                .and().csrf().disable();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return new ProviderManager(Arrays.asList(basicAuthenticationProvider));
    }
}
