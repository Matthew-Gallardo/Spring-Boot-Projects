package org.matt.dev.codes.service;

import java.util.List;
import java.util.UUID;

import org.matt.dev.codes.model.Task;
import org.matt.dev.codes.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository repository;
	
	//Create
	
	public Task createTask(Task task){
		task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
		return repository.save(task);
	}
	
	//Select All
	
	public List<Task> getAllTask(){
		return repository.findAll();
	}
	
	//Single Select
	public Task getTaskById(String taskId) {
		return repository.findById(taskId).get();
	}
	
	
	public List<Task> getTaskBySeverity(Integer severity){
		return repository.findBySeverity(severity);
	}
	
	public List<Task> getTaskByAssignee(String assignee ){
		return repository.findByAssignee(assignee);
	}
	
	//Update
	public Task updateTask (Task taskRequest) {
		//get the existing document from mongodb
		Task existingTask = repository.findById(taskRequest.getTaskId()).get();
		existingTask.setAssignee(taskRequest.getAssignee());
		existingTask.setDescription(taskRequest.getDescription());
		existingTask.setSeverity(taskRequest.getSeverity());
		existingTask.setStoryPoint(taskRequest.getStoryPoint());
		//populate new value from request to existing document
		return repository.save(existingTask);
	}
	
	//Delete
	public String deleteTask(String taskId) {
		repository.deleteById(taskId);
		return "Task ID: %s ,Deleted successfully".formatted(taskId);
		
	}
	
	

}
