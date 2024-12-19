package org.matt.dev.codes.validators;

import org.matt.dev.codes.dto.UserDTO;
import org.matt.dev.codes.exception.NoMessageException;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {

    public void validate(UserDTO user) {
        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()) {
            throw new NoMessageException("First name cannot be blank");
        }
        
        if (user.getLastName() == null || user.getLastName().trim().isEmpty()) {
            throw new NoMessageException("Last name cannot be blank");
        }
        
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()) {
            throw new NoMessageException("Email cannot be blank");
        }
        
        if (user.getAge() == null) {
            throw new NoMessageException("Age cannot be blank");
        }
        
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new NoMessageException("Password cannot be blank");
        }
    }
}