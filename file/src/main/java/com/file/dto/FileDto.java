package com.file.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
public class FileDto {

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CreateFileDto {
		private String fileCode;
		private String fileName;
	}
}
