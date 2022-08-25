package com.employee.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.employee.dto.EmployeeDto.CreateEmployeeDto;
import com.employee.dto.EmployeeDto.UpdateEmployeeDto;
import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;

import lombok.SneakyThrows;
import lombok.val;

@Service
@Transactional(rollbackOn = Exception.class)
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@SneakyThrows(Exception.class)
	public ResponseEntity<Object> createEmployee(CreateEmployeeDto dto) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		Employee employee = new Employee();

		employee.setEmployeeCode(dto.getEmployeeCode().trim());
		employee.setEmployeeName(dto.getEmployeeName().trim());
		employee.setEmployeePhone(dto.getEmployeePhone().trim());
		employee.setEmployeeAddress(dto.getEmployeeAddress().trim());
		employee.setOrganizationCode(dto.getOrganizationCode());

		val std = employeeRepository.findByEmployeeCode(dto.getEmployeeCode().trim());
		int error = 0;

		if (Optional.ofNullable(std).isPresent()) {
			res.put("message", "employeeCode is Already Exist.");
			error = 1;
			return ResponseEntity.status(HttpStatus.CONFLICT).headers(headers).body(res);
		}else if (dto.getEmployeeCode().trim() == null) {
			res.put("message", "employeeCode can't be Blank.");
			error = 1;
		}else if (dto.getEmployeeName().trim() == null) {
			res.put("message", "employeeName can't be Blank.");
			error = 1;
		}else if (dto.getOrganizationCode() == null) {
			res.put("message", "organizationCode can't be Blank.");
			error = 1;
		}else {
			employeeRepository.save(employee);
			res.put("message", "Success.");
			error = 0;
		}

		if(error==0) {
			val data = employeeRepository.findByEmployeeCode(dto.getEmployeeCode().trim());
			res.put("data", data);
			res.put("code", HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(res);
		}else {
			res.put("data", null);
			res.put("code", HttpStatus.CONFLICT.value());
			return ResponseEntity.status(HttpStatus.CONFLICT).headers(headers).body(res);
		}
	}

	@SneakyThrows(Exception.class)
	public ResponseEntity<Object> getEmployee(String employeeCode) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		val std = employeeRepository.findByEmployeeCode(employeeCode);

		if (Optional.ofNullable(std).isPresent()) {
			res.put("id", std.getId());
			res.put("organizationCode", std.getEmployeeCode());
			res.put("employeeName", std.getEmployeeName());
			res.put("employeePhone", std.getEmployeePhone());
			res.put("employeeAddress", std.getEmployeeAddress());
			res.put("organizationCode", std.getOrganizationCode());
			res.put("organizationName", ServiceCall.organizationName(std.getOrganizationCode()));
			res.put("createdAt", std.getCreatedDate());
			res.put("updatedAt", std.getLastModifiedDate());
			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
		} else {
			res.put("message", "employeeCode is Not Found.");
			res.put("data", null);
			res.put("code", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(res);
		}
	}

	@SneakyThrows(Exception.class)
	public ResponseEntity<Object> getEmployees() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		val std = employeeRepository.findAll();

		res.put("code", HttpStatus.OK.value());
		res.put("message", "Success.");
		res.put("data", std);

		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
	}

	@SneakyThrows(Exception.class)
	public ResponseEntity<Object> updateEmployee(UpdateEmployeeDto dto) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		val employee = employeeRepository.findById(dto.getId()).orElse(null);
		int error = 0;

		if (Optional.ofNullable(employee).isPresent()) {
			if(employee.getEmployeeCode().trim()!=dto.getEmployeeCode().trim()) {
				val employeeCek = employeeRepository.findByEmployeeCode(dto.getEmployeeCode().trim());
				if (Optional.ofNullable(employeeCek).isPresent() && employeeCek.getId()!=dto.getId()) {
					error = 1;
				}else {
					error = 0;
				}
			}else{
				error = 0;
			}

			if(error==0) {
				employee.setEmployeeCode(dto.getEmployeeCode().trim());
				employee.setEmployeeName(dto.getEmployeeName().trim());
				employee.setEmployeePhone(dto.getEmployeePhone().trim());
				employee.setEmployeeAddress(dto.getEmployeeAddress().trim());
				employee.setOrganizationCode(dto.getOrganizationCode().trim());
				
				employeeRepository.save(employee);
				
				res.put("message", "Success."); 
				res.put("data", employee);
				res.put("code", HttpStatus.CREATED.value());
				return  ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(res);
			}else {
				res.put("message", "employeeCode is Already Exist.");
				res.put("data", null);
				res.put("code", HttpStatus.CONFLICT.value());
				return ResponseEntity.status(HttpStatus.CONFLICT).headers(headers).body(res);
			}

		} else {
			res.put("message", "Id is Not Found.");
			res.put("data", null);
			res.put("code", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(res);
		}


	}

	@SneakyThrows(Exception.class)
	public ResponseEntity<Object> deleteEmployee(Long id) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		val employee = employeeRepository.findById(id).orElse(null);

		if (Optional.ofNullable(employee).isPresent()) {

			employeeRepository.deleteById(id);

			res.put("message", "Success.");
			res.put("data", employee);

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