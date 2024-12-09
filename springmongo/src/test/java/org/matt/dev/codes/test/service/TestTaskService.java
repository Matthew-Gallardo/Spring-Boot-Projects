package org.matt.dev.codes.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.matt.dev.codes.model.Task;
import org.matt.dev.codes.repository.TaskRepository;
import org.matt.dev.codes.service.TaskService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@Rollback(true)
@ExtendWith(MockitoExtension.class)
public class TestTaskService {
	@Mock
    private TaskRepository repository;

    @InjectMocks
    private TaskService service;

    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task(UUID.randomUUID().toString().split("-")[0], 
        		        "Test Task Description", 1, "Test Assignee", 5);
    }

    @Test
    public void testCreateTask() {
        when(repository.save(any(Task.class))).thenReturn(task);
        Task createdTask = service.createTask(new Task());
        assertNotNull(createdTask);
        assertEquals(task.getTaskId(), createdTask.getTaskId());
    }

    @Test
    public void testGetAllTask() {
        when(repository.findAll()).thenReturn(Arrays.asList(task));
        List<Task> tasks = service.getAllTask();
        assertNotNull(tasks);
        assertEquals(1, tasks.size());
    }

    @Test
    public void testGetTaskById() {
        when(repository.findById(task.getTaskId())).thenReturn(Optional.of(task));
        Task foundTask = service.getTaskById(task.getTaskId());
        assertNotNull(foundTask);
        assertEquals(task.getTaskId(), foundTask.getTaskId());
    }

    @Test
    public void testGetTaskBySeverity() {
        when(repository.findBySeverity(task.getSeverity())).thenReturn(Arrays.asList(task));
        List<Task> tasks = service.getTaskBySeverity(task.getSeverity());
        assertNotNull(tasks);
        assertEquals(1, tasks.size());
    }

    @Test
    public void testGetTaskByAssignee() {
        when(repository.findByAssignee(task.getAssignee())).thenReturn(Arrays.asList(task));
        List<Task> tasks = service.getTaskByAssignee(task.getAssignee());
        assertNotNull(tasks);
        assertEquals(1, tasks.size());
    }

    @Test
    public void testUpdateTask() {
        when(repository.findById(task.getTaskId())).thenReturn(Optional.of(task));
        when(repository.save(any(Task.class))).thenReturn(task);
        Task updatedTask = service.updateTask(task);
        assertNotNull(updatedTask);
        assertEquals(task.getTaskId(), updatedTask.getTaskId());
    }

    @Test
    public void testDeleteTask() {
        String taskId = task.getTaskId();
        String result = service.deleteTask(taskId);
        assertEquals("Task ID: %s ,Deleted successfully".formatted(taskId), result);
    }
	

}
