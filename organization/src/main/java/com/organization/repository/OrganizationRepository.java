package com.organization.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.organization.model.Organization;

@Repository
public interface OrganizationRepository extends JpaRepository<Organization, Long> {

	public Organization findByOrganizationCode(String organization);

}
