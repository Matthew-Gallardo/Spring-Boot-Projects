package org.matt.dev.codes.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "User")
public class User {
	
	@Id
	private String id;
	private String firstName;
	private String lastName;
	private Integer age;
	private String email;
	private String password;

}
