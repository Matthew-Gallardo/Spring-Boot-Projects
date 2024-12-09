package org.matt.dev.codes.service;

import java.util.List;
import java.util.UUID;

import org.matt.dev.codes.model.Employee;
import org.matt.dev.codes.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	public Employee addEmployee(Employee employee) {
		employee.setEmployeeId(UUID.randomUUID().toString().split("-")[0]);
		return repository.save(employee);
	}

	public List<Employee> getAllEmployees() {
		return repository.findAll();
	}

	public Employee getTaskByEmployee(String employeeId) {
		return repository.findById(employeeId).get();
	}

	public List<Employee> getTaskByPosition(String position) {
		return repository.findByPosition(position);
	}

	
	public Employee updateEmployee (Employee employee) {
		Employee existingEmployee = repository.findById(employee.getEmployeeId())
				 .orElseThrow(
			        () -> new IllegalArgumentException("Employee with ID " + employee.getEmployeeId() + " not found.")
			    );
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setBirthDate(employee.getBirthDate());
		existingEmployee.setAge(employee.getAge());
		existingEmployee.setPosition(employee.getPosition());
		
		return repository.save(existingEmployee);
	}


	public String deleteEmployee(String employeeId) {
		repository.deleteById(employeeId);
		return "Employee ID: %s ,Deleted successfully".formatted(employeeId);

	}

}
