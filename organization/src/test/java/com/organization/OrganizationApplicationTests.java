package com.organization;

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

import com.organization.model.Organization;
import com.organization.repository.OrganizationRepository;
import com.ulisesbocchio.jasyptspringboot.configuration.EnableEncryptablePropertiesConfiguration;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(EnableEncryptablePropertiesConfiguration.class)
class OrganizationApplicationTests {

	@Autowired
	private OrganizationRepository organizationRepository;

	@Test
	public void testCreateOrganization() {

		Organization organization = new Organization();

		organization.setOrganizationCode("TESTING");
		organization.setOrganizationName("PT Ikonsultan Inovatama");
		organization.setOrganizationPhone("(021)29857220");
		organization.setOrganizationAddress("Beltway Office Park, Jakarta");
		organization.setOrganizationLat(-6.2931353d);
		organization.setOrganizationLong(106.8219903d);
		organization.setOrganizationRadius(100.0d);

		Organization res = organizationRepository.save(organization);
		assertNotNull(res);
	}

	@Test
	public void testGetOrganization() {

		Organization organization = new Organization();

		organization.setOrganizationCode("TESTING");
		organization.setOrganizationName("PT Ikonsultan Inovatama");
		organization.setOrganizationPhone("(021)29857220");
		organization.setOrganizationAddress("Beltway Office Park, Jakarta");
		organization.setOrganizationLat(-6.2931353d);
		organization.setOrganizationLong(106.8219903d);
		organization.setOrganizationRadius(100.0d);

		organizationRepository.save(organization);
		
		Organization res = organizationRepository.findByOrganizationCode("TESTING");
		assertNotNull(res);
	}

	@Test
	public void testGetOrganizations() {

		Organization organization = new Organization();

		organization.setOrganizationCode("TESTING");
		organization.setOrganizationName("PT Ikonsultan Inovatama");
		organization.setOrganizationPhone("(021)29857220");
		organization.setOrganizationAddress("Beltway Office Park, Jakarta");
		organization.setOrganizationLat(-6.2931353d);
		organization.setOrganizationLong(106.8219903d);
		organization.setOrganizationRadius(100.0d);

		organizationRepository.save(organization);
		
		List<Organization> res = organizationRepository.findAll();
		assertThat(res).size().isGreaterThan(0);
	}

	@Test
	public void testUpdateOrganization() {
		
		Organization organization = new Organization();

		organization.setOrganizationCode("TESTING");
		organization.setOrganizationName("PT Ikonsultan Inovatama");
		organization.setOrganizationPhone("(021)29857220");
		organization.setOrganizationAddress("Beltway Office Park, Jakarta");
		organization.setOrganizationLat(-6.2931353d);
		organization.setOrganizationLong(106.8219903d);
		organization.setOrganizationRadius(100.0d);

		organizationRepository.save(organization);
		
		Organization organizationUpdate = organizationRepository.findByOrganizationCode("TESTING");

		organizationUpdate.setOrganizationCode("TESTINGUPDATE");
		organizationUpdate.setOrganizationName("PT Ikonsultan Inovatama");
		organizationUpdate.setOrganizationPhone("(021)29857220");
		organizationUpdate.setOrganizationAddress("Beltway Office Park, Jakarta");
		organizationUpdate.setOrganizationLat(-6.2931353d);
		organizationUpdate.setOrganizationLong(106.8219903d);
		organizationUpdate.setOrganizationRadius(100.0d);

		Organization resUpdate = organizationRepository.save(organizationUpdate);
		assertNotNull(resUpdate);
	}

	@Test
	public void testDeleteOrganization() {
		Organization organization = new Organization();

		organization.setOrganizationCode("TESTING");
		organization.setOrganizationName("PT Ikonsultan Inovatama");
		organization.setOrganizationPhone("(021)29857220");
		organization.setOrganizationAddress("Beltway Office Park, Jakarta");
		organization.setOrganizationLat(-6.2931353d);
		organization.setOrganizationLong(106.8219903d);
		organization.setOrganizationRadius(100.0d);

		organizationRepository.save(organization);
		
		Organization organizationDelete = organizationRepository.findByOrganizationCode("TESTING");

		organizationRepository.delete(organizationDelete);

		assertNull(organizationRepository.findByOrganizationCode("TESTING"));
	}
}