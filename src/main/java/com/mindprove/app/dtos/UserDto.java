package com.mindprove.app.dtos;

import java.util.Date;

import com.mindprove.app.enums.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {

	private Long userId;
	private String fullName;
	private String email;
	private String password;
	private String confirmPassword;
	private Role role;
	private Date createdAt;
	private Date updatedAt;
}
