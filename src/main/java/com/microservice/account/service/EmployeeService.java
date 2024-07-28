package com.microservice.account.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservice.account.dto.ProjectDto;
import com.microservice.account.dto.TaskDto;
import com.microservice.account.dto.WorkLogDto;
import com.microservice.account.enums.JobTitle;
import com.microservice.account.exception.ResourceNotFoundException;
import com.microservice.account.model.Employee;
import com.microservice.account.model.Project;
import com.microservice.account.model.Task;
import com.microservice.account.model.WorkLog;
import com.microservice.account.repository.EmployeeRepository;
import com.microservice.account.repository.ManagerRepository;
import com.microservice.account.repository.ProjectRepository;
import com.microservice.account.repository.TaskRepository;
import com.microservice.account.repository.WorkLogRepository;

@Service
public class EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private WorkLogRepository workLogRepository;

	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	public Employee addEmployee(Employee employee) {
		String rawPass = employee.getUserInfo().getPassword();
		String encodedPass = passwordEncoder.encode(rawPass);
		employee.getUserInfo().setPassword(encodedPass);
		return employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		employeeRepository.deleteById(id);
	}

	public Employee getEmployeeById(int id) throws ResourceNotFoundException {
		
		 Optional<Employee> optional = employeeRepository.findById(id);
		 if(optional.isEmpty()) {
			 throw new ResourceNotFoundException("Invalid Employee Id Given");
		 }
		
		return optional.get();
	}

	public List<String> getAllJobType() {
		JobTitle[] titles = JobTitle.values();
		List<JobTitle> list = Arrays.asList(titles);
		List<String> listStr = new ArrayList<>();
		list.stream().forEach(jt->{
			listStr.add(jt.toString());
		});
		return listStr;
	}
	
	public List<Employee> searchEmployeeOnName(String searchStr) {
		return employeeRepository.searchEmployeeOnNameJpql(searchStr);

	}

	public Employee getEmployeeByUsername(String username) {
		return employeeRepository.getEmployeeByUsername(username);
	}

	/*public List<Project> getProjectsByEmployee(String username) {
		// TODO Auto-generated method stub
		return employeeRepository.getProjectByEmployeeJpql(username);
	}*/
	

	
}