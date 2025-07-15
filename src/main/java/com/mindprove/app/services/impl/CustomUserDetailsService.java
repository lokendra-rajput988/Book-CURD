package com.mindprove.app.services.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mindprove.app.entities.UserDb;
import com.mindprove.app.exceptions.ResourceNotFoundException;
import com.mindprove.app.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//load user by username
		UserDb user = userRepository.findByEmail(username).orElseThrow(()-> new ResourceNotFoundException("User Not Found !!"));
		return user;
	}

}
