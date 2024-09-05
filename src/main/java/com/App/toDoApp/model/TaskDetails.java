package com.App.toDoApp.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "task_manager")
@Component
public class TaskDetails {
  
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String taskTitle;

	private String taskDesc;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date taskDate;

	private String flag;
	
	private String taskby;
	
	private int userid;

	public TaskDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TaskDetails(int id, String taskTitle, String taskDesc, Date taskDate, String flag, String taskby,
			int userid) {
		super();
		this.id = id;
		this.taskTitle = taskTitle;
		this.taskDesc = taskDesc;
		this.taskDate = taskDate;
		this.flag = flag;
		this.taskby = taskby;
		this.userid = userid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskDesc() {
		return taskDesc;
	}

	public void setTaskDesc(String taskDesc) {
		this.taskDesc = taskDesc;
	}

	public Date getTaskDate() {
		return taskDate;
	}

	public void setTaskDate(Date taskDate) {
		this.taskDate = taskDate;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getTaskby() {
		return taskby;
	}

	public void setTaskby(String taskby) {
		this.taskby = taskby;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	@Override
	public String toString() {
		return "TaskDetails [id=" + id + ", taskTitle=" + taskTitle + ", taskDesc=" + taskDesc + ", taskDate="
				+ taskDate + ", flag=" + flag + ", taskby=" + taskby + ", userid=" + userid + "]";
	}

	
	
	
}
