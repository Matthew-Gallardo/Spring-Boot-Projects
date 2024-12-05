package org.matt.dev.codes.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {
	
	private static final String MESSAGE = "I Love Java!";
	
	@GetMapping(path = "/message",
			produces = { MediaType.APPLICATION_JSON_VALUE ,
					     MediaType.APPLICATION_XML_VALUE })
	public String showMessage() {
		return MESSAGE;
	}
	
}