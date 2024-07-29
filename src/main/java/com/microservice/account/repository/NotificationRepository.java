package com.microservice.account.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.account.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{

	List<Notification> findByEmployeeId(int eid);

}
