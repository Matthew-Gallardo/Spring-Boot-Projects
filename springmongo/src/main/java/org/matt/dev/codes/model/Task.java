package org.matt.dev.codes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tasks")
public class Task {
	@Id
	private String taskId;
	private String description;
	private Integer severity;
	private String assignee;
	private Integer storyPoint;
	
	public Task() {
		// TODO Auto-generated constructor stub
	}
	
	public Task(String taskId, String description, Integer severity, String assignee, Integer storyPoint) {
		super();
		this.taskId = taskId;
		this.description = description;
		this.severity = severity;
		this.assignee = assignee;
		this.storyPoint = storyPoint;
	}

	public String getTaskId() {
		return taskId;
	}
	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getSeverity() {
		return severity;
	}
	public void setSeverity(Integer severity) {
		this.severity = severity;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public Integer getStoryPoint() {
		return storyPoint;
	}
	public void setStoryPoint(Integer storyPoint) {
		this.storyPoint = storyPoint;
	}

	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", description=" + description + ", severity=" + severity + ", assignee="
				+ assignee + ", storyPoint=" + storyPoint + "]";
	}
	
	
	
	
}
