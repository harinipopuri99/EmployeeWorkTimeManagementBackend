package com.microservice.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.model.Task;
import com.microservice.account.service.TaskService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class TaskController {

	@Autowired
	private TaskService taskService;
	@PostMapping("/api/cap/task/employee/{eid}")
	public void assignTask(@PathVariable("eid") int eid, @RequestBody Task task) {
		taskService.assignTask(eid,task);
	}
}