package com.onewordapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig {

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
                http
                                // CSRF is enabled by default in Spring Security 6
                                .csrf(csrf -> {
                                }) // ✅ Enable CSRF for production
                                .authorizeHttpRequests(auth -> auth
                                                .requestMatchers("/", "/register", "/login", "/css/**", "/js/**")
                                                .permitAll() // ✅ Public pages
                                                .requestMatchers("/post-word").authenticated() // ✅ Posting requires
                                                                                               // login
                                                .anyRequest().authenticated())
                                .formLogin(form -> form
                                                .loginPage("/login")
                                                .defaultSuccessUrl("/post-word", true) // ✅ Redirect after login
                                                .permitAll())
                                .logout(logout -> logout
                                                .logoutSuccessUrl("/") // ✅ Redirect to home after logout
                                                .permitAll());
                return http.build();
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return new BCryptPasswordEncoder();
        }
}