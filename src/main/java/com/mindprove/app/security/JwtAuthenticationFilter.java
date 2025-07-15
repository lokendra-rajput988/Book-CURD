package com.mindprove.app.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private  JwtHelper jwtHelper;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		

		log.info("JwtAuthenticationFilter");
        final String requestHeader = request.getHeader("Authorization");

		log.info("Header : {}",requestHeader);
		
		String username=null;
		String token=null;
        
		if(requestHeader!=null && requestHeader.startsWith("Bearer")) {
			System.out.println("inside the doFilterInternal");
			token=requestHeader.substring(7);
			try {
				username=this.jwtHelper.extractUsername(token);
			}catch(IllegalCallerException e) {
				log.info("Illegal Argument while fetching the username !!");
				e.printStackTrace();
			}catch(ExpiredJwtException e) {
				log.info("Given jwt token is expired !!");
				e.printStackTrace();
			}catch(MalformedJwtException e) {
				log.info("Some changed has done in token !! Invalid Token");
				e.printStackTrace();
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null ) {
				//fetch user detail from username
				UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			    boolean tokenValid = this.jwtHelper.isTokenValid(token, userDetails);
			    if(tokenValid) {
			    	//set the authentication
			    	UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			    	authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			    	
			    	SecurityContextHolder.getContext().setAuthentication(authentication);
			    }else {
					log.info("Validation fails !!");
				}
			}
			
			
		}
		filterChain.doFilter(request, response);
       
		
	}

}
