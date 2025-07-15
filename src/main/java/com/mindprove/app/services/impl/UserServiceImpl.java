package com.mindprove.app.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mindprove.app.dtos.UserDto;
import com.mindprove.app.entities.UserDb;
import com.mindprove.app.exceptions.ResourceNotFoundException;
import com.mindprove.app.mapper.UserMapper;
import com.mindprove.app.repositories.UserRepository;
import com.mindprove.app.services.IUserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PasswordEncoder passwordEncode;

	@Override
	public UserDto createUser(UserDto userDto) {
		log.info("create user method called ");
		UserDb userDb=userMapper.toEntity(userDto);
		Optional<UserDb> findByEmail = userRepository.findByEmail(userDb.getEmail());
		if(findByEmail.isEmpty()) {
			if(userDb.getPassword().equals(userDb.getConfirmPassword())) {
				userDb.setPassword(passwordEncode.encode(userDb.getPassword()));
				userDb.setConfirmPassword(passwordEncode.encode(userDb.getConfirmPassword()));
				log.info("create user method completed");
				return userMapper.toDTO(userRepository.save(userDb));
			}else
				throw new RuntimeException("password mis-match exception");
		}
		throw new RuntimeException("Email is already exist");

	}

	@Override
	public UserDto updateUser(Long userId, UserDto userDto) {
		log.info("update user method called");
		UserDb userDb = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User id "+userId+" is not found."));
		Optional<UserDb> findByEmail= userRepository.findByEmail(userDto.getEmail());
		if(findByEmail.isEmpty()) {
			
			if(userDto.getPassword().equals(userDto.getConfirmPassword())) {
				userDb.setPassword(passwordEncode.encode(userDto.getPassword()));
				userDb.setConfirmPassword(passwordEncode.encode(userDto.getConfirmPassword()));
				userDb.setEmail(userDto.getEmail());
				userDb.setFullName(userDto.getFullName());
				userDb.setRole(userDto.getRole());
				log.info("update user method completed");
				return userMapper.toDTO(userRepository.save(userDb));
			}else
				throw new RuntimeException("password mis-match exception");
			
		}else {
			throw new RuntimeException("Email is already exist.");
		}
	}

	@Override
	public UserDto getUserById(Long userId) {
		log.info("get user by id method called");
		UserDb userDb = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User id "+userId+" is not found."));
		log.info("get user by id method completed");
		return userMapper.toDTO(userDb);
	}

	@Override
	public boolean deleteUserById(Long userId) {
	    log.info("delete user by id method called");
		UserDb userDb = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User id "+userId+" is not found."));
		log.info("delete user by id completed");
		userRepository.delete(userDb);
		return true;
	}
	
	

}
