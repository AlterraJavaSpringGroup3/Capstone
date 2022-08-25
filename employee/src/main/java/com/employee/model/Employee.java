package com.employee.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.employee.model.audit.AuditModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Employee")
@Table(name = "employee")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "createdDate", "lastModifiedDate" })
public class Employee extends AuditModel<String> implements Serializable {

	private static final long serialVersionUID = -4492412758881190961L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, insertable = false, unique=true)
	private Long id;

	@NotEmpty
	@NotBlank(message = "Employee Code is mandatory")
	@Column(name = "employee_code", nullable = false, length = 30, unique=true)
	private String employeeCode;

	@NotEmpty
	@NotBlank(message = "Employee Name is mandatory")
	@Column(name = "employee_name", nullable = false, length = 100)
	private String employeeName;

	@Column(name = "employee_phone", nullable = true, length = 20)
	private String employeePhone;

	@Column(name = "employee_address", nullable = true, columnDefinition = "TEXT")
	private String employeeAddress;
	
	@Column(name = "organization_code", nullable = true, length = 30)
	private String organizationCode;

}
