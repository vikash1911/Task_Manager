package com.task.taskpro.repos;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task.taskpro.entities.ParentTask;
import com.task.taskpro.entities.Task;


@Repository
public interface TaskRepo extends JpaRepository<Task, Long> {

	@Transactional
	@Modifying
	@Query("update Task set "
			+ "Task = :Task, "
			+ "Parent_ID = :parent_Id, "
			+ "Priority = :Priority, "
			+ "Start_Date = :Start_Date, "
			+ "End_Date = :End_Date, "
			+ "Project_ID = :Project_ID, "
			+ "User_ID = :User_ID "
			+ "where Task_ID = :task_ID")
	int updateTaskVal( 
			@Param("Task") String Task,
			@Param("parent_Id") long parent_Id,
			@Param("Priority") int Priority,
			@Param("Start_Date") Date Start_Date,
			@Param("End_Date") Date End_Date,
			@Param("Project_ID") int Project_ID,
			@Param("User_ID") int User_ID,
			@Param("task_ID") long task_ID);
	
	@Transactional
	@Modifying
	@Query("update Task set "
			+ "End_Task = true "
			+ "where Task_ID = :task_ID")
	int disableTask( 
			@Param("task_ID") long task_ID);
	
	@Transactional
	@Modifying
	@Query("from Task where Project_ID = :project_ID")
	List<Task> getTaskByProId( 
			@Param("project_ID") int project_ID);
			
}
