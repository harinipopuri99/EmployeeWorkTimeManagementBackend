package com.microservice.account.dto;

import java.time.LocalDate;

public class TaskDto {

    private int id;
    private String taskDetails;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isArchived;
    private int projectId;
    private int assignedEmployeeId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTaskDetails() {
		return taskDetails;
	}
	public void setTaskDetails(String taskDetails) {
		this.taskDetails = taskDetails;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public boolean isArchived() {
		return isArchived;
	}
	public void setArchived(boolean isArchived) {
		this.isArchived = isArchived;
	}
	public int getProjectId() {
		return projectId;
	}
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	public int getAssignedEmployeeId() {
		return assignedEmployeeId;
	}
	public void setAssignedEmployeeId(int assignedEmployeeId) {
		this.assignedEmployeeId = assignedEmployeeId;
	}

    // Getters and Setters
    

}