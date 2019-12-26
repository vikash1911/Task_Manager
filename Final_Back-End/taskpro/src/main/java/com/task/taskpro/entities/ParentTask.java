package com.task.taskpro.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "parent_task")
public class ParentTask implements Serializable {

	@Column(name = "Parent_ID", nullable = false, length = 10)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long Parent_ID;
	
	@Column(name = "Parent_Task")
	public String Parent_Task;
	
	@Column(name = "Parent_Pro_ID")
	private int Parent_Pro_ID;
	
	@OneToMany(mappedBy = "parentTask", fetch = FetchType.LAZY)
    private Set<Task> tasks = new HashSet<>();

	public ParentTask(long parent_ID, String parent_Task) {
		super();
		Parent_ID = parent_ID;
		Parent_Task = parent_Task;
	}
	
	public ParentTask(){
		
	}

	public long getParent_ID() {
		return Parent_ID;
	}

	public void setParent_ID(long parent_ID) {
		Parent_ID = parent_ID;
	}

	public int getParent_Pro_ID() {
		return Parent_Pro_ID;
	}

	public void setParent_Pro_ID(int parent_Pro_ID) {
		Parent_Pro_ID = parent_Pro_ID;
	}

	public String getParent_Task() {
		return Parent_Task;
	}

	public void setParent_Task(String parent_Task) {
		Parent_Task = parent_Task;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	
	
}
