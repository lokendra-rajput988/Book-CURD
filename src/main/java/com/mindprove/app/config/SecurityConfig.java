package com.mindprove.app.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.mindprove.app.enums.Role;
import com.mindprove.app.security.JwtAuthenticationEntryPoint;
import com.mindprove.app.security.JwtAuthenticationFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@Slf4j
public class SecurityConfig {

	@Autowired
	private JwtAuthenticationEntryPoint point;
	
	@Autowired
	private JwtAuthenticationFilter filter;
	@Autowired
	private UserDetailsService usrDetailsService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		log.info("Your are enterd in security filter chain");
		http.csrf(csrf-> csrf.disable())
		.authorizeHttpRequests(auth->
		auth.requestMatchers("/api/book/createBook","/api/book/updateBookById/**").hasAnyAuthority(Role.ADMIN.name())
		.requestMatchers("/api/auth/**","/api/book/getAllBooks/**","/api/book/getBookById/**","/api/book/search/**").permitAll().anyRequest().authenticated())
		.exceptionHandling(ex->ex.authenticationEntryPoint(point))
		.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		;
		
		http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
		return http.build();
		
		
	}
	
	  @Bean
	    AuthenticationProvider authenticationProvider() {
	        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

	        authProvider.setUserDetailsService(usrDetailsService);
	        authProvider.setPasswordEncoder(passwordEncoder);

	        return authProvider;
	    }
	

	
	
	
	   
}
