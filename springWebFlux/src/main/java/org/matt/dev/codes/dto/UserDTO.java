package org.matt.dev.codes.dto;

import lombok.*;

@Getter
@Setter
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
