package com.microservice.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.microservice.account.model.Task;

public interface TaskRepository extends JpaRepository<Task, Integer>{
	
	List<Task> findByEmployeeId(int eid);

	List<Task> findByProjectId(int pid);

	@Query("SELECT t FROM Task t "
	         + "JOIN t.employee e "
	         + "JOIN e.userInfo u "
	         + "WHERE u.username = ?1")
	List<Task> findTasksByEmployeeUsername(String username);

}