package com.attendance.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
public class AttendanceDto {

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class CreateAttendanceDto {

		private String attendancePhoto;
		private Double attendanceLong;
		private Double attendanceLat;
		private String attendanceLabel;
		private String attendanceDevicetime;
		private String attendanceTimezone;
		private String attendanceType;
		private String employeeCode;
	}

	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class UpdateAttendanceDto {

		private Long id;
		private String attendancePhoto;
		private Double attendanceLong;
		private Double attendanceLat;
		private String attendanceLabel;
		private String attendanceDevicetime;
		private String attendanceTimezone;
		private String attendanceType;
		private String employeeCode;
	}
}
