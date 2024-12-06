package com.matt.codes.models;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public record Author(Integer id,
		String name) {
	
	public static List<Author> authors = Arrays.asList(
			new Author(1, "Kai Sotto"),
			new Author(2, "Lebron James"),
			new Author(3, "Carmelo Anthony"),
			new Author(4, "Victor Wenbanyama"));
	
	
	public static Optional<Author> getAuthorById(Integer id) {
		return authors.stream()
				.filter(b-> b.id.equals(id))
				.findFirst();
	}
			
	
}



