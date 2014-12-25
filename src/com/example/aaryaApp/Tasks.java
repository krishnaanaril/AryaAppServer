package com.example.aaryaApp;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;

@Entity
public class Tasks {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String from;
	private String to;
	private String taskTitle;
	private String taskDate;
	private String taskDueDate;
	private String taskLocalId;
	private Long taskCreatedDate;
	
	public Tasks() {}
	
	public Tasks(String _taskLocalId, String _from, String _to, String _taskTitle, String _taskDate, String _taskDueDate, Long _taskCreatedDate ) {
		this.from=_from;
		this.to=_to;
		this.taskTitle=_taskTitle;
		this.taskDate=_taskDate;
		this.taskDueDate=_taskDueDate;
		this.taskLocalId=_taskLocalId;
		this.taskCreatedDate=_taskCreatedDate;
	}
	
	public static Tasks find(Long createdDate, EntityManager em) {
		Query q = em.createQuery("select c from Tasks c where c.taskCreatedDate = :createdDate");
		q.setParameter("createdDate", createdDate);
		List<Tasks> result = q.getResultList();
		
		if (!result.isEmpty()) {
			return result.get(0);
		}
		return null;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getTaskLocalId() {
		return taskLocalId;
	}
	public void setTaskLocalId(String _taskLocalId) {
		this.taskLocalId = _taskLocalId;
	}
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String _from) {
		this.from = _from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String _to) {
		this.to = _to;
	}
	
	public String getTaskTitle() {
		return taskTitle;
	}
	public void setTaskTitle(String _taskTitle) {
		this.taskTitle = _taskTitle;
	}
	
	public String getTaskDate() {
		return taskDate;
	}
	public void setTaskDate(String _taskDate) {
		this.taskDate = _taskDate;
	}
	
	public String getTaskDueDate() {
		return taskDueDate;
	}
	public void setTaskDueDate(String _taskDueDate) {
		this.taskDueDate = _taskDueDate;
	}
	
	public Long getTaskCreatedDate() {
		return taskCreatedDate;
	}
}
