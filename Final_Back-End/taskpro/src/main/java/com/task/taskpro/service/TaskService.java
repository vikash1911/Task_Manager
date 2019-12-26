package com.task.taskpro.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.taskpro.entities.ParentTask;
import com.task.taskpro.entities.Task;
import com.task.taskpro.repos.ParentTaskRepo;
import com.task.taskpro.repos.TaskRepo;

@Service
public class TaskService {

	@Autowired
	TaskRepo taskRepo;
	
	@Autowired
	ParentTaskRepo parentTaskRepo;
	
	public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }

	public List<Task> getTaskByProjectId(int id) {
        return taskRepo.getTaskByProId(id);
    }
	
	public Optional<Task> getTaskById(long id) {
		//List<Long> d = new ArrayList<Long>();
		//d.add(id);
        return taskRepo.findById(id);
    }
	
	public int getNumOfTasksById(long id) {
		
        List<Task> ss = taskRepo.getTaskByProId((int)id);
        return ss.size();
    }
    /*public Optional<Task> getBookById(Long bookId) {
        if (!bookDao.existsById(bookId)) {
            throw new ResourceNotFoundException("Book with id " + bookId + " not found");
        }
        return bookDao.findById(bookId);
    }*/

	public int updateTaskService(Task task, Long Id){
		return taskRepo.updateTaskVal(task.getTask(), task.getParentTask_id(), 
	    		task.getPriority(), task.getStart_Date(), task.getEnd_Date(), 
	    		task.getProject_ID(), task.getUser_ID(), Id);
	}
	
	public int disableTaskService(Long Id){
		return taskRepo.disableTask(Id);
	}

    public Task createTask(Long parentTaskId, Task task) {
        Set<Task> tasks = new HashSet<>();
        Task tt = new Task();
        ParentTask author1 = new ParentTask();

        Optional<ParentTask> byId = parentTaskRepo.findById(parentTaskId);
       // if (!byId.isPresent()) {
       //     throw new ResourceNotFoundException("Author with id " + authorId + " does not exist");
      //  }
        ParentTask parentTask = byId.get();

        //tie Author to Book
        task.setParentTask(parentTask);
    
        Task taskk = taskRepo.save(task);
        //tie Book to Author
        tasks.add(taskk);
        author1.setParent_Task(task.getTask());
        //author1.setTasks(tasks);
        parentTaskRepo.save(author1);
        return taskk;
    }

}
