package com.microservice.account.model;

import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.microservice.account.enums.JobTitle;

@Entity
public class Employee {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String city;
	private double salary;
	
	@Enumerated(EnumType.STRING)
	private JobTitle jobTitle; 
	
	@OneToOne
	private UserInfo userInfo;
	
	@ManyToOne
	private Manager manager; 
	
	@Column(columnDefinition = "LONGTEXT")
	private String dailyLog;
	
	@Column(columnDefinition = "LONGTEXT")
	private String messages;
	
	@Column(columnDefinition = "MEDIUMTEXT")
	private String notifications;
	
	/*@OneToMany(mappedBy = "employee")
	private List<Task> tasks;*/

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public JobTitle getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(JobTitle jobTitle) {
		this.jobTitle = jobTitle;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public String getDailyLog() {
		return dailyLog;
	}

	public void setDailyLog(String dailyLog) {
		this.dailyLog = dailyLog;
	}

	public String getMessages() {
		return messages;
	}

	public void setMessages(String messages) {
		this.messages = messages;
	}

	public String getNotifications() {
		return notifications;
	}

	public void setNotifications(String notifications) {
		this.notifications = notifications;
	}

	/*public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}*/

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", city=" + city + ", salary=" + salary + ", jobTitle="
				+ jobTitle + ", userInfo=" + userInfo + ", manager=" + manager + ", dailyLog=" + dailyLog
				+ ", messages=" + messages + ", notifications=" + notifications + "]";
	}
	
	
	
}
