package com.microservice.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.account.model.Employee;
import com.microservice.account.model.Project;
import com.microservice.account.model.Task;
import com.microservice.account.model.WorkLog;
import com.microservice.account.repository.EmployeeRepository;
import com.microservice.account.repository.TaskRepository;
import com.microservice.account.repository.WorkLogRepository;

@Service
public class WorkLogService {

	@Autowired
	private WorkLogRepository workLogRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

	public WorkLog createWorkLog(int tid, int eid, WorkLog workLog) {
		Task task = taskRepository.findById(tid).get();
		Employee employee = employeeRepository.findById(eid).get();
		workLog.setTask(task);
		workLog.setEmployee(employee);
		return workLogRepository.save(workLog);
		
	}
	
	
}
