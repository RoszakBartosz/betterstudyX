package com.example.betterStudy.model.security;

import com.example.betterStudy.model.enums.UserRole;
import com.example.betterStudy.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.io.ObjectInputFilter;

// TODO usuwamy nieuzywane importy

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration  {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(authorize -> authorize
                    .requestMatchers("/student/find-by-id/**").permitAll()
                    .requestMatchers(request -> request.getMethod().equals("POST")).hasRole("ADMIN")
                    .requestMatchers(request -> request.getServletPath().startsWith("/classroom")).hasAnyRole("STUDENT", "ADMIN", "TEACHER")
                    .requestMatchers("/student/**").hasAnyRole("ADMIN", "TEACHER")
                    .requestMatchers("/teacher/**").hasRole("ADMIN")
                    .requestMatchers("/lesson/find-by-id/**").hasRole("STUDENT")
                    .requestMatchers("/lesson/**").hasAnyRole("TEACHER", "ADMIN")
                    .requestMatchers("/users/**").hasRole("ADMIN")
                    .anyRequest().authenticated()
            ).authenticationProvider(authenticationProvider())
             .httpBasic(Customizer.withDefaults());

        return http.build();
    }

//    .authorizeHttpRequests(authorize -> authorize
//            .requestMatchers(req -> req.getServletPath().startsWith("/user")).permitAll()
//                        .requestMatchers("/swagger-ui/**").permitAll()
//                        .requestMatchers("/v3/api-docs/**".hasRole()).permitAll()
//                        .requestMatchers(req -> req.getMethod().equals("GET")).hasRole(UseRole.USER.name())
//            .requestMatchers(req -> req.getMethod().equals("POST")
//                                || req.getMethod().equals("PUT")
//                                || req.getMethod().equals("PATCH")).hasAuthority(UserPermission.WRITE.name())
//            .requestMatchers(req -> req.getMethod().equals("DELETE")).hasAuthority(UserPermission.DELETE.name())
//            .requestMatchers(req -> req.getMethod().equals("GET")).authenticated()
//                        .anyRequest().authenticated()
//                )


    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return authenticationProvider;
    }
}
