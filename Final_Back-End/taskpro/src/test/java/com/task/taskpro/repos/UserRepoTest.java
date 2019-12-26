package com.task.taskpro.repos;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.task.taskpro.entities.User;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepoTest {
	@MockBean
	private UserRepo ptRepo;
	
	 
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
	public void testupdateUser(){
    	User us = new User();
    	us.setUid(189);
    	us.setFirst_name("user 9");
    	us.setLast_name("hari");
    	us.setEmployee_id(125125);
    	Assertions.assertThat(ptRepo.updateUser("user 9", "hari", 125125, 189)).isEqualTo(0);
	}

}
