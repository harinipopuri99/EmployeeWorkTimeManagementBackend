package com.microservice.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.microservice.account.model.Employee;
import com.microservice.account.model.Project;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	@Query(nativeQuery = true , value = "select * from employee where name LIKE %?1% OR city LIKE %?1%")
	List<Employee> searchEmployeeOnName(String searchStr);

	@Query("select e from Employee e where e.name LIKE %?1% OR e.city LIKE %?1%")
	List<Employee> searchEmployeeOnNameJpql(String searchStr);
	
	/* @Query("select p from Project p "
	            + " JOIN p.employees e "
	            + " where e.userInfo.username = ?1")
	    List<Project> getProjectByEmployeeJpql(String username);*/

	
	@Query("SELECT e FROM Employee e JOIN e.userInfo ui WHERE ui.username = :username")
    Employee getEmployeeByUsername(@Param("username") String username);

}
