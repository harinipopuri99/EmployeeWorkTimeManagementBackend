package com.microservice.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.microservice.account.model.WorkLog;

public interface WorkLogRepository extends JpaRepository<WorkLog, Integer> {
}
