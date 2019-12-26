package com.task.taskpro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.taskpro.entities.ParentTask;
import com.task.taskpro.entities.Task;
import com.task.taskpro.repos.ParentTaskRepo;

@Service
public class ParentTaskService {

	@Autowired
	ParentTaskRepo parenttaskRepo;
	
	public List<ParentTask> getParentTaskVal(){
		return parenttaskRepo.findAll();
	}
	
	public ParentTask createParentTask(ParentTask parenttask){
		return parenttaskRepo.save(parenttask);
	}
	
	public Optional<ParentTask> getParentTaskById(Long parentTaskId){
		return parenttaskRepo.findById(parentTaskId);
	}
	
	public List<ParentTask> getParentTaskByProjectId(int id) {
        return parenttaskRepo.getPTByProjectId(id);
    }
}
