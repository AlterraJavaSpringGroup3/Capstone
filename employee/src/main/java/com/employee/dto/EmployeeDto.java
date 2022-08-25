package com.employee.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
public class EmployeeDto {

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CreateEmployeeDto {

		private String employeeCode;
		private String employeeName;
		private String employeePhone;
		private String employeeAddress;
		private String organizationCode;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class UpdateEmployeeDto {

		private Long id;
		private String employeeCode;
		private String employeeName;
		private String employeePhone;
		private String employeeAddress;
		private String organizationCode;
	}
}
