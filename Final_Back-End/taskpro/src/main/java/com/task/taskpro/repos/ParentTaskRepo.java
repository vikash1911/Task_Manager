package com.task.taskpro.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task.taskpro.entities.ParentTask;

@Repository
public interface ParentTaskRepo extends JpaRepository<ParentTask, Long> {

	@Transactional
	@Modifying
	@Query("from ParentTask where Parent_Pro_ID = :parent_Pro_ID")
	List<ParentTask> getPTByProjectId( 
			@Param("parent_Pro_ID") int parent_Pro_ID);
}
