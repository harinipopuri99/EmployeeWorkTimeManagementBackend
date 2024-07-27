package com.microservice.account.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.account.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer>{

}