package com.organization.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
public class OrganizationDto {

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CreateOrganizationDto {

		private String organizationCode;
		private String organizationName;
		private String organizationPhone;
		private String organizationAddress;
		private Double organizationLat;
		private Double organizationLong;
		private Double organizationRadius;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class UpdateOrganizationDto {

		private Long id;
		private String organizationCode;
		private String organizationName;
		private String organizationPhone;
		private String organizationAddress;
		private Double organizationLat;
		private Double organizationLong;
		private Double organizationRadius;
	}
}
