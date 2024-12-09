package org.matt.dev.codes.repository;

import java.util.List;

import org.matt.dev.codes.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

	List<Employee> findByPosition(String position);


}
