package com.auth;

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

import com.auth.model.User;
import com.auth.repository.UserRepository;
import com.ulisesbocchio.jasyptspringboot.configuration.EnableEncryptablePropertiesConfiguration;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(EnableEncryptablePropertiesConfiguration.class)
class UserApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testCreateUser() {

		User user = new User();

		user.setUserName("userTESTING");
		user.setUserPassword("mashum**123");
		user.setUserRoles("PEGAWAI");
		user.setEmployeeCode("IKON");

		User res = userRepository.save(user);
		assertNotNull(res);
	}

	@Test
	public void testGetUser() {

		User user = new User();

		user.setUserName("userTESTING");
		user.setUserPassword("mashum**123");
		user.setUserRoles("PEGAWAI");
		user.setEmployeeCode("IKON");

		userRepository.save(user);
		
		User res = userRepository.findByUserName("userTESTING");
		assertNotNull(res);
	}

	@Test
	public void testGetUsers() {

		User user = new User();

		user.setUserName("userTESTING");
		user.setUserPassword("mashum**123");
		user.setUserRoles("PEGAWAI");
		user.setEmployeeCode("IKON");
		userRepository.save(user);
		
		List<User> res = userRepository.findAll();
		assertThat(res).size().isGreaterThan(0);
	}

	@Test
	public void testUpdateUser() {
		
		User user = new User();

		user.setUserName("userTESTING");
		user.setUserPassword("mashum**123");
		user.setUserRoles("PEGAWAI");
		user.setEmployeeCode("IKON");

		userRepository.save(user);
		
		User userUpdate = userRepository.findByUserName("userTESTING");

		user.setUserName("userTESTING");
		user.setUserPassword("mashum**123");
		user.setUserRoles("PEGAWAI");
		user.setEmployeeCode("IKON");

		User resUpdate = userRepository.save(userUpdate);
		assertNotNull(resUpdate);
	}

	@Test
	public void testDeleteUser() {
		User user = new User();

		user.setUserName("userTESTING");
		user.setUserPassword("mashum**123");
		user.setUserRoles("PEGAWAI");
		user.setEmployeeCode("IKON");

		userRepository.save(user);
		
		User userDelete = userRepository.findByUserName("userTESTING");

		userRepository.delete(userDelete);

		assertNull(userRepository.findByUserName("userTESTING"));
	}
}