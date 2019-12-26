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

import com.task.taskpro.entities.ParentTask;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ParentTaskRepoTest {
	
	@MockBean
	private ParentTaskRepo ptRepo;
	
	 
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
	public void testgetPTByProjectId(){
    	//try {
			ParentTask pt = new ParentTask();
			pt.setParent_ID(137);
			pt.setParent_Task("qaw24");
			pt.setParent_Pro_ID(2);
			List<ParentTask> ptarray = new ArrayList<ParentTask>();
			ptarray.add(pt);
			List<ParentTask> ptarray1 = new ArrayList<ParentTask>();
			Assertions.assertThat(ptRepo.getPTByProjectId(2)).isEqualTo(ptarray1);
	}
}
