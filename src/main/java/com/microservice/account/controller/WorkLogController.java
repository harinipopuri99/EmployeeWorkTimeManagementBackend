package com.microservice.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.model.WorkLog;
import com.microservice.account.service.TaskService;
import com.microservice.account.service.WorkLogService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class WorkLogController {
	
	@Autowired
	private WorkLogService workLogService;
	

	@PostMapping("/api/cap/task/worklog/{tid}/{eid}")
	public WorkLog createWorkLog(@PathVariable("tid") int tid, @PathVariable("eid") int eid, @RequestBody WorkLog workLog) {
		return workLogService.createWorkLog(tid, eid, workLog);
	}

}
