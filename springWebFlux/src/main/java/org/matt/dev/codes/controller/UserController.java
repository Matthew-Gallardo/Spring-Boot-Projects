package org.matt.dev.codes.controller;

import org.matt.dev.codes.dto.UserDTO;
import org.matt.dev.codes.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products/v1")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@GetMapping
	public Flux<UserDTO>users(){
		return service.getAllUser();
	}
	
	@GetMapping("/{id}")
	public Mono<UserDTO>getUserById(@PathVariable String id){
		return service.getUser(id);
	}
	
	@PostMapping()
	public Mono<UserDTO>createUser(@RequestBody Mono<UserDTO>userDtoMono){
		return service.createUser(userDtoMono);
	}
	
	@PutMapping("/update/{id}")
	public Mono<UserDTO>updateUser(@RequestBody Mono<UserDTO>userDtoMono, @PathVariable String id){
		return service.updateUser(userDtoMono, id);
	}
	
	@DeleteMapping("/delete/{id}")
	public Mono<Void>deleteUser(@PathVariable String id){
		return service.deleteUser(id);
	}

}
