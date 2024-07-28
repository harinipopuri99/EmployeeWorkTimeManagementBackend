package com.microservice.account.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.dto.ResponseDto;
import com.microservice.account.exception.ResourceNotFoundException;
import com.microservice.account.model.Employee;
import com.microservice.account.model.EmployeeProject;
import com.microservice.account.model.Project;
import com.microservice.account.service.EmployeeProjectService;
import com.microservice.account.service.EmployeeService;
import com.microservice.account.service.ProjectService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class EmployeeProjectController {

	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ProjectService projectService;
	@Autowired
	private EmployeeProjectService employeeProjectService;
	
	@PostMapping("/api/cap/employee/project/assign/{employeeId}/{projectId}")
	public ResponseEntity<?> assignProjectToEmployee(
			@PathVariable("employeeId") int employeeId, 
			@PathVariable("projectId") int projectId) {
		
		Employee employee = null; 
		/* fetch employee based in employeeId, if invalid, throw exception  */
		try {
			employee = employeeService.getEmployeeById(employeeId);
		} catch (ResourceNotFoundException e) {
			 return ResponseEntity
					 .badRequest()
					 .body(new ResponseDto(e.getMessage(), "400"));
		}
		Project project = null; 
		/* fetch project based in projectId, if invalid, throw exception  */
		try {
			project = projectService.getProjectById(projectId);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity
					 .badRequest()
					 .body(new ResponseDto(e.getMessage(), "400"));
		}
		/* set employee obj and project obj to employeeProjectObj  */
		EmployeeProject emp = new EmployeeProject();
		emp.setEmployee(employee);
		emp.setProject(project);
		emp.setDateOfAssign(LocalDate.now());
		
		/* save employeeProjectObj */
		emp = employeeProjectService.postEmployeeProject(emp);
		return ResponseEntity
				 .ok()
				 .body(emp);
	}
	
	@GetMapping("/api/cap/employee/projects")
    public List<Project> getProjectsByEmployeeUsername(Principal principal) {
        String username = principal.getName();
        return employeeProjectService.getProjectsByEmployeeUsername(username);
    }
}