package com.microservice.account.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.account.enums.JobTitle;
import com.microservice.account.enums.Priority;
import com.microservice.account.model.Employee;
import com.microservice.account.model.Project;
import com.microservice.account.model.Task;
import com.microservice.account.repository.EmployeeRepository;
import com.microservice.account.repository.ProjectRepository;
import com.microservice.account.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private TaskRepository taskRepository;
	@Autowired
	private ProjectRepository projectRepository;

	public void assignTask(int eid, int tid) {
		Employee employee = employeeRepository.findById(eid).get();
		Task task = taskRepository.findById(tid).get();
		
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

	public void createTask(int pid, Task task) {
		Project project = projectRepository.findById(pid).get();
		task.setProject(project);
		taskRepository.save(task);
	}

	public List<Task> getAllTasksByProject(int pid) {
		return taskRepository.findByProjectId(pid);
	}

	public List<Task> getAllTasks() {
		return taskRepository.findAll();
	}

	public List<String> getAllPriorities() {
		Priority[] p = Priority.values();
		List<Priority> list = Arrays.asList(p);
		List<String> listStr = new ArrayList<>();
		list.stream().forEach(pr->{
			listStr.add(pr.toString());
		});
		return listStr;
	}

}