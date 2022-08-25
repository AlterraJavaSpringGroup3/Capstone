package com.file.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.file.service.FileService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api("File Service")
public class FileController {
	
	@Autowired
    private FileService fileService;
    
	@SneakyThrows(Exception.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieving data"),
			@ApiResponse(code = 201, message = "Successfully creating data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There are errors which come from codes or data") })
	@ApiOperation(value = "An API for creating file data", produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping(path = "/file/create")
	public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile multipartFile) {
        log.info("HIT -/upload | File Name : {}", multipartFile.getOriginalFilename());
        return fileService.upload(multipartFile);
    }
	
	@SneakyThrows(Exception.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieving data"),
			@ApiResponse(code = 201, message = "Successfully creating data"),
			@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
			@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
			@ApiResponse(code = 404, message = "The resource you were trying to reach is not found"),
			@ApiResponse(code = 500, message = "There are errors which come from codes or data") })
	@ApiOperation(value = "An API for getting organization data", produces = MediaType.APPLICATION_JSON_VALUE)
	@GetMapping(path = "/file/{fileCode}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getFile(@PathVariable(name = "fileCode") String fileCode) {

		log.info("GET http://localhost:8081/api/v1/file/{} is called", fileCode);
		return fileService.getFile(fileCode);
	}
}
