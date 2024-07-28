package com.microservice.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microservice.account.model.EmployeeProject;
import com.microservice.account.model.Project;

public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Integer>{
	
	@Query("SELECT p FROM Project p "
	         + "JOIN EmployeeProject ep ON p.id = ep.project.id "
	         + "JOIN ep.employee e "
	         + "JOIN e.userInfo u "
	         + "WHERE u.username = ?1")
	    List<Project> findProjectsByEmployeeUsername(String username);
}