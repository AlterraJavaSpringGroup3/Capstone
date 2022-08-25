package com.file.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.file.model.audit.AuditModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "File")
@Table(name = "file")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "createdDate", "lastModifiedDate" })
public class File extends AuditModel<String> implements Serializable {

	private static final long serialVersionUID = -4492412758881190961L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, insertable = false, unique=true)
	private Long id;

	@NotNull
	@Column(name = "file_code", nullable = false, length = 100, unique=true)
	private String fileCode;

	@NotNull
	@Column(name = "file_name", nullable = false, length = 255)
	private String fileName;
}
