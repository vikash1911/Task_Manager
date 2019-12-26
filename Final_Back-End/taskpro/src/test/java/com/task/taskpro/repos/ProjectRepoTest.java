package com.task.taskpro.repos;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.task.taskpro.entities.Project;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectRepoTest {
	
	@MockBean
	private ProjectRepo ptRepo;
	
	 
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
	public void testupdateProject(){
    	try {
		Project pt = new Project();
		Date date1=new SimpleDateFormat("dd/MM/yyyy").parse("26/09/2019");
    	Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse("28/09/2019");
		pt.setPid(190);
		pt.setPro_start_date(date1);
		pt.setPro_end_date(date2);
		pt.setPro_priority(9);
		pt.setPro_user_id(170);
		pt.setProject("pro19");
		int aa = ptRepo.updateProject("pro19", date1, date2, 
				9, 170, 190);
		Assertions.assertThat(ptRepo.updateProject("pro19", date1, date2, 
				9, 170, 190)).isEqualTo(0);
    	} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
