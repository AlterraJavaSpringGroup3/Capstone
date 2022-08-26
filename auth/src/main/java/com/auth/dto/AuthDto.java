package com.auth.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
public class UserDto {

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CreateUserDto {

		private String userName;
		private String userPassword;
		private String userRoles;
		private String employeeCode;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class UpdateUserDto {

		private Long id;
		private String userName;
		private String userPassword;
		private String userRoles;
		private String employeeCode;
	}
}
