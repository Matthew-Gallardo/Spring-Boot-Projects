package org.matt.dev.codes.controller;

import org.matt.dev.codes.dto.UserDTO;
import org.matt.dev.codes.service.UserService;
import org.matt.dev.codes.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/user/v1")
public class UserController {
    
    @Autowired
    private UserService service;

    @Autowired
    private UserValidator validator;
    
    @GetMapping
    public Flux<UserDTO> users() {
        return service.getAllUser();
    }
    
    @GetMapping("/{id}")
    public Mono<UserDTO> getUserById(@PathVariable String id) {
        return service.getUser(id);
    }
    
    @PostMapping
    public Mono<UserDTO> createUser(@RequestBody Mono<UserDTO> userDtoMono) {
        return userDtoMono
                .doOnNext(validator::validate)
                .flatMap(userDto -> service.createUser(Mono.just(userDto)));
    }
    
    @PutMapping("/update/{id}")
    public Mono<UserDTO> updateUser(@RequestBody Mono<UserDTO> userDtoMono, @PathVariable String id) {
        return userDtoMono
                .doOnNext(validator::validate)
                .flatMap(userDto -> service.updateUser(Mono.just(userDto), id));
    }
    
    @DeleteMapping("/delete/{id}")
    public Mono<Void> deleteUser(@PathVariable String id) {
        return service.deleteUser(id);
    }

    @PostMapping("/bulk")
    public Flux<UserDTO> createUsersBulk(@RequestBody List<UserDTO> userDtos) {
        userDtos.forEach(validator::validate);
        return Flux.fromIterable(userDtos)
                   .flatMap(userDto -> service.createUser(Mono.just(userDto)));
    }
    
    @PostMapping("/generate/{count}")
    public Flux<UserDTO> generateUsers(@PathVariable int count) {
        List<UserDTO> userDtos = IntStream.range(0, count)
                                          .mapToObj(i -> {
                                              UserDTO user = new UserDTO();
                                              user.setId(UUID.randomUUID().toString());
                                              user.setFirstName("FirstName" + i);
                                              user.setLastName("LastName" + i);
                                              user.setAge(20 + (i % 30));
                                              user.setEmail("user" + i + "@example.com");
                                              user.setPassword("password" + i);
                                              return user;
                                          })
                                          .collect(Collectors.toList());
        return createUsersBulk(userDtos);
    }
}