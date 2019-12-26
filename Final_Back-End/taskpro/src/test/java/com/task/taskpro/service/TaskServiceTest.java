package com.task.taskpro.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.task.taskpro.entities.ParentTask;
import com.task.taskpro.entities.Project;
import com.task.taskpro.entities.Task;
import com.task.taskpro.repos.ProjectRepo;
import com.task.taskpro.repos.TaskRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskServiceTest {
	
	@Autowired
	private TaskService tservice;
	
	@MockBean
	private TaskRepo tRepo;
	
	 
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
	public void testgetAllTasks(){
    	try {
    		Task tsk = new Task();
    		tsk.setTask_ID(180);
    		tsk.setTask("sdfgxcv");
    		tsk.setPriority(6);
    		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2019");
        	Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("27/09/2019");
    		tsk.setStart_Date(date1);
    		tsk.setEnd_Date(date2);
    		ParentTask pt = new ParentTask();
    		pt.setParent_ID(72);
    		pt.setParent_Task("q11");
    		pt.setParent_Pro_ID(1);
    		tsk.setParentTask(pt);
    		tsk.setEnd_Task(false);
    		tsk.setProject_ID(145);
    		tsk.setUser_ID(168);
    		List<Task> ts = new ArrayList<Task>();
    		ts.add(tsk);
			Mockito.when(tRepo.findAll()).thenReturn(ts);
			Assertions.assertThat(tservice.getAllTasks()).isEqualTo(ts);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @Test
	public void testgetTaskByProjectId(){
    	try {
    		Task tsk = new Task();
    		tsk.setTask_ID(180);
    		tsk.setTask("sdfgxcv");
    		tsk.setPriority(6);
    		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2019");
        	Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("27/09/2019");
    		tsk.setStart_Date(date1);
    		tsk.setEnd_Date(date2);
    		ParentTask pt = new ParentTask();
    		pt.setParent_ID(72);
    		pt.setParent_Task("q11");
    		pt.setParent_Pro_ID(1);
    		tsk.setParentTask(pt);
    		tsk.setEnd_Task(false);
    		tsk.setProject_ID(145);
    		tsk.setUser_ID(168);
    		List<Task> ts = new ArrayList<Task>();
    		ts.add(tsk);
			Mockito.when(tRepo.getTaskByProId(145)).thenReturn(ts);
			Assertions.assertThat(tservice.getTaskByProjectId(145)).isEqualTo(ts);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @Test
	public void testgetTaskById(){
    	try {
    		Task tsk = new Task();
    		tsk.setTask_ID(180);
    		tsk.setTask("sdfgxcv");
    		tsk.setPriority(6);
    		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2019");
        	Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("27/09/2019");
    		tsk.setStart_Date(date1);
    		tsk.setEnd_Date(date2);
    		ParentTask pt = new ParentTask();
    		pt.setParent_ID(72);
    		pt.setParent_Task("q11");
    		pt.setParent_Pro_ID(1);
    		tsk.setParentTask(pt);
    		tsk.setEnd_Task(false);
    		tsk.setProject_ID(145);
    		tsk.setUser_ID(168);
    		Optional<Task> pya = Optional.of(tsk);
			Mockito.when(tRepo.findById(180l)).thenReturn(pya);
			Assertions.assertThat(tservice.getTaskById(180l)).isEqualTo(pya);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @Test
	public void testgetNumOfTasksById(){
    	try {
    		Task tsk = new Task();
    		tsk.setTask_ID(180);
    		tsk.setTask("sdfgxcv");
    		tsk.setPriority(6);
    		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2019");
        	Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("27/09/2019");
    		tsk.setStart_Date(date1);
    		tsk.setEnd_Date(date2);
    		ParentTask pt = new ParentTask();
    		pt.setParent_ID(72);
    		pt.setParent_Task("q11");
    		pt.setParent_Pro_ID(1);
    		tsk.setParentTask(pt);
    		tsk.setEnd_Task(false);
    		tsk.setProject_ID(145);
    		tsk.setUser_ID(168);
    		List<Task> ts = new ArrayList<Task>();
    		ts.add(tsk);
			Mockito.when(tRepo.getTaskByProId(145)).thenReturn(ts);
			Assertions.assertThat(tservice.getNumOfTasksById(145)).isEqualTo(ts.size());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @Test
	public void testupdateTaskService(){
    	try {
    		Task tsk = new Task();
    		tsk.setTask_ID(180);
    		tsk.setTask("sdfgxcv");
    		tsk.setPriority(6);
    		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2019");
        	Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("27/09/2019");
    		tsk.setStart_Date(date1);
    		tsk.setEnd_Date(date2);
    		ParentTask pt = new ParentTask();
    		pt.setParent_ID(72);
    		pt.setParent_Task("q11");
    		pt.setParent_Pro_ID(1);
    		tsk.setParentTask(pt);
    		tsk.setEnd_Task(false);
    		tsk.setProject_ID(145);
    		tsk.setUser_ID(168);
    		List<Task> ts = new ArrayList<Task>();
    		ts.add(tsk);
			Mockito.when(tRepo.updateTaskVal("sdfgxcv", 72, 6, date1, date2, 145, 168, 180)).thenReturn(0);
			Assertions.assertThat(tservice.updateTaskService(tsk, 180l)).isEqualTo(0);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @Test
	public void testdisableTaskService(){
			Mockito.when(tRepo.disableTask(180l)).thenReturn(0);
			Assertions.assertThat(tservice.disableTaskService(180l)).isEqualTo(0);
	}
    
    @Ignore
	public void testcreateTask(){
    	try {
    		Task tsk = new Task();
    		tsk.setTask_ID(180);
    		tsk.setTask("sdfgxcv");
    		tsk.setPriority(6);
    		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse("25/09/2019");
        	Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("27/09/2019");
    		tsk.setStart_Date(date1);
    		tsk.setEnd_Date(date2);
    		ParentTask pt = new ParentTask();
    		pt.setParent_ID(72);
    		pt.setParent_Task("q11");
    		pt.setParent_Pro_ID(1);
    		tsk.setParentTask(pt);
    		tsk.setEnd_Task(false);
    		tsk.setProject_ID(145);
    		tsk.setUser_ID(168);
    		List<Task> ts = new ArrayList<Task>();
    		ts.add(tsk);
			Mockito.when(tRepo.save(tsk)).thenReturn(tsk);
			Assertions.assertThat(tservice.createTask(72l, tsk)).isEqualTo(tsk);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
