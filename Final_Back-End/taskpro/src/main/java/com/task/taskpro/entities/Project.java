package com.task.taskpro.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "project")
public class Project implements Serializable {

	@Column(name = "pid", nullable = false, length = 10)
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long pid;
	
	@Column(name = "project")
	@NotBlank(message = "Enter a title ")
	private String project;
	
	@Column(name = "pro_start_date")
	private Date pro_start_date;
	
	@Column(name = "pro_end_date")
	private Date pro_end_date;
	
	@Column(name = "pro_priority")
	private int pro_priority;
	
	@Column(name = "pro_user_id")
	private int pro_user_id;

	public long getPid() {
		return pid;
	}

	public void setPid(long pid) {
		this.pid = pid;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public Date getPro_start_date() {
		return pro_start_date;
	}

	public void setPro_start_date(Date pro_start_date) {
		this.pro_start_date = pro_start_date;
	}

	public Date getPro_end_date() {
		return pro_end_date;
	}

	public void setPro_end_date(Date pro_end_date) {
		this.pro_end_date = pro_end_date;
	}

	public int getPro_priority() {
		return pro_priority;
	}

	public void setPro_priority(int pro_priority) {
		this.pro_priority = pro_priority;
	}

	public int getPro_user_id() {
		return pro_user_id;
	}

	public void setPro_user_id(int pro_user_id) {
		this.pro_user_id = pro_user_id;
	}
	
	
	
}
