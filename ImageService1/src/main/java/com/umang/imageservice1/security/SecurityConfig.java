package com.umang.imageservice1.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .requestMatchers("/images/**").permitAll() // Allow access to image endpoints without authentication
                                .anyRequest().authenticated() // Secure other requests
                )
                .httpBasic(Customizer.withDefaults()) // Use HTTP Basic Authentication with customizer
                .csrf(csrf -> csrf.disable()); // Disable CSRF for non-browser clients (if needed)

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}password") // Use {noop} for plain text password
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
