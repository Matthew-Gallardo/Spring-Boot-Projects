package org.matt.dev.codes.controller;

import java.util.List;

import org.matt.dev.codes.model.Employee;
import org.matt.dev.codes.model.Task;
import org.matt.dev.codes.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    
    @Autowired
    private EmployeeService service;
    
    @GetMapping("/all")
    public List<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }
    
    @GetMapping("/{employeeId}")
    public Employee getEmployeeById(@PathVariable String employeeId) {
        return service.getEmployeeById(employeeId);
    }
    
    @GetMapping("/position/{employeePosition}")
    public List<Employee> getEmployeesByPosition(@PathVariable String employeePosition) {
        return service.getEmployeesByPosition(employeePosition);
    }
    
    @PostMapping
    public Employee addEmployee(@RequestBody Employee employee) {
        return service.addEmployee(employee);
    }
    
    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee) {
        return service.updateEmployee(employee);
    }
    
    @DeleteMapping("/{employeeId}")
    public String deleteEmployee(@PathVariable String employeeId) {
        return service.deleteEmployee(employeeId);
    }
    
    @PostMapping("/{employeeId}/tasks")
    public Employee addTaskToEmployee(@PathVariable String employeeId, @RequestBody Task task) {
        return service.addTaskToEmployee(employeeId, task);
    }
    
    @PutMapping("/{employeeId}/tasks")
    public Employee updateTaskForEmployee(@PathVariable String employeeId, @RequestBody Task task) {
        return service.updateTaskForEmployee(employeeId, task);
    }
    
    @DeleteMapping("/{employeeId}/tasks/{taskId}")
    public Employee removeTaskFromEmployee(@PathVariable String employeeId, @PathVariable String taskId) {
        return service.removeTaskFromEmployee(employeeId, taskId);
    }
}