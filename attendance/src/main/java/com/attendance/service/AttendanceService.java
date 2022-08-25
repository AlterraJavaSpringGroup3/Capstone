package com.attendance.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.attendance.dto.AttendanceDto.CreateAttendanceDto;
import com.attendance.dto.AttendanceDto.UpdateAttendanceDto;
import com.attendance.model.Attendance;
import com.attendance.repository.AttendanceRepository;

import lombok.SneakyThrows;
import lombok.val;

@Service
@Transactional(rollbackOn = Exception.class)
public class AttendanceService {
	
	@Value("${timezonedb.password}")
	private String timezonedb;
	
	@Autowired
	private AttendanceRepository attendanceRepository;
	
	@SneakyThrows(Exception.class)
	public ResponseEntity<Object> createAttendance(CreateAttendanceDto dto) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		Attendance attendance = new Attendance();

		attendance.setAttendancePhoto(dto.getAttendancePhoto().trim());
		attendance.setAttendanceLong(dto.getAttendanceLong());
		attendance.setAttendanceLat(dto.getAttendanceLat());
		attendance.setAttendanceDevicetime(dto.getAttendanceDevicetime());
		attendance.setAttendanceType(dto.getAttendanceType().trim());
		attendance.setEmployeeCode(dto.getEmployeeCode().trim());
		attendance.setAttendanceTimezone(ServiceCall.timeZone(timezonedb, dto.getAttendanceLat(), dto.getAttendanceLong()));
		attendance.setAttendanceLabel(ServiceDistance.label(dto.getAttendanceLat(), dto.getAttendanceLong()));
		
		int error = 0;
		
		if (dto.getAttendanceLong() == null) {
			res.put("message", "attendanceLong can't be Blank.");
			error = 1;
		}else if (dto.getAttendanceLat() == null) {
			res.put("message", "attendanceLat can't be Blank.");
			error = 1;
		}else if (dto.getEmployeeCode().trim() == null) {
			res.put("message", "employeeCode can't be Blank.");
			error = 1;
		}else {
			res.put("message", "Success.");
			error = 0;
		}

		if(error==0) {
			attendanceRepository.save(attendance);
			res.put("data", attendance);
			res.put("code", HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(res);
		}else {
			res.put("data", null);
			res.put("code", HttpStatus.CONFLICT.value());
			return ResponseEntity.status(HttpStatus.CONFLICT).headers(headers).body(res);
		}
	}

	@SneakyThrows(Exception.class)
	public ResponseEntity<Object> getAttendance(Long Id) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		val std = attendanceRepository.findById(Id).orElse(null);

		if (Optional.ofNullable(std).isPresent()) {
			res.put("message", "Success.");
			res.put("data", std);
			res.put("code", HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
		} else {
			res.put("message", "ID is Not Found.");
			res.put("data", null);
			res.put("code", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(res);
		}
	}

	@SneakyThrows(Exception.class)
	public ResponseEntity<Object> getAttendances() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		val std = attendanceRepository.findAll();

		res.put("code", HttpStatus.OK.value());
		res.put("message", "Success.");
		res.put("data", std);

		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
	}

	@SneakyThrows(Exception.class)
	public ResponseEntity<Object> updateAttendance(UpdateAttendanceDto dto) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		val attendance = attendanceRepository.findById(dto.getId()).orElse(null);

		if (Optional.ofNullable(attendance).isPresent()) {
			attendance.setAttendancePhoto(dto.getAttendancePhoto().trim());
			attendance.setAttendanceLong(dto.getAttendanceLong());
			attendance.setAttendanceLat(dto.getAttendanceLat());
			attendance.setAttendanceDevicetime(dto.getAttendanceDevicetime());
			attendance.setAttendanceType(dto.getAttendanceType().trim());
			attendance.setEmployeeCode(dto.getEmployeeCode().trim());
			attendance.setAttendanceTimezone(ServiceCall.timeZone(timezonedb, dto.getAttendanceLat(), dto.getAttendanceLong()));
			attendance.setAttendanceLabel(ServiceDistance.label(dto.getAttendanceLat(), dto.getAttendanceLong()));
			attendanceRepository.save(attendance);
			attendance.setCreatedDate(attendance.getCreatedDate());		
			res.put("message", "Success."); 
			res.put("data", attendance);
			res.put("code", HttpStatus.OK.value());
			return  ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
		} else {
			res.put("message", "Id is Not Found.");
			res.put("data", null);
			res.put("code", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(res);
		}


	}

	@SneakyThrows(Exception.class)
	public ResponseEntity<Object> deleteAttendance(Long id) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		val attendance = attendanceRepository.findById(id).orElse(null);

		if (Optional.ofNullable(attendance).isPresent()) {

			attendanceRepository.deleteById(id);

			res.put("message", "Success.");
			res.put("data", attendance);

		} else {
			res.put("message", "Id is Not Found.");
			res.put("data", null);
			res.put("code", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(res);
		}

		res.put("code", HttpStatus.OK.value());

		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
	}
}