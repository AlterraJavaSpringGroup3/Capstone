package com.file.service;
import java.util.UUID;

import javax.transaction.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.file.model.File;
import com.file.repository.FileRepository;

import lombok.SneakyThrows;
import lombok.val;

@Service
@Transactional(rollbackOn = Exception.class)
public class FileService {

	@Autowired
	private FileRepository fileRepository;

	public String getExtensionByApacheCommonLib(String filename) {
	    return FilenameUtils.getExtension(filename);
	}
	
	@SneakyThrows(Exception.class)
	public ResponseEntity<Object> upload(MultipartFile multipartFile) {

		Map<String, Object> res = new HashMap<String, Object>();
		  
		String fileName = multipartFile.getOriginalFilename();
		String fileExt = FilenameUtils.getExtension(fileName);
		String fileCode = UUID.randomUUID().toString();
		String fileNameToFirebase = fileCode+"."+fileExt;

		File files = new File();

		files.setFileName(fileNameToFirebase);
		files.setFileCode(fileCode);
		
		HttpHeaders headersakhir = new HttpHeaders();
		
		if (fileName == null || fileName == "") {
			res.put("message", "File Not Found.");
			res.put("data", null);
			res.put("code", HttpStatus.CONFLICT.value());
			return ResponseEntity.status(HttpStatus.CONFLICT).headers(headersakhir).body(res);
		}else{

			String urlFirebase = "https://firebasestorage.googleapis.com/v0/b/capstone-alterra.appspot.com/o?uploadType=media&name="+fileNameToFirebase;
			HttpHeaders headersawal = new HttpHeaders();
			headersawal.setContentType(MediaType.MULTIPART_FORM_DATA);

	        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
	        body.add("bar", multipartFile.getResource());

	        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headersawal);
	        
	        RestTemplate restTemplate = new RestTemplate();
	        
	        restTemplate.postForEntity(
	        		urlFirebase,
	            requestEntity,
	            String.class
	        );

	        headersakhir.setContentType(MediaType.APPLICATION_JSON);
			fileRepository.save(files);
			res.put("message", "Success.");
			res.put("data", files);
			res.put("code", HttpStatus.CREATED.value());
			return ResponseEntity.status(HttpStatus.CREATED).headers(headersakhir).body(res);
		}
	}
	
	@SneakyThrows(Exception.class)
	public ResponseEntity<Object> getFile(String fileCode) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Map<String, Object> res = new HashMap<String, Object>();

		val std = fileRepository.findByFileCode(fileCode);
		if (Optional.ofNullable(std).isPresent()) {
			
			Map<String, String> data = new HashMap<String, String>();   
			data.put("fileCode", std.getFileCode());
			data.put("filePath", "https://firebasestorage.googleapis.com/v0/b/capstone-alterra.appspot.com/o/"+std.getFileName()+"?alt=media");
			res.put("message", "Success.");
			res.put("data", data);
			res.put("code", HttpStatus.OK.value());
			return ResponseEntity.status(HttpStatus.OK).headers(headers).body(res);
		} else {
			res.put("message", "fileCode is Not Found.");
			res.put("data", null);
			res.put("code", HttpStatus.NOT_FOUND.value());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).headers(headers).body(res);
		}
	}
}
