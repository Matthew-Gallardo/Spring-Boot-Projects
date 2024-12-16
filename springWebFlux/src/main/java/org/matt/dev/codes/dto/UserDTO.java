package org.matt.dev.codes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private String id;
	private String firstName;
	private String lastName;
	private Integer age;
	private String email;
	private String password;

}
