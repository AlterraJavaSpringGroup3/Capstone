package com.employee;

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

import com.employee.model.Employee;
import com.employee.repository.EmployeeRepository;
import com.ulisesbocchio.jasyptspringboot.configuration.EnableEncryptablePropertiesConfiguration;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(EnableEncryptablePropertiesConfiguration.class)
class EmployeeApplicationTests {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void testCreateEmployee() {

		Employee employee = new Employee();

		employee.setEmployeeCode("TESTING");
		employee.setEmployeeName("Mashum Abdul Jabbar");
		employee.setEmployeePhone("0812345678");
		employee.setEmployeeAddress("Jakarta Timur");
		employee.setOrganizationCode("IKON");

		Employee res = employeeRepository.save(employee);
		assertNotNull(res);
	}

	@Test
	public void testGetEmployee() {

		Employee employee = new Employee();

		employee.setEmployeeCode("TESTING");
		employee.setEmployeeName("Mashum Abdul Jabbar");
		employee.setEmployeePhone("0812345678");
		employee.setEmployeeAddress("Jakarta Timur");
		employee.setOrganizationCode("IKON");

		employeeRepository.save(employee);
		
		Employee res = employeeRepository.findByEmployeeCode("TESTING");
		assertNotNull(res);
	}

	@Test
	public void testGetEmployees() {

		Employee employee = new Employee();

		employee.setEmployeeCode("TESTING");
		employee.setEmployeeName("Mashum Abdul Jabbar");
		employee.setEmployeePhone("0812345678");
		employee.setEmployeeAddress("Jakarta Timur");
		employee.setOrganizationCode("IKON");
		employeeRepository.save(employee);
		
		List<Employee> res = employeeRepository.findAll();
		assertThat(res).size().isGreaterThan(0);
	}

	@Test
	public void testUpdateEmployee() {
		
		Employee employee = new Employee();

		employee.setEmployeeCode("TESTING");
		employee.setEmployeeName("Mashum Abdul Jabbar");
		employee.setEmployeePhone("0812345678");
		employee.setEmployeeAddress("Jakarta Timur");
		employee.setOrganizationCode("IKON");

		employeeRepository.save(employee);
		
		Employee employeeUpdate = employeeRepository.findByEmployeeCode("TESTING");

		employee.setEmployeeCode("TESTING");
		employee.setEmployeeName("Mashum Abdul Jabbar");
		employee.setEmployeePhone("0812345678");
		employee.setEmployeeAddress("Jakarta Timur");
		employee.setOrganizationCode("IKON");

		Employee resUpdate = employeeRepository.save(employeeUpdate);
		assertNotNull(resUpdate);
	}

	@Test
	public void testDeleteEmployee() {
		Employee employee = new Employee();

		employee.setEmployeeCode("TESTING");
		employee.setEmployeeName("Mashum Abdul Jabbar");
		employee.setEmployeePhone("0812345678");
		employee.setEmployeeAddress("Jakarta Timur");
		employee.setOrganizationCode("IKON");

		employeeRepository.save(employee);
		
		Employee employeeDelete = employeeRepository.findByEmployeeCode("TESTING");

		employeeRepository.delete(employeeDelete);

		assertNull(employeeRepository.findByEmployeeCode("TESTING"));
	}
}