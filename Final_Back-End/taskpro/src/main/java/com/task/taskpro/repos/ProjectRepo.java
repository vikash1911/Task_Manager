package com.task.taskpro.repos;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task.taskpro.entities.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long> {
	
	@Transactional
	@Modifying
	@Query("update Project set "
			+ "project = :project, "
			+ "pro_start_date = :pro_start_date, "
			+ "pro_end_date = :pro_end_date, "
			+ "pro_priority = :pro_priority, "
			+ "pro_user_id = :pro_user_id "
			+ "where pid = :pid")
	int updateProject( 
			@Param("project") String project,
			@Param("pro_start_date") Date pro_start_date,
			@Param("pro_end_date") Date pro_end_date,
			@Param("pro_priority") int pro_priority,
			@Param("pro_user_id") int pro_user_id,
			@Param("pid") long pid);

}
