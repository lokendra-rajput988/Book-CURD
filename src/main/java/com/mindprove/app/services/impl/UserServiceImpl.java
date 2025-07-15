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

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PasswordEncoder passwordEncode;

	@Override
	public UserDto createUser(UserDto userDto) {
		UserDb userDb=userMapper.toEntity(userDto);
		Optional<UserDb> findByEmail = userRepository.findByEmail(userDb.getEmail());
		if(findByEmail.isEmpty()) {
			if(userDb.getPassword().equals(userDb.getConfirmPassword())) {
				userDb.setPassword(passwordEncode.encode(userDb.getPassword()));
				userDb.setConfirmPassword(passwordEncode.encode(userDb.getConfirmPassword()));
				return userMapper.toDTO(userRepository.save(userDb));
			}else
				throw new RuntimeException("password mis-match exception");
		}
		throw new RuntimeException("Email is already exist");

	}

	@Override
	public UserDto updateUser(Long userId, UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto getUserById(Long userId) {
		UserDb userDb = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User id "+userId+" is not found."));
		return userMapper.toDTO(userDb);
	}

	@Override
	public boolean deleteUserById(Long userId) {
		UserDb userDb = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User id "+userId+" is not found."));
		userRepository.delete(userDb);
		return true;
	}
	
	

}
