package com.learnproject.spring_web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.learnproject.spring_web.auth.service.UserService;
import com.learnproject.spring_web.config.jwt.JwtAuthenticationEntryPoint;
import com.learnproject.spring_web.config.jwt.JwtRequestFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserService userService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtRequestFilter jwtRequestFilter;

    public SecurityConfig(UserService userService, JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint, JwtRequestFilter jwtRequestFilter) {
        this.userService = userService;
        this.jwtAuthenticationEntryPoint = jwtAuthenticationEntryPoint;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        configureHttpSecurity(httpSecurity);
        configureJwtFilter(httpSecurity);
        return httpSecurity.build();
    }

    private void configureHttpSecurity(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/signin").permitAll()
                .requestMatchers("/produto/**").hasAnyRole("ADMIN")
                .requestMatchers("/pedido/**").authenticated()
                .anyRequest().authenticated())
            .exceptionHandling(Customizer -> Customizer
                .authenticationEntryPoint(jwtAuthenticationEntryPoint))
            .sessionManagement(Customizer -> Customizer
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
    }

    private void configureJwtFilter(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public UserDetailsService userDetailsService() {

        return userService;
    }*/

}
