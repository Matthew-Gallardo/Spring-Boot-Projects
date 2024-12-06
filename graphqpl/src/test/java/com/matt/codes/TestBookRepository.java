package com.matt.codes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.graphql.test.tester.GraphQlTester;

import com.matt.codes.controller.BookController;
import com.matt.codes.models.Book;

@GraphQlTest(BookController.class)
public class TestBookRepository {
    
    @Autowired
    private GraphQlTester graphQlTester;
    
    @Test
    public void canGetBooks() {
        graphQlTester
            .documentName("books")
            .execute()
            .path("books")
            .entityList(Book.class)
            .hasSize(5);
    }
}