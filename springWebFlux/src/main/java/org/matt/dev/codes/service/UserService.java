package org.matt.dev.codes.service;

import org.matt.dev.codes.dto.UserDTO;
import org.matt.dev.codes.kafka.KafkaProducer;
import org.matt.dev.codes.repository.UserRepository;
import org.matt.dev.codes.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository repository;
    
    @Autowired
    private KafkaProducer kafkaProducer;

    private static final String TOPIC = "user_topic";

    public Flux<UserDTO> getAllUser() {
        log.info("Fetching all users");


        return repository.findAll()
                .map(AppUtils::entityToDto)
                .doOnComplete(() -> log.info("Fetched all users successfully"))
                .doOnError(e -> log.error("Error fetching all users", e))
                .doOnTerminate(() -> repository.findAll().count()
                    .doOnNext(count -> log.info("Total number of users fetched: {}", count))
                    .subscribe());

    }

    public Mono<UserDTO> getUser(String id) {
        log.info("Fetching user with id: {}", id);
        return repository.findById(id)
                .map(AppUtils::entityToDto)
                .doOnSuccess(user -> log.info("Fetched user with id: {}", id))
                .doOnError(e -> log.error("Error fetching user with id: {}", id, e));
    }

    public Mono<UserDTO> createUser(Mono<UserDTO> userDtoMono) {
        log.info("Creating new user");

        return userDtoMono
                .map(AppUtils::dtoToEntity)
                .flatMap(repository::insert)
                .map(AppUtils::entityToDto)
                .doOnNext(userDTO -> {
                    log.info("Created user: {}", userDTO);
                    kafkaProducer.sendMessage(TOPIC, userDTO);
                })
                .doOnError(e -> log.error("Error creating user", e));
    }

    public Mono<UserDTO> updateUser(Mono<UserDTO> userDtoMono, String id) {
        log.info("Updating user with id: {}", id);
        return repository.findById(id)
                .flatMap(u -> userDtoMono.map(AppUtils::dtoToEntity)
                .doOnNext(e -> e.setId(id)))
                .flatMap(repository::save)
                .map(AppUtils::entityToDto)
                .doOnNext(userDTO -> {
                    log.info("Updated user with id: {}", id);
                    kafkaProducer.sendMessage(TOPIC, userDTO);
                })
                .doOnError(e -> log.error("Error updating user with id: {}", id, e));
    }
    
    public Mono<Void> deleteUser(String id){
        log.info("Deleting user with id: {}", id);
        return repository.deleteById(id)
                .doOnSuccess(unused -> log.info("Deleted user with id: {}", id))
                .doOnError(e -> log.error("Error deleting user with id: {}", id, e));
    }
}