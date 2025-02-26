package com.example.betterStudy.model.security;

import com.example.betterStudy.model.enums.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.io.ObjectInputFilter;

// TODO usuwamy nieuzywane importy

@EnableWebSecurity
@Configuration
public class SecurityConfiguration  {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers("/student/save")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .authenticationProvider(authenticationProvider());

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
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService();
        //wstrzyknać tu userservice obok setuserdetailservice
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
}
