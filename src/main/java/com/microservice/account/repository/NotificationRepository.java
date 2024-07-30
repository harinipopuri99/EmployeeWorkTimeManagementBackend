package com.microservice.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.microservice.account.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{

	List<Notification> findByEmployeeId(int eid);

	 @Query("SELECT n FROM Notification n "
		         + "JOIN n.employee e "
		         + "JOIN e.userInfo u "
		         + "WHERE u.username = ?1")
	List<Notification> findNotificationsByEmployeeUsername(String username);

	

}
