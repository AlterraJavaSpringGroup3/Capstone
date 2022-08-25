package com.file;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import com.file.model.File;
import com.file.repository.FileRepository;
import com.ulisesbocchio.jasyptspringboot.configuration.EnableEncryptablePropertiesConfiguration;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(EnableEncryptablePropertiesConfiguration.class)
class FileApplicationTests {

	@Autowired
	private FileRepository fileRepository;

	@Test
	public void testCreateFile() {

		File file = new File();

		file.setFileCode("805d5ee3-d572-4f26-8f0a-5bf3ebab546d");
		file.setFileName("805d5ee3-d572-4f26-8f0a-5bf3ebab546d.jpg");

		File res = fileRepository.save(file);
		assertNotNull(res);
	}
	

	@Test
	public void testGetFile() {

		File file = new File();

		file.setFileCode("805d5ee3-d572-4f26-8f0a-5bf3ebab546d");
		file.setFileName("805d5ee3-d572-4f26-8f0a-5bf3ebab546d.jpg");

		fileRepository.save(file);
		
		File res = fileRepository.findByFileCode("805d5ee3-d572-4f26-8f0a-5bf3ebab546d");
		assertNotNull(res);
	}
}