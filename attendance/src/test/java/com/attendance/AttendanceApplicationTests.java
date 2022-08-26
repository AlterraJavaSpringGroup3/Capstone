package com.attendance;

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

import com.attendance.model.Attendance;
import com.attendance.repository.AttendanceRepository;
import com.ulisesbocchio.jasyptspringboot.configuration.EnableEncryptablePropertiesConfiguration;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Import(EnableEncryptablePropertiesConfiguration.class)
class AttendanceApplicationTests {

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Test
	public void testCreateAttendance() {

		Attendance attendance = new Attendance();

		attendance.setAttendancePhoto("TESTING");
		attendance.setAttendanceLong(106.8219903d);
		attendance.setAttendanceLat(-6.3402008d);
		attendance.setAttendanceDevicetime("2022-08-24 08:00:00");
		attendance.setAttendanceType("Clock In");
		attendance.setEmployeeCode("20220800001");

		Attendance res = attendanceRepository.save(attendance);
		assertNotNull(res);
	}

	@Test
	public void testGetAttendance() {

		Attendance attendance = new Attendance();

		attendance.setAttendancePhoto("TESTING");
		attendance.setAttendanceLong(106.8219903d);
		attendance.setAttendanceLat(-6.3402008d);
		attendance.setAttendanceDevicetime("2022-08-24 08:00:00");
		attendance.setAttendanceType("Clock In");
		attendance.setEmployeeCode("20220800001");

		attendanceRepository.save(attendance);
		
		Attendance res = attendanceRepository.findByAttendancePhoto("TESTING");
		assertNotNull(res);
	}

	@Test
	public void testGetAttendances() {

		Attendance attendance = new Attendance();

		attendance.setAttendancePhoto("TESTING");
		attendance.setAttendanceLong(106.8219903d);
		attendance.setAttendanceLat(-6.3402008d);
		attendance.setAttendanceDevicetime("2022-08-24 08:00:00");
		attendance.setAttendanceType("Clock In");
		attendance.setEmployeeCode("20220800001");

		attendanceRepository.save(attendance);
		
		List<Attendance> res = attendanceRepository.findAll();
		assertThat(res).size().isGreaterThan(0);
	}

	@Test
	public void testUpdateAttendance() {
		
		Attendance attendance = new Attendance();

		attendance.setAttendancePhoto("TESTING");
		attendance.setAttendanceLong(106.8219903d);
		attendance.setAttendanceLat(-6.3402008d);
		attendance.setAttendanceDevicetime("2022-08-24 08:00:00");
		attendance.setAttendanceType("Clock In");
		attendance.setEmployeeCode("20220800001");

		attendanceRepository.save(attendance);
		
		Attendance attendanceUpdate = attendanceRepository.findByAttendancePhoto("TESTING");

		attendance.setAttendancePhoto("TESTING");
		attendance.setAttendanceLong(106.8219903d);
		attendance.setAttendanceLat(-6.3402008d);
		attendance.setAttendanceDevicetime("2022-08-24 08:00:00");
		attendance.setAttendanceType("Clock In");
		attendance.setEmployeeCode("20220800001");

		Attendance resUpdate = attendanceRepository.save(attendanceUpdate);
		assertNotNull(resUpdate);
	}

	@Test
	public void testDeleteAttendance() {
		Attendance attendance = new Attendance();

		attendance.setAttendancePhoto("TESTING");
		attendance.setAttendanceLong(106.8219903d);
		attendance.setAttendanceLat(-6.3402008d);
		attendance.setAttendanceDevicetime("2022-08-24 08:00:00");
		attendance.setAttendanceType("Clock In");
		attendance.setEmployeeCode("20220800001");

		attendanceRepository.save(attendance);
		
		Attendance attendanceDelete = attendanceRepository.findByAttendancePhoto("TESTING");

		attendanceRepository.delete(attendanceDelete);

		assertNull(attendanceRepository.findByAttendancePhoto("TESTING"));
	}
}