package com.microservice.account.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.account.model.Notification;
import com.microservice.account.model.Project;
import com.microservice.account.model.Task;
import com.microservice.account.repository.NotificationRepository;

@Service
public class NotificationService {
	
	@Autowired
	private NotificationRepository notificationRepository;


	public Notification postNotification(Notification notification) {
		// TODO Auto-generated method stub
		return notificationRepository.save(notification);
	}


	public List<Notification> getAllNotificationsByEmployee(int eid) {
		// TODO Auto-generated method stub
		return notificationRepository.findByEmployeeId(eid);
	}


	public List<Notification> getNotificationsByEmployeeUsername(String username) {
		return notificationRepository.findNotificationsByEmployeeUsername(username);
		
	}


	public void updateNotificationForArchival(int nid) {
		Notification notification = notificationRepository.findById(nid).get();
		notification.setArchived(true);
		notificationRepository.save(notification);
		
	}
	

	

}
