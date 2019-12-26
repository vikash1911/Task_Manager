package com.task.taskpro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.task.taskpro.entities.User;
import com.task.taskpro.repos.UserRepo;

@Service
public class UserService {

	@Autowired
	UserRepo userRepo;
	
	public List<User> getAllUser(){
		return userRepo.findAll();
	}
	
	public User createUser(User user){
		return userRepo.save(user);
	}
	
	public Optional<User> getUserById(Long Id){
		return userRepo.findById(Id);
	}
	
	public void deleteUser(Long Id){
		userRepo.deleteById(Id);
	}

	public int updateUser(User user, Long id) {
		return userRepo.updateUser(user.getFirst_name(), user.getLast_name(), 
				user.getEmployee_id(), id);
	}
}
