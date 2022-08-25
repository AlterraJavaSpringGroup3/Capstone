package com.attendance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.attendance.dto.AttendanceDto.CreateAttendanceDto;
import com.attendance.dto.AttendanceDto.UpdateAttendanceDto;
import com.attendance.service.AttendanceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api("Attendance Service")
public class AttendanceController {

	@Autowired
	private AttendanceService attendanceService;

	@SneakyThrows(Exception.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieving data"),
			@ApiResponse(code = 201, message = "Successfully creating data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There are errors which come from codes or data") })
	@ApiOperation(value = "An API for creating attendance data", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(path = "/attendance/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createAttendance(@RequestBody CreateAttendanceDto dto) {

		log.info("POST http://localhost:8085/api/v1/attendance/create is called");
		return attendanceService.createAttendance(dto);
	}

	@SneakyThrows(Exception.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieving data"),
			@ApiResponse(code = 201, message = "Successfully creating data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There are errors which come from codes or data") })
	@ApiOperation(value = "An API for getting attendance data", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(path = "/attendance/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAttendance(@PathVariable(name = "id") Long id) {

		log.info("GET http://localhost:8085/api/v1/attendance/{} is called", id);
		return attendanceService.getAttendance(id);
	}

	@SneakyThrows(Exception.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieving data"),
			@ApiResponse(code = 201, message = "Successfully creating data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There are errors which come from codes or data") })
	@ApiOperation(value = "An API for getting all attendances data", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(path = "/attendance/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getAttendances() {

		log.info("GET http://localhost:8085/api/v1/attendance/all is called");
		return attendanceService.getAttendances();
	}

	@SneakyThrows(Exception.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieving data"),
			@ApiResponse(code = 201, message = "Successfully creating data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There are errors which come from codes or data") })
	@ApiOperation(value = "An API for updating attendance data", produces = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping(path = "/attendance/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateAttendance(@RequestBody UpdateAttendanceDto dto) {

		log.info("PUT http://localhost:8085/api/v1/attendance/update is called...");
		return attendanceService.updateAttendance(dto);
	}

	@SneakyThrows(Exception.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieving data"),
			@ApiResponse(code = 201, message = "Successfully creating data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There are errors which come from codes or data") })
	@ApiOperation(value = "An API for deleting attendance data", produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(path = "/attendance/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteAttendance(@PathVariable(name = "id") Long id) {

		log.info("DELETE http://localhost:8085/api/v1/attendance/{} is called", id);

		return attendanceService.deleteAttendance(id);
	}
}
