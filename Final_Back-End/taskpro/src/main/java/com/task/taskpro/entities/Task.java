package com.task.taskpro.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "task")
public class Task implements Serializable{

	@Column(name = "Task_ID", nullable = false, length = 10)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long Task_ID;
	
	@Column(name = "Task")
	@NotBlank(message = "Enter a title ")
	private String Task;
	
	@Column(name = "Start_Date")
	private Date Start_Date;
	
	@Column(name = "End_Date")
	private Date End_Date;
	
	@Column(name = "Priority")
	private int Priority;
	
	@Column(name = "End_Task")
	private boolean End_Task;
	
	@Column(name = "Project_ID")
	private int Project_ID;
	
	@Column(name = "User_ID")
	private int User_ID;
	
	public int getProject_ID() {
		return Project_ID;
	}

	public void setProject_ID(int project_ID) {
		Project_ID = project_ID;
	}

	public int getUser_ID() {
		return User_ID;
	}

	public void setUser_ID(int user_ID) {
		User_ID = user_ID;
	}

	public boolean isEnd_Task() {
		return End_Task;
	}

	public void setEnd_Task(boolean end_Task) {
		End_Task = end_Task;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_Id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ParentTask parentTask;
	
	public Task(){
		
	}

	public Task(long task_ID, String task) {
		super();
		Task_ID = task_ID;
		Task = task;
	}

	public long getTask_ID() {
		return Task_ID;
	}
	public void setTask_ID(long task_ID) {
		Task_ID = task_ID;
	}
	public String getTask() {
		return Task;
	}
	public void setTask(String task) {
		Task = task;
	}
	public Date getStart_Date() {
		return Start_Date;
	}
	public void setStart_Date(Date start_Date) {
		Start_Date = start_Date;
	}
	public Date getEnd_Date() {
		return End_Date;
	}
	public void setEnd_Date(Date end_Date) {
		End_Date = end_Date;
	}
	public int getPriority() {
		return Priority;
	}
	public void setPriority(int priority) {
		Priority = priority;
	}
	
	public Long getParentTask_id(){
        return parentTask.getParent_ID();
    }

    public String getParentTaskName(){
        return parentTask.getParent_Task();
    }

    @JsonIgnore
    public ParentTask getParentTask() {
        return parentTask;
    }

    @JsonIgnore
    public void setParentTask(ParentTask parentTask) {
        this.parentTask = parentTask;
    }
	
	
}
