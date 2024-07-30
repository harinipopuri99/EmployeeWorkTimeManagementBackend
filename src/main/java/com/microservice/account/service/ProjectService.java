package com.microservice.account.service;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.microservice.account.dto.EmployeeDto;
import com.microservice.account.dto.ManagerDto;
import com.microservice.account.dto.ProjectDto;
import com.microservice.account.dto.TaskDto;
import com.microservice.account.exception.ResourceNotFoundException;
import com.microservice.account.model.Employee;
import com.microservice.account.model.Manager;
import com.microservice.account.model.Project;
import com.microservice.account.model.Task;
import com.microservice.account.repository.ProjectRepository;
import com.microservice.account.repository.TaskRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	@Autowired
	private TaskRepository taskRepository;

	public Project getProjectById(int projectId) throws ResourceNotFoundException {
		Optional<Project> optional = projectRepository.findById(projectId);
		if (optional.isEmpty())
			throw new ResourceNotFoundException("Project Id Invalid...");
		return optional.get();
	}

	public Project postProject(Project project) {

		return projectRepository.save(project);
	}

	public List<Project> getAllProjects() {
		
		return projectRepository.findAll();
	}

	public List<ProjectDto> getAllProjectsWithTasks() {
		List<Task> listTask = taskRepository.findAll();
		List<Project> listProject = projectRepository.findAll();

		List<ProjectDto> pDtoList = new ArrayList<>();

		listProject.stream().forEach(p->{
			ProjectDto pDto = new ProjectDto();
			pDto.setId(p.getId());
			pDto.setName(p.getName());
			pDto.setDescription(p.getDescription());
		

			List<Task> filteredList 
					= listTask
							.stream()
							.filter(t->t.getProject().getId() == p.getId())
							.collect(Collectors.toList());

			List<TaskDto> listTaskDto = new ArrayList<>();
			filteredList.stream().forEach(t->{
				TaskDto tDto = new TaskDto(); 
				tDto.setId(t.getId());
				tDto.setName(t.getName());
				tDto.setTaskDetails(t.getTaskDetails());
				tDto.setPriority(t.getPriority());
				tDto.setStartDate(t.getStartDate());
				tDto.setEndDate(t.getEndDate());
				listTaskDto.add(tDto);
			});
			pDto.setTasks(listTaskDto);
			pDtoList.add(pDto);
		});
		return pDtoList;
	}
	
	



}