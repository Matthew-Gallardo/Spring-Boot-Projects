package org.matt.dev.codes.validators;

import org.matt.dev.codes.exception.NoMessageException;
import org.matt.dev.codes.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskValidator {

    public void validate(Task task) {
        if (task.getDescription() == null || task.getDescription().trim().isEmpty()) {
            throw new NoMessageException("Description cannot be blank");
        }
        if (task.getSeverity() == null) {
            throw new NoMessageException("Severity cannot be null");
        }
        if (task.getAssignee() == null || task.getAssignee().trim().isEmpty()) {
            throw new NoMessageException("Assignee cannot be blank");
        }
        if (task.getStoryPoint() == null) {
            throw new NoMessageException("Story point cannot be null");
        }
    }
}