package org.matt.dev.codes.repository;

import java.util.List;

import org.matt.dev.codes.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface TaskRepository extends MongoRepository<Task, String> {

	List<Task> findBySeverity(Integer severity);

	@Query("{assignee: ?0 }")
	List<Task> findByAssignee(String assignee);
	
	

}
