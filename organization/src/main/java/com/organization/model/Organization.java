package com.organization.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.organization.model.audit.AuditModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Organization")
@Table(name = "organization")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "createdDate", "lastModifiedDate" })
public class Organization extends AuditModel<String> implements Serializable {

	private static final long serialVersionUID = -4492412758881190961L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, insertable = false, unique=true)
	private Long id;

	@NotEmpty
	@NotBlank(message = "Organization Code is mandatory")
	@Column(name = "organization_code", nullable = false, length = 30, unique=true)
	private String organizationCode;

	@NotEmpty
	@NotBlank(message = "Organization Name is mandatory")
	@Column(name = "organization_name", nullable = false, length = 100)
	private String organizationName;

	@Column(name = "organization_phone", nullable = true, length = 20)
	private String organizationPhone;

	@Column(name = "organization_address", nullable = true, columnDefinition = "TEXT")
	private String organizationAddress;

	@NotNull
	@Column(name = "organization_lat", nullable = false)
	private Double organizationLat;

	@NotNull
	@Column(name = "organization_long", nullable = false)
	private Double organizationLong;

	@NotNull
	@Column(name = "organization_radius", nullable = false)
	private Double organizationRadius;

}
