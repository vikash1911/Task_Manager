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

import com.task.taskpro.entities.ParentTask;
import com.task.taskpro.entities.Project;
import com.task.taskpro.repos.ProjectRepo;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectServiceTest {
	
	@Autowired
	private ProjectService ptservice;
	
	@MockBean
	private ProjectRepo ptRepo;
	
	 
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
	public void testgetAllProjects(){
    	try {
    	Project pj = new Project();
    	pj.setPid(174);
    	Date date1=new SimpleDateFormat("dd/MM/yyyy").parse("26/09/2019");
    	pj.setPro_start_date(date1);
    	Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("26/09/2019");
    	pj.setPro_end_date(date2);
    	pj.setPro_priority(7);
    	pj.setPro_user_id(165);
    	pj.setProject("pro17");
    	List<Project> pro = new ArrayList<Project>();
    	pro.add(pj);
		Mockito.when(ptRepo.findAll()).thenReturn(pro);
		Assertions.assertThat(ptservice.getAllProjects()).isEqualTo(pro);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
    @Test
	public void testcreateProject(){
    	try {
        	Project pj = new Project();
        	pj.setPid(174);
        	Date date1=new SimpleDateFormat("dd/MM/yyyy").parse("26/09/2019");
        	pj.setPro_start_date(date1);
        	Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("26/09/2019");
        	pj.setPro_end_date(date2);
        	pj.setPro_priority(7);
        	pj.setPro_user_id(165);
        	pj.setProject("pro17");
    		Mockito.when(ptRepo.save(pj)).thenReturn(pj);
    		Assertions.assertThat(ptservice.createProject(pj)).isEqualTo(pj);
    		} catch (ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}

    }
    
    @Test
	public void testgetProjectById(){
    	try {
	    	Project pj = new Project();
	    	pj.setPid(174);
	    	Date date1=new SimpleDateFormat("dd/MM/yyyy").parse("26/09/2019");
	    	pj.setPro_start_date(date1);
	    	Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("26/09/2019");
	    	pj.setPro_end_date(date2);
	    	pj.setPro_priority(7);
	    	pj.setPro_user_id(165);
	    	pj.setProject("pro17");
	    	Optional<Project> pya = Optional.of(pj);
			Mockito.when(ptRepo.findById(174l)).thenReturn(pya);
			Assertions.assertThat(ptservice.getProjectById(174l)).isEqualTo(pya);
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @Test
	public void testupdateProject(){
    	try {
    		Project pj = new Project();
	    	pj.setPid(174);
	    	Date date1=new SimpleDateFormat("dd/MM/yyyy").parse("26/09/2019");
	    	pj.setPro_start_date(date1);
	    	Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("26/09/2019");
	    	pj.setPro_end_date(date2);
	    	pj.setPro_priority(7);
	    	pj.setPro_user_id(165);
	    	pj.setProject("pro17");
			Mockito.when(ptRepo.updateProject("pro17", date1, date2, 7, 165, 174)).thenReturn(0);
			Assertions.assertThat(ptservice.updateProject(174l, pj)).isEqualTo(0);
			} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
