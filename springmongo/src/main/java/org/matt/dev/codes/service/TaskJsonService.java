package org.matt.dev.codes.service;

import java.util.List;
import java.util.UUID;

import org.matt.dev.codes.kafka.KafkaJsonProducer;
import org.matt.dev.codes.model.Task;
import org.matt.dev.codes.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TaskJsonService {

    @Autowired
    private TaskRepository repository;

    @Autowired
    private KafkaJsonProducer kafkaJsonProducer;

    private static final String TOPIC = "task_json_topic";
    // Create
    public Task createTask(Task task) {
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        Task savedTask = repository.save(task);
        kafkaJsonProducer.sendMessage(TOPIC, savedTask);
        log.info("Created Task with ID: {}", savedTask.getTaskId());
        return savedTask;
    }

    // Select All
    public List<Task> getAllTask() {
        List<Task> allTasks = repository.findAll();
        log.info("Retrieved all tasks, count: {}", allTasks.size());
        return allTasks;
    }

    // Single Select
    public Task getTaskById(String taskId) {
        Task task = repository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task with ID %s not found.".formatted(taskId)));
        log.info("Retrieved Task with ID: {}", taskId);
        return task;
    }

    public List<Task> getTaskBySeverity(Integer severity) {
        List<Task> allTaskBySeverity = repository.findBySeverity(severity);
        log.info("Retrieved tasks with severity: {}, count: {}", severity, allTaskBySeverity.size());
        return allTaskBySeverity;
    }

    public List<Task> getTaskByAssignee(String assignee) {
        List<Task> allTaskByAssignee = repository.findByAssignee(assignee);
        log.info("Retrieved tasks assigned to: {}, count: {}", assignee, allTaskByAssignee.size());
        return allTaskByAssignee;
    }

    // Update
    public Task updateTask(Task taskRequest) {
        Task existingTask = repository.findById(taskRequest.getTaskId()).orElse(null);
        if (existingTask == null) {
            log.warn("Task with ID: {} not found for update", taskRequest.getTaskId());
            return null;
        }

        existingTask.setAssignee(taskRequest.getAssignee());
        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setSeverity(taskRequest.getSeverity());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());

        Task updatedTask = repository.save(existingTask);
        kafkaJsonProducer.sendMessage(TOPIC, updatedTask);
        log.info("Updated Task with ID: {}", updatedTask.getTaskId());
        return updatedTask;
    }

    // Delete
    public String deleteTask(String taskId) {
        repository.deleteById(taskId);
        kafkaJsonProducer.sendMessage(TOPIC, new Task(taskId, null, null, null, null));
        log.info("Deleted Task with ID: {}", taskId);
        return "Task ID: %s ,Deleted successfully".formatted(taskId);
    }
}