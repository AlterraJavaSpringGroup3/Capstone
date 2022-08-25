package com.user.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.user.dto.UserDto.CreateUserDto;
import com.user.dto.UserDto.UpdateUserDto;
import com.user.model.User;
import com.user.repository.UserRepository;

import lombok.SneakyThrows;
import lombok.val;

@Service
@Transactional(rollbackOn = Exception.class)
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@SneakyThrows(Exception.class)
	public ResponseEntity<Object> createUser(CreateUserDto dto) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		User user = new User();

		user.setUserName(dto.getUserName().trim());
		user.setUserPassword(dto.getUserPassword().trim());
		user.setUserRoles(dto.getUserRoles().trim());
		user.setEmployeeCode(dto.getEmployeeCode().trim());

		val std = userRepository.findByUserName(dto.getUserName().trim());
		int error = 0;

		if (Optional.ofNullable(std).isPresent()) {
			res.put("message", "userName is Already Exist.");
			error = 1;
			return ResponseEntity.status(HttpStatus.CONFLICT).headers(headers).body(res);
		}else if (dto.getUserName().trim() == null) {
			res.put("message", "userName can't be Blank.");
			error = 1;
		}else if (dto.getUserPassword().trim() == null) {
			res.put("message", "userPassword can't be Blank.");
			error = 1;
		}else if (dto.getEmployeeCode() == null) {
			res.put("message", "employeeCode can't be Blank.");
			error = 1;
		}else {
			userRepository.save(user);
			res.put("message", "Success.");
			error = 0;
		}

		if(error==0) {
			val data = userRepository.findByUserName(dto.getUserName().trim());
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
	public ResponseEntity<Object> getUser(String userName) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		val std = userRepository.findByUserName(userName);

		if (Optional.ofNullable(std).isPresent()) {
			String employeeName = ServiceCall.employeeName(std.getEmployeeCode());
			
			Map<String, Object> data = new HashMap<String, Object>();   
			data.put("id", std.getId());
			data.put("userName", std.getUserName());
			data.put("employeeName", employeeName);
			data.put("userRoles",  std.getUserRoles());
			data.put("employeeCode",  std.getEmployeeCode());
			
			res.put("message", "Success.");
			res.put("data", data);
			res.put("code", HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
		} else {
			res.put("message", "userName is Not Found.");
			res.put("data", null);
			res.put("code", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(res);
		}
	}

	@SneakyThrows(Exception.class)
	public ResponseEntity<Object> getUsers() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		val std = userRepository.findAll();

		res.put("code", HttpStatus.OK.value());
		res.put("message", "Success.");
		res.put("data", std);

		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
	}

	@SneakyThrows(Exception.class)
	public ResponseEntity<Object> updateUser(UpdateUserDto dto) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		val user = userRepository.findById(dto.getId()).orElse(null);
		int error = 0;

		if (Optional.ofNullable(user).isPresent()) {
			if(user.getUserName().trim()!=dto.getUserName().trim()) {
				val userCek = userRepository.findByUserName(dto.getUserName().trim());
				if (Optional.ofNullable(userCek).isPresent() && userCek.getId()!=dto.getId()) {
					error = 1;
				}else {
					error = 0;
				}
			}else{
				error = 0;
			}

			if(error==0) {
				user.setUserName(dto.getUserName().trim());
				user.setUserPassword(dto.getUserPassword().trim());
				user.setUserRoles(dto.getUserRoles().trim());
				user.setEmployeeCode(dto.getEmployeeCode().trim());
				
				userRepository.save(user);
				
				res.put("message", "Success."); 
				res.put("data", user);
				res.put("code", HttpStatus.CREATED.value());
				return  ResponseEntity.status(HttpStatus.CREATED).headers(headers).body(res);
			}else {
				res.put("message", "userName is Already Exist.");
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
	public ResponseEntity<Object> deleteUser(Long id) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		val user = userRepository.findById(id).orElse(null);

		if (Optional.ofNullable(user).isPresent()) {

			userRepository.deleteById(id);

			res.put("message", "Success.");
			res.put("data", user);

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