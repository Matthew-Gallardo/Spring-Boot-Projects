package org.matt.dev.codes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "tasks")
@Data
@NoArgsConstructor
public class Task {
	@Id
	private String taskId;
	private String description;
	private Integer severity;
	private String assignee;
	private Integer storyPoint;
	
	@DBRef
	@JsonIgnore
	private Employee employee;
	
	
	public Task(String taskId, String description, Integer severity, String assignee, Integer storyPoint) {
		super();
		this.taskId = taskId;
		this.description = description;
		this.severity = severity;
		this.assignee = assignee;
		this.storyPoint = storyPoint;
	}

	
	
	
}
