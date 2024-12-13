package org.matt.dev.codes.entity;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Document(collection = "User")
public class User {
	
	private String firstName;
	private String lastName;
	private Integer age;
	private String email;
	private String password;

}
