package com.employee.controller;

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

import com.employee.dto.EmployeeDto.CreateEmployeeDto;
import com.employee.dto.EmployeeDto.UpdateEmployeeDto;
import com.employee.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api("Employee Service")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@SneakyThrows(Exception.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieving data"),
			@ApiResponse(code = 201, message = "Successfully creating data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There are errors which come from codes or data") })
	@ApiOperation(value = "An API for creating employee data", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(path = "/employee/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createEmployee(@RequestBody CreateEmployeeDto dto) {

		log.info("POST http://localhost:8084/api/v1/employee/create is called");
		return employeeService.createEmployee(dto);
	}

	@SneakyThrows(Exception.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieving data"),
			@ApiResponse(code = 201, message = "Successfully creating data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There are errors which come from codes or data") })
	@ApiOperation(value = "An API for getting employee data", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(path = "/employee/{employeeCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getEmployee(@PathVariable(name = "employeeCode") String employeeCode) {

		log.info("GET http://localhost:8084/api/v1/employee/{} is called", employeeCode);
		return employeeService.getEmployee(employeeCode);
	}

	@SneakyThrows(Exception.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieving data"),
			@ApiResponse(code = 201, message = "Successfully creating data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There are errors which come from codes or data") })
	@ApiOperation(value = "An API for getting all employees data", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(path = "/employee/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getEmployees() {

		log.info("GET http://localhost:8084/api/v1/employee/all is called");
		return employeeService.getEmployees();
	}

	@SneakyThrows(Exception.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieving data"),
			@ApiResponse(code = 201, message = "Successfully creating data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There are errors which come from codes or data") })
	@ApiOperation(value = "An API for updating employee data", produces = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping(path = "/employee/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateEmployee(@RequestBody UpdateEmployeeDto dto) {

		log.info("PUT http://localhost:8084/api/v1/employee/update is called...");
		return employeeService.updateEmployee(dto);
	}

	@SneakyThrows(Exception.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieving data"),
			@ApiResponse(code = 201, message = "Successfully creating data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There are errors which come from codes or data") })
	@ApiOperation(value = "An API for deleting employee data", produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(path = "/employee/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteEmployee(@PathVariable(name = "id") Long id) {

		log.info("DELETE http://localhost:8084/api/v1/employee/{} is called", id);

		return employeeService.deleteEmployee(id);
	}
}
