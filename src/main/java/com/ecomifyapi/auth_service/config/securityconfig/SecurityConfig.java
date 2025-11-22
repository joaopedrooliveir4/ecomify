package com.ecomifyapi.auth_service.config.securityconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
        @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(
                        authorizeConfig -> {
                            authorizeConfig.requestMatchers("/ecomifyapi/**").permitAll();
                            authorizeConfig.requestMatchers("/h2-console/**").permitAll();
                            authorizeConfig.anyRequest().authenticated();
                        }
                )

                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/h2-console/**")
                        .ignoringRequestMatchers("/ecomifyapi/**")
                )
                .headers(headers -> headers
                        .frameOptions(frameOptions -> frameOptions
                                .sameOrigin()
                        )
                )
                .build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
    }
}