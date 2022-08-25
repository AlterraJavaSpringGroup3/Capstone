package com.attendance.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.attendance.model.audit.AuditModel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Attendance")
@Table(name = "attendance")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value = { "createdDate", "lastModifiedDate" })
public class Attendance extends AuditModel<String> implements Serializable {

	private static final long serialVersionUID = -4492412758881190961L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, insertable = false, unique=true)
	private Long id;

	@Column(name = "attendance_photo", nullable = true, length = 100)
	private String attendancePhoto;

	@NotNull
	@Column(name = "attendance_long", nullable = false)
	private Double attendanceLong;

	@NotNull
	@Column(name = "attendance_lat", nullable = false)
	private Double attendanceLat;
	
	@Column(name = "attendance_timezone", nullable = true, length = 30)
	private String attendanceTimezone;
	
	@Column(name = "attendance_label", nullable = true, length = 30)
	private String attendanceLabel;
	
	@Column(name = "attendance_devicetime", nullable = true, length = 30)
	private String attendanceDevicetime;

	@Column(name = "attendance_type", nullable = true, length = 30)
	private String attendanceType;

	@NotNull
	@Column(name = "employee_code", nullable = false, length = 30)
	private String employeeCode;

}
