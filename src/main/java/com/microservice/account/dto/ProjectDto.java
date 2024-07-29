package com.microservice.account.dto;

import java.util.List;

public class ProjectDto {

	private int id;
    private String name;
    private String description;
    private List<TaskDto> tasks;

    // Getters and Setters
    
    public String getName() {
        return name;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<TaskDto> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDto> tasks) {
        this.tasks = tasks;
    }
}
