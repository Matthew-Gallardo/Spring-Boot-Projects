package org.matt.dev.codes.controller;

import java.util.List;

import org.matt.dev.codes.model.Task;
import org.matt.dev.codes.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	@Autowired
	private TaskService service;
	
	@PostMapping
	public Task createTask(@RequestBody Task task) {
		return service.createTask(task);
	}
	
	@GetMapping("/all")
	public List<Task> getTasks(){
		return service.getAllTask();
	}
	
	@GetMapping("/{taskId}")
	public Task getTask(@PathVariable String taskId) {
		return service.getTaskById(taskId);
	}
	
	@GetMapping("/severity/{severity}")
	public List<Task> findTasksBySeverity(@PathVariable Integer severity){
		return service.getTaskBySeverity(severity);
	}
	
	@GetMapping("/assignee/{assignee}")
	public List<Task> findTasksByAssignee(@PathVariable String assignee){
		return service.getTaskByAssignee(assignee);
	}
	
	@PutMapping
	public Task updateTask(@RequestBody Task task) {
		return service.updateTask(task);
	}
	
	@DeleteMapping("/{taskId}")
	public String deleteTask(@PathVariable String taskId) {
		return service.deleteTask(taskId);
	}
	
	

}
