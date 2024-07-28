package com.microservice.account.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name = "employee_project")
public class EmployeeProject {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	
	private LocalDate dateOfAssign;
	
	@ManyToOne
	private Employee employee; 
	
	@ManyToOne
	private Project project; 
	
	@ManyToOne
	private Task task;
	

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public LocalDate getDateOfAssign() {
		return dateOfAssign;
	}

	public void setDateOfAssign(LocalDate dateOfAssign) {
		this.dateOfAssign = dateOfAssign;
	} 
	
	
}