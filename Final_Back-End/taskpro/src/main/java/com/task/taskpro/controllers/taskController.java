package com.task.taskpro.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


import com.task.taskpro.entities.ParentTask;
import com.task.taskpro.entities.Project;
import com.task.taskpro.entities.Task;
import com.task.taskpro.entities.User;
import com.task.taskpro.service.ParentTaskService;
import com.task.taskpro.service.ProjectService;
import com.task.taskpro.service.TaskService;
import com.task.taskpro.service.UserService;

@RestController
@CrossOrigin("*")
@RequestMapping(path="/")
public class taskController {
	
	private static final Logger logger = LoggerFactory.getLogger(taskController.class);
	@Autowired
	ParentTaskService pts;
	
	@Autowired
	TaskService ts;
	
	@Autowired
	ProjectService ps;
	
	@Autowired
	UserService us;

	@RequestMapping(value = "/getAllParentTask", method = RequestMethod.GET)
    public List<ParentTask> getAllParentTask() {
		logger.info("getting list of parrent task");
        return pts.getParentTaskVal();
        
    }
	
	@RequestMapping(value = "/getParentTaskByProjectId/{Id}", method = RequestMethod.GET)
    public List<ParentTask> getParentTaskByProjectId(@PathVariable(value = "Id") Integer Id) {
		logger.info("getting of parrent task");
        return pts.getParentTaskByProjectId(Id);
    }

    @RequestMapping(value = "/createParentTask", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ParentTask createAuthor(@RequestBody Task pt) {
    	ParentTask parentTask = new ParentTask();
    	parentTask.setParent_Pro_ID(pt.getProject_ID());
    	parentTask.setParent_Task(pt.getTask());
    	try {
    		logger.info("Creating new parent task");
        return pts.createParentTask(parentTask);
      
    	} catch(Exception e) {
    		logger.info("error while Creating new parent task"+e.getMessage());
    		return null;
    	}
    }

    @RequestMapping(value = "/getAllTasks", method = RequestMethod.GET)
    public List<Task> getAllTasks() {
        return ts.getAllTasks();
    }
    
    @RequestMapping(value = "/getTaskByProjectId/{Id}", method = RequestMethod.GET)
    public List<Task> getTaskByProjectId(@PathVariable(value = "Id") Integer Id) {
    	List<Task> taskList = ts.getTaskByProjectId(Id);
    	
    	return taskList;
    }
    @RequestMapping(value = "/getTaskById/{Id}", method = RequestMethod.GET)
    public List<Task> getTaskById(@PathVariable(value = "Id") Long Id) {
    	
    	Optional<Task> task = ts.getTaskById(Id);
    	
    	List<Task> taskList = new ArrayList<Task>();
    	taskList.add(task.get());
    	return taskList;
    }
    
    @RequestMapping(value = "/getAllProjects", method = RequestMethod.GET)
    public List<Project> getAllProjects() {
        return ps.getAllProjects();
    }
    
    @RequestMapping(value = "/getProjectById/{id}", method = RequestMethod.GET)
    public Project getProjectById(@PathVariable(value = "id") Long id) {
    	Optional<Project> asw = ps.getProjectById(id);
    	return asw.get();
    }
    
    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return us.getAllUser();
    }
    
    @RequestMapping(value = "/getUserById/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable(value = "id") Long id) {
    	Optional<User> users = us.getUserById(id);
    	return users.get();
    }
    
    @RequestMapping(value = "/getNumOfTasksById/{id}", method = RequestMethod.GET)
    public int getNumOfTasksById(@PathVariable(value = "id") Long id) {
    	int numberofTask = ts.getNumOfTasksById(id);
    	return numberofTask;
    }
    
    @RequestMapping(value = "/deleteUserById/{id}", method = RequestMethod.DELETE)
    public void deleteUserById(@PathVariable(value = "id") Long id) {
    	try {
    	us.deleteUser(id);
    	logger.info("user deleted with id::"+id);
    	} catch(Exception e) {
    		logger.info("error in user delation with id::"+e.getMessage());
    	}
    }

    @RequestMapping(value = "/{parentId}/task", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Task createTask(@PathVariable(value = "parentId") Long parentId, @RequestBody Task task) {
    	try {
    		logger.info("creating new Task ");
    		  return ts.createTask(parentId, task);
    	} catch (Exception e) {
    		logger.info("error in creting  new Task "+e.getMessage());
    		return null;
    	}
       
    }
    
    @RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User createUser(@RequestBody User user) {
    	try {
    		logger.info("creating new User ");
    		return us.createUser(user);
    	} catch (Exception e) {
    		logger.info("error in creting  user "+e.getMessage());
    		return null;
    	}
        
    }
    
    @RequestMapping(value = "/updateUser/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public int updateUser(@PathVariable(value = "id") Long Id,@RequestBody User user) {
       
        try {
    		logger.info("Updateing user with user id  "+Id);
    		return us.updateUser(user, Id);
    	} catch (Exception e) {
    		logger.info("error in Updateing user "+e.getMessage());
    		return -1;
    	}
    }
    
    @RequestMapping(value = "/addProject", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Project createProject(@RequestBody Project project) {
    	try {
    		logger.info("creating new Project ");
    		return ps.createProject(project);
    	} catch (Exception e) {
    		logger.info("error in creting  Project "+e.getMessage());
    		return null;
    	}
         
    }
    
    @RequestMapping(value = "updateTask/{id}/{ptId}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public int updateTask(@PathVariable(value = "id") Long Id, @PathVariable(value = "ptId") Long ptId, @RequestBody Task task) {
    	ParentTask pt = new ParentTask();
    	pt.setParent_ID(ptId);
    	task.setParentTask(pt);
      
        try {
    		logger.info("updateing task  ");
    		return ts.updateTaskService(task, Id);
    	} catch (Exception e) {
    		logger.info("error in updateing task "+e.getMessage());
    		return -1;
    	}
    }
    
    @RequestMapping(value = "updateProject/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public int updateProject(@PathVariable(value = "id") Long Id, @RequestBody Project project) {
       
        
        try {
    		logger.info("updateing Project  ");
    		 return ps.updateProject(Id, project);
    	} catch (Exception e) {
    		logger.info("error in updateing Project "+e.getMessage());
    		return -1;
    	}
    }
    
    @RequestMapping(value = "disableTask/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public int disableTask(@PathVariable(value = "id") Long Id, @RequestBody Task task) {
        return ts.disableTaskService(Id);
    }
}
