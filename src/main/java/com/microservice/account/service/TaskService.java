package com.microservice.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.account.model.Employee;
import com.microservice.account.model.Task;
import com.microservice.account.repository.EmployeeRepository;
import com.microservice.account.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private TaskRepository taskRepository;

	public void assignTask(int eid, Task task) {
		Employee employee = employeeRepository.findById(eid).get();
		task.setEmployee(employee);
		taskRepository.save(task);
	}
	
	public List<Task> getAllTask(int eid) {

		return taskRepository.findByEmployeeId(eid);
	}

	public void updateTaskForArchival(int tid) {
		Task task = taskRepository.findById(tid).get();
		task.setArchived(true);
		taskRepository.save(task);
	}

}