package com.task.taskpro.service;

import java.util.ArrayList;
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

import com.task.taskpro.entities.ParentTask;
import com.task.taskpro.repos.ParentTaskRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParentTaskServiceTest {
	
	@Autowired
	private ParentTaskService ptservice;
	
	@MockBean
	private ParentTaskRepo ptRepo;
	
	 
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
	public void testgetParentTaskVal(){
		ParentTask pt = new ParentTask();
		pt.setParent_ID(93);
		pt.setParent_Task("qqwwee");
		pt.setParent_Pro_ID(1);
		List<ParentTask> ptarray = new ArrayList<ParentTask>();
		ptarray.add(pt);
		Mockito.when(ptRepo.findAll()).thenReturn(ptarray);
		Assertions.assertThat(ptservice.getParentTaskVal()).isEqualTo(ptarray);
	}
    
    @Test
	public void testcreateParentTask(){
    	ParentTask pt = new ParentTask();
		pt.setParent_ID(93);
		pt.setParent_Task("qqwwee");
		pt.setParent_Pro_ID(1);
		Mockito.when(ptRepo.save(pt)).thenReturn(pt);
		Assertions.assertThat(ptservice.createParentTask(pt)).isEqualTo(pt);
	}
    
    @Test
	public void testgetParentTaskById(){
    	ParentTask pt = new ParentTask();
		pt.setParent_ID(93);
		pt.setParent_Task("qqwwee");
		pt.setParent_Pro_ID(1);
		Optional<ParentTask> pya = Optional.of(pt);
		Mockito.when(ptRepo.findById(93l)).thenReturn(pya);
		Assertions.assertThat(ptservice.getParentTaskById(93l)).isEqualTo(pya);
	}
    
    @Test
	public void testgetParentTaskByProjectId(){
    	ParentTask pt = new ParentTask();
		pt.setParent_ID(93);
		pt.setParent_Task("qqwwee");
		pt.setParent_Pro_ID(1);
		List<ParentTask> ptarray = new ArrayList<ParentTask>();
		ptarray.add(pt);
		Mockito.when(ptRepo.getPTByProjectId(1)).thenReturn(ptarray);
		Assertions.assertThat(ptservice.getParentTaskByProjectId(1)).isEqualTo(ptarray);
	}

}
