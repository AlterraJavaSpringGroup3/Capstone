package com.auth.model;

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
import com.auth.model.audit.AuditModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "User")
@Table(name = "user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "createdDate", "lastModifiedDate" })
public class User extends AuditModel<String> implements Serializable {

	private static final long serialVersionUID = -4492412758881190961L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, insertable = false, unique=true)
	private Long id;

	@NotEmpty
	@NotBlank(message = "User Name is mandatory")
	@Column(name = "user_name", nullable = false, length = 30, unique=true)
	private String userName;

	@NotEmpty
	@NotBlank(message = "User Password is mandatory")
	@Column(name = "user_password", nullable = false, length = 100)
	private String userPassword;

	@Column(name = "user_roles", nullable = true, length = 30)
	private String userRoles;
	
	@Column(name = "employee_code", nullable = true, length = 30)
	private String employeeCode;

}
