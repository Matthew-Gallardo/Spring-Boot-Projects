package com.matt.codes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.matt.codes.models.Author;
import com.matt.codes.models.Book;

@Controller
public class BookController {
	
	@QueryMapping
	public List<Book>books(){
		return Book.books;
	}
	
    @QueryMapping
    public Optional<Book> bookById(@Argument("id")Integer id) {
        return Book.getBookById(id);
    }
    
    @SchemaMapping
    public Optional<Author> author(Book book) {
        return Author.getAuthorById(book.authorId());
    }
    

}
