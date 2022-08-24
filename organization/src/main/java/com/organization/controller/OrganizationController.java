package com.organization.controller;

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

import com.organization.dto.OrganizationDto.CreateOrganizationDto;
import com.organization.dto.OrganizationDto.UpdateOrganizationDto;
import com.organization.service.OrganizationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api("Organization Service")
public class OrganizationController {

	@Autowired
	private OrganizationService organizationService;

	@SneakyThrows(Exception.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieving data"),
			@ApiResponse(code = 201, message = "Successfully creating data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There are errors which come from codes or data") })
	@ApiOperation(value = "An API for creating organization data", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(path = "/organization/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> createOrganization(@RequestBody CreateOrganizationDto dto) {

		log.info("POST http://localhost:8081/api/v1/organization/create is called");
		return organizationService.createOrganization(dto);
	}

	@SneakyThrows(Exception.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieving data"),
			@ApiResponse(code = 201, message = "Successfully creating data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There are errors which come from codes or data") })
	@ApiOperation(value = "An API for getting organization data", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(path = "/organization/{organizationCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getOrganization(@PathVariable(name = "organizationCode") String organizationCode) {

		log.info("GET http://localhost:8081/api/v1/organization/{} is called", organizationCode);
		return organizationService.getOrganization(organizationCode);
	}

	@SneakyThrows(Exception.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieving data"),
			@ApiResponse(code = 201, message = "Successfully creating data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There are errors which come from codes or data") })
	@ApiOperation(value = "An API for getting all organizations data", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(path = "/organization/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getOrganizations() {

		log.info("GET http://localhost:8081/api/v1/organization/all is called");
		return organizationService.getOrganizations();
	}

	@SneakyThrows(Exception.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieving data"),
			@ApiResponse(code = 201, message = "Successfully creating data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There are errors which come from codes or data") })
	@ApiOperation(value = "An API for updating organization data", produces = MediaType.APPLICATION_JSON_VALUE)
	@PutMapping(path = "/organization/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateOrganization(@RequestBody UpdateOrganizationDto dto) {

		log.info("PUT http://localhost:8081/api/v1/organization/update is called...");
		return organizationService.updateOrganization(dto);
	}

	@SneakyThrows(Exception.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieving data"),
			@ApiResponse(code = 201, message = "Successfully creating data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There are errors which come from codes or data") })
	@ApiOperation(value = "An API for deleting organization data", produces = MediaType.APPLICATION_JSON_VALUE)
	@DeleteMapping(path = "/organization/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> deleteOrganization(@PathVariable(name = "id") Long id) {

		log.info("DELETE http://localhost:8081/api/v1/organization/{} is called", id);

		return organizationService.deleteOrganization(id);
	}
}
