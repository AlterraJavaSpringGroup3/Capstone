package com.file.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.file.model.File;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
	public File findByFileCode(String file);
}
