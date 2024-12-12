package org.matt.dev.codes.service;

import java.util.List;
import java.util.UUID;

import org.matt.dev.codes.model.Employee;
import org.matt.dev.codes.model.Task;
import org.matt.dev.codes.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
  

    @Autowired
    private EmployeeRepository repository;

    public Employee addEmployee(Employee employee) {
        employee.setEmployeeId(UUID.randomUUID().toString().split("-")[0]);
        Employee savedEmployee = repository.save(employee);
       
        return savedEmployee;
    }

    public List<Employee> getAllEmployees() {
        List<Employee> employees = repository.findAll();
     
        return employees;
    }

    public Employee getEmployeeById(String employeeId) {
        Employee employee = repository.findById(employeeId)
                .orElseThrow(() -> new IllegalArgumentException("Employee with ID " + employeeId + " not found."));
        
        return employee;
    }

    public List<Employee> getEmployeesByPosition(String position) {
        List<Employee> employees = repository.findByPosition(position);
    
        return employees;
    }

    public Employee updateEmployee(Employee employee) {
        Employee existingEmployee = repository.findById(employee.getEmployeeId())
                .orElseThrow(() -> new IllegalArgumentException("Employee with ID " + employee.getEmployeeId() + " not found."));
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setBirthDate(employee.getBirthDate());
        existingEmployee.setAge(employee.getAge());
        existingEmployee.setPosition(employee.getPosition());
        Employee updatedEmployee = repository.save(existingEmployee);
        
        return updatedEmployee;
    }

    public String deleteEmployee(String employeeId) {
        repository.deleteById(employeeId);
      
        return "Employee ID: %s ,Deleted successfully".formatted(employeeId);
    }

    public Employee addTaskToEmployee(String employeeId, Task task) {
        Employee employee = getEmployeeById(employeeId);
        employee.getTasks().add(task);
        Employee updatedEmployee = repository.save(employee);
       
        return updatedEmployee;
    }

    public Employee removeTaskFromEmployee(String employeeId, String taskId) {
        Employee employee = getEmployeeById(employeeId);
        employee.getTasks().removeIf(task -> task.getTaskId().equals(taskId));
        Employee updatedEmployee = repository.save(employee);

        return updatedEmployee;
    }

    public Employee updateTaskForEmployee(String employeeId, Task updatedTask) {
        Employee employee = getEmployeeById(employeeId);
        employee.getTasks().stream()
                .filter(task -> task.getTaskId().equals(updatedTask.getTaskId()))
                .forEach(task -> {
                    task.setDescription(updatedTask.getDescription());
                    task.setSeverity(updatedTask.getSeverity());
                    task.setAssignee(updatedTask.getAssignee());
                    task.setStoryPoint(updatedTask.getStoryPoint());
                });
        Employee updatedEmployee = repository.save(employee);
        return updatedEmployee;
    }
}