package com.mindprove.app.services;

import com.mindprove.app.dtos.UserDto;
import com.mindprove.app.response.ResponseDto;

public interface IUserService {

	UserDto createUser(UserDto userDto);
	
	UserDto updateUser(Long userId,UserDto userDto);
	
	UserDto getUserById(Long userId);
	
	boolean deleteUserById(Long userId);
}
