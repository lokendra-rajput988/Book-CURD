package com.mindprove.app.mapper;

import org.springframework.stereotype.Component;

import com.mindprove.app.dtos.UserDto;
import com.mindprove.app.entities.UserDb;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserMapper {

	public UserDb toEntity(UserDto userDto) {
		log.info("DTO to Entity");
		UserDb userDb=new UserDb();
		userDb.setFullName(userDto.getFullName());
		userDb.setEmail(userDto.getEmail());
		userDb.setPassword(userDto.getPassword());
		userDb.setConfirmPassword(userDto.getConfirmPassword());
		userDb.setRole(userDto.getRole());
		userDb.setCreatedAt(userDto.getCreatedAt());
		userDb.setUpdatedAt(userDto.getUpdatedAt());
		return userDb;
	}
	
	public UserDto toDTO(UserDb userDb) {
		log.info("Entity to DTO");
		UserDto userDto=new UserDto();
		userDto.setUserId(userDb.getUserId());
		userDto.setFullName(userDb.getFullName());
		userDto.setPassword(userDb.getPassword());
		userDto.setConfirmPassword(userDb.getConfirmPassword());
		userDto.setEmail(userDb.getEmail());
		userDto.setRole(userDb.getRole());
		userDto.setCreatedAt(userDb.getCreatedAt());
		userDto.setUpdatedAt(userDb.getUpdatedAt());
		return userDto;
	}
}
