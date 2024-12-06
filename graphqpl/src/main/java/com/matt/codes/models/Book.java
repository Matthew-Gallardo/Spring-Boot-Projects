package com.matt.codes.models;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public record Book(
		Integer id, 
		String name, 
		Integer pageCount,
		Integer authorId) {
	
	public static List<Book>books = Arrays.asList(
			new Book(1, "CleanCode", 200,1),
			new Book(2, "Spring Book", 120,2),
			new Book(3, "React Book", 300,3),
			new Book(4, "Harry Potter", 300,4),
			new Book(5, "Foo", 100,1));
	
	public static Optional<Book> getBookById(Integer id) {
		return books.stream()
				.filter(b-> b.id.equals(id))
				.findFirst();
	}
			

}
