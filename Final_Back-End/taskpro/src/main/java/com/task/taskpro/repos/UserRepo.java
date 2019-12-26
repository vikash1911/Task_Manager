package com.task.taskpro.repos;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.task.taskpro.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	@Transactional
	@Modifying
	@Query("update User set "
			+ "first_name = :first_name, "
			+ "last_name = :last_name, "
			+ "employee_id = :employee_id "
			+ "where uid = :uid")
	int updateUser( 
			@Param("first_name") String first_name,
			@Param("last_name") String last_name,
			@Param("employee_id") int employee_id,
			@Param("uid") long uid);

}
