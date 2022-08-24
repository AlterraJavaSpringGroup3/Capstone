package com.organization.service;

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

import com.organization.dto.OrganizationDto.CreateOrganizationDto;
import com.organization.dto.OrganizationDto.UpdateOrganizationDto;
import com.organization.model.Organization;
import com.organization.repository.OrganizationRepository;

import lombok.SneakyThrows;
import lombok.val;

@Service
@Transactional(rollbackOn = Exception.class)
public class OrganizationService {

	@Autowired
	private OrganizationRepository organizationRepository;

	@SneakyThrows(Exception.class)
	public ResponseEntity<Object> createOrganization(CreateOrganizationDto dto) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		Organization organization = new Organization();

		organization.setOrganizationCode(dto.getOrganizationCode().trim());
		organization.setOrganizationName(dto.getOrganizationName().trim());
		organization.setOrganizationPhone(dto.getOrganizationPhone().trim());
		organization.setOrganizationAddress(dto.getOrganizationAddress().trim());
		organization.setOrganizationLat(dto.getOrganizationLat());
		organization.setOrganizationLong(dto.getOrganizationLong());
		organization.setOrganizationRadius(dto.getOrganizationRadius());

		val std = organizationRepository.findByOrganizationCode(dto.getOrganizationCode().trim());
		int error = 0;

		if (Optional.ofNullable(std).isPresent()) {
			res.put("message", "organizationCode is Already Exist.");
			error = 1;
			return ResponseEntity.status(HttpStatus.CONFLICT).headers(headers).body(res);
		}else if (dto.getOrganizationCode().trim() == null) {
			res.put("message", "organizationCode can't be Blank.");
			error = 1;
		}else if (dto.getOrganizationName().trim() == null) {
			res.put("message", "organizationName can't be Blank.");
			error = 1;
		}else if (dto.getOrganizationLat() == null) {
			res.put("message", "organizationLat can't be Blank.");
			error = 1;
		}else if (dto.getOrganizationLong() == null) {
			res.put("message", "organizationLong can't be Blank.");
			error = 1;
		}else if (dto.getOrganizationRadius() == null) {
			res.put("message", "organizationRadius can't be Blank.");
			error = 1;
		}else {
			organizationRepository.save(organization);
			res.put("message", "Success.");
			error = 0;
		}

		if(error==0) {
			val data = organizationRepository.findByOrganizationCode(dto.getOrganizationCode().trim());
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
	public ResponseEntity<Object> getOrganization(String organizationCode) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		val std = organizationRepository.findByOrganizationCode(organizationCode);

		if (Optional.ofNullable(std).isPresent()) {
			res.put("message", "Success.");
			res.put("data", std);
			res.put("code", HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
		} else {
			res.put("message", "organizationCode is Not Found.");
			res.put("data", null);
			res.put("code", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(res);
		}
	}

	@SneakyThrows(Exception.class)
	public ResponseEntity<Object> getOrganizations() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		val std = organizationRepository.findAll();

		res.put("code", HttpStatus.OK.value());
		res.put("message", "Success.");
		res.put("data", std);

		return ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
	}

	@SneakyThrows(Exception.class)
	public ResponseEntity<Object> updateOrganization(UpdateOrganizationDto dto) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		val organization = organizationRepository.findById(dto.getId()).orElse(null);
		int error = 0;

		if (Optional.ofNullable(organization).isPresent()) {
			if(organization.getOrganizationCode().trim()!=dto.getOrganizationCode().trim()) {
				val organizationCek = organizationRepository.findByOrganizationCode(dto.getOrganizationCode().trim());
				if (Optional.ofNullable(organizationCek).isPresent() && organizationCek.getId()!=dto.getId()) {
					error = 1;
				}else {
					error = 0;
				}
			}else{
				error = 0;
			}

			if(error==0) {
				organization.setOrganizationCode(dto.getOrganizationCode().trim());
				organization.setOrganizationName(dto.getOrganizationName().trim());
				organization.setOrganizationPhone(dto.getOrganizationPhone().trim());
				organization.setOrganizationAddress(dto.getOrganizationAddress().trim());
				organization.setOrganizationLat(dto.getOrganizationLat());
				organization.setOrganizationLong(dto.getOrganizationLong());
				organization.setOrganizationRadius(dto.getOrganizationRadius());  
				
				organizationRepository.save(organization);
				
				res.put("message", "Success."); 
				res.put("data", organization);
				res.put("code", HttpStatus.OK.value());
				return  ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
			}else {
				res.put("message", "organizationCode is Already Exist.");
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
	public ResponseEntity<Object> deleteOrganization(Long id) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		val organization = organizationRepository.findById(id).orElse(null);

		if (Optional.ofNullable(organization).isPresent()) {

			organizationRepository.deleteById(id);

			res.put("message", "Success.");
			res.put("data", organization);

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