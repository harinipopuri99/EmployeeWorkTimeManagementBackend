package com.microservice.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.account.dto.ResponseDto;
import com.microservice.account.exception.ResourceNotFoundException;
import com.microservice.account.model.Project;
import com.microservice.account.model.Region;
import com.microservice.account.service.ProjectService;
import com.microservice.account.service.RegionService;

@RestController
public class ProjectController {

	@Autowired
	private RegionService regionService;
	
	@Autowired
	private ProjectService projectService;
	
	@PostMapping("/api/cap/project/add/{regionId}")
	public ResponseEntity<?> postProject(@PathVariable("regionId") int regionId,  
			@RequestBody Project project) {
		
		/* Fetch region obj based on regionid */
		
		Region region = null; 
		try {
			region  = regionService.getRegionById(regionId);
		} catch (ResourceNotFoundException e) {
			return ResponseEntity
					 .badRequest()
					 .body(new ResponseDto(e.getMessage(), "400"));
		}
		/* Attach region to project */
		project.setRegion(region);
		
		/* Save project */
		project = projectService.postProject(project);
		return ResponseEntity.ok().body(project); 
	}
}