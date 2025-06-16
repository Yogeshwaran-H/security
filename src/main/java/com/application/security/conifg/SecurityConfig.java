package com.application.security.conifg;

import com.application.security.controller.Controller;
import com.application.security.service.CustomUserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;

    SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService= customUserDetailsService;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    	http.csrf(customizer->customizer.disable());
    	http.authorizeHttpRequests(request->request.anyRequest().authenticated());
    	http.httpBasic(Customizer.withDefaults());
    	http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//    	http.httpBasic();
    	return http.build();
	}
    
    @Bean
    AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider dao=new DaoAuthenticationProvider();
    	dao.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
    	dao.setUserDetailsService(customUserDetailsService);
		return null;
    }
}
