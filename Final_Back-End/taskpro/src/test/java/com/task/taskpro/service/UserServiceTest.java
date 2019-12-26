package com.task.taskpro.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.task.taskpro.entities.Project;
import com.task.taskpro.entities.User;
import com.task.taskpro.repos.ProjectRepo;
import com.task.taskpro.repos.UserRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
	@Autowired
	private UserService uservice;
	
	@MockBean
	private UserRepo uRepo;
	
	 
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
	public void testgetAllUser(){
    	User us = new User();
    	us.setUid(189);
    	us.setFirst_name("user 9");
    	us.setLast_name("hari");
    	us.setEmployee_id(125125);
    	List<User> lu = new ArrayList<User>();
    	lu.add(us);
		Mockito.when(uRepo.findAll()).thenReturn(lu);
		Assertions.assertThat(uservice.getAllUser()).isEqualTo(lu);
	}
    
    @Test
	public void testcreateUser(){
    	User us = new User();
    	us.setUid(189);
    	us.setFirst_name("user 9");
    	us.setLast_name("hari");
    	us.setEmployee_id(125125);
		Mockito.when(uRepo.save(us)).thenReturn(us);
		Assertions.assertThat(uservice.createUser(us)).isEqualTo(us);
	}
    
    @Test
	public void testgetUserById(){
    	User us = new User();
    	us.setUid(189);
    	us.setFirst_name("user 9");
    	us.setLast_name("hari");
    	us.setEmployee_id(125125);
    	List<User> lu = new ArrayList<User>();
    	lu.add(us);
    	Optional<User> pya = Optional.of(us);
		Mockito.when(uRepo.findById(189l)).thenReturn(pya);
		Assertions.assertThat(uservice.getUserById(189l)).isEqualTo(pya);
	}
    
    @Test
	public void testupdateUser(){
    	User us = new User();
    	us.setUid(189);
    	us.setFirst_name("user 9");
    	us.setLast_name("hari");
    	us.setEmployee_id(125125);
    	List<User> lu = new ArrayList<User>();
    	lu.add(us);
		Mockito.when(uRepo.updateUser("user 9", "hari", 125125, 189)).thenReturn(0);
		Assertions.assertThat(uservice.updateUser(us, 189l)).isEqualTo(0);
	}
    
    

}
