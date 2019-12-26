package com.task.taskpro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.taskpro.entities.Project;
import com.task.taskpro.repos.ProjectRepo;

@Service
public class ProjectService {

	@Autowired
	ProjectRepo projectRepo;
	
	public List<Project> getAllProjects(){
		return projectRepo.findAll();
	}
	
	public Project createProject(Project project){
		return projectRepo.save(project);
	}
	
	public Optional<Project> getProjectById(Long projectId){
		return projectRepo.findById(projectId);
	}

	public int updateProject(Long id, Project pro) {
		return projectRepo.updateProject(pro.getProject(), pro.getPro_start_date(),
				pro.getPro_end_date(), pro.getPro_priority(),
				pro.getPro_user_id(),id);
	}
}
