package com.microservice.account.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.dto.ResponseDto;
import com.microservice.account.exception.ResourceNotFoundException;
import com.microservice.account.model.Employee;
import com.microservice.account.model.Notification;
import com.microservice.account.model.Project;
import com.microservice.account.model.Task;
import com.microservice.account.service.EmployeeService;
import com.microservice.account.service.NotificationService;
import com.microservice.account.service.RegionService;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class NotificationController {

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/api/cap/notification/{eid}")
	public ResponseEntity<?> sendNotification(@PathVariable("eid") int eid, @RequestBody Notification notification) {
		Employee employee = null;
		try {
			employee = employeeService.getEmployeeById(eid);
		} catch (ResourceNotFoundException e) {
			// TODO Auto-generated catch block
			return ResponseEntity
					 .badRequest()
					 .body(new ResponseDto(e.getMessage(), "400"));
		}
		notification.setEmployee(employee);
		notification = notificationService.postNotification(notification);
		return ResponseEntity.ok().body(notification);
		
	}
	
	@GetMapping("/api/cap/notification/employee/{eid}")
	public List<Notification> getAllNotificationsByEmployee(@PathVariable("eid") int eid) {
		return notificationService.getAllNotificationsByEmployee(eid);
	}
	
	@GetMapping("/api/cap/notification/employee/all")
    public List<Notification> getNotificationsByEmployeeUsername(Principal principal) {
        String username = principal.getName();
        return notificationService.getNotificationsByEmployeeUsername(username)
        		.stream()
				.filter(t->t.isArchived() == false)
				.collect(Collectors.toList());
    }
	
	@GetMapping("/api/cap/notification/archive/{nid}")
	public void updateNotificationForArchival(@PathVariable("nid") int nid) {
		notificationService.updateNotificationForArchival(nid);
	}
}
