package org.matt.dev.codes.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "employees")
@Data
@NoArgsConstructor
public class Employee {
    
    @Id
    private String employeeId;
    private String firstName;
    private String lastName;
    private Integer age;
    private LocalDate birthDate;
    private String position;
    
    @DBRef
    private List<Task> tasks = new ArrayList<>();
    
    public Employee(String employeeId, String firstName, String lastName, Integer age, LocalDate birthDate, String position) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
        this.position = position;
        this.tasks = new ArrayList<>();
    }

}