package com.mindprove.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindprove.app.dtos.UserDto;
import com.mindprove.app.paylod.JwtRequest;
import com.mindprove.app.paylod.JwtResponse;
import com.mindprove.app.response.ResponseDto;
import com.mindprove.app.security.JwtHelper;
import com.mindprove.app.services.IUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {
	
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private IUserService userService;
	@Autowired
	private JwtHelper helper;
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
		log.info("Post API called : {}");
		this.doAuthenticate(request.getEmail(), request.getPassword());
		UserDetails userDetails=userDetailsService.loadUserByUsername(request.getEmail());
		String token=this.helper.generateToken(userDetails);
		return ResponseEntity.ok().body(new JwtResponse(token, userDetails.getUsername()));

	}
	
	
	@PostMapping("/signup")
	public ResponseEntity<ResponseDto> createUser(@RequestBody UserDto userDto){
		log.info("Post API called : {}");
		return ResponseEntity.ok().body(new ResponseDto("Success",userService.createUser(userDto),HttpStatus.CREATED));
	}
	
	
	
	public void doAuthenticate(String email,String password) {
		UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(email, password);
		
		try {
			manager.authenticate(authentication);
		}catch(BadCredentialsException e) {
			throw new RuntimeException("Invalid Username and Password");
		}
	}
	
}
