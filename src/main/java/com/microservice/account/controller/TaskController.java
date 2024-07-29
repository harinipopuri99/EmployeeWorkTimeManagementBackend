package com.microservice.account.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.model.Project;
import com.microservice.account.model.Task;
import com.microservice.account.service.TaskService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@PostMapping("/api/cap/task/employee/{eid}/{tid}")
	public void assignTask(@PathVariable("eid") int eid, @PathVariable("tid") int tid) {
		taskService.assignTask(eid, tid);
	}
	
	@PostMapping("/api/cap/task/project/{pid}")
	public void createTask(@PathVariable("pid") int pid, @RequestBody Task task) {
		taskService.createTask(pid, task);
	}
	
	@GetMapping("/api/cap/tasks/all")
	public List<Task> getAllTasks() {
		return taskService.getAllTasks();
	}
	
	@GetMapping("/api/cap/task/project/{pid}")
	public List<Task> getAllTasksByProject(@PathVariable("pid") int pid) {
		return taskService.getAllTasksByProject(pid);
	}
	
	@GetMapping("/api/cap/task/{eid}")
	public List<Task> getAllTask(@PathVariable("eid") int eid){
		return taskService.getAllTask(eid)
				.stream()
				.filter(t->t.isArchived() == false)
				.collect(Collectors.toList());
	}
	
	@GetMapping("/api/cap/task/archive/{tid}")
	public void updateTaskForArchival(@PathVariable("tid") int tid) {
		taskService.updateTaskForArchival(tid);
	}
	
	@GetMapping("/api/cap/priority/all")
	public List<String> getAllPriorities() {
		List<String> list = taskService.getAllPriorities();
		return list; 
	}

}