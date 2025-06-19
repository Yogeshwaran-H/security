package com.application.security.conifg;

import com.application.security.controller.Controller;
import com.application.security.service.CustomUserDetailsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    private final CustomUserDetailsService customUserDetailsService;

    SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService= customUserDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	logger.info("inside security filter chain");
    	http.csrf(customizer->customizer.disable());
    	http.authorizeHttpRequests(request->request.anyRequest().authenticated());
    	http.httpBasic(Customizer.withDefaults());
    	http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//    	http.httpBasic();
    	return http.build();
	}
    
    @Bean
    AuthenticationProvider authenticationProvider() {
    	logger.info("inside security authentication provider");
    	DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
    	dao.setPasswordEncoder(passwordEncoder());
    	dao.setUserDetailsService(customUserDetailsService);
		return dao;
    }
    
    @Bean
    PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}
