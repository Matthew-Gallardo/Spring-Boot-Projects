package org.matt.dev.codes.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.matt.dev.codes.dto.UserDTO;
import org.matt.dev.codes.entity.User;
import org.matt.dev.codes.kafka.KafkaProducer;
import org.matt.dev.codes.repository.UserRepository;
import org.matt.dev.codes.utils.AppUtils;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.annotation.Rollback;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Rollback(true)
@ExtendWith(MockitoExtension.class)
public class TestUserService {

    @Mock
    private UserRepository repository;

    @Mock
    private KafkaProducer kafkaProducer;

    @InjectMocks
    private UserService service;

    private User user;
    private UserDTO userDTO;

    @BeforeEach
    public void setUp() {
        user = new User(UUID.randomUUID().toString(), "FirstName", "LastName", 25, "user@example.com", "password");
        userDTO = AppUtils.entityToDto(user);
    }

    @Test
    public void testCreateUser() {
        when(repository.insert(any(User.class))).thenReturn(Mono.just(user));
        doNothing().when(kafkaProducer).sendMessage(any(String.class), any(UserDTO.class));

        Mono<UserDTO> createdUser = service.createUser(Mono.just(userDTO));
        assertNotNull(createdUser);
        assertEquals(userDTO.getId(), createdUser.block().getId());
    }

    @Test
    public void testGetAllUser() {
        when(repository.findAll()).thenReturn(Flux.just(user));

        Flux<UserDTO> users = service.getAllUser();
        assertNotNull(users);
        assertEquals(1, users.collectList().block().size());
    }

    @Test
    public void testGetUserById() {
        when(repository.findById(user.getId())).thenReturn(Mono.just(user));

        Mono<UserDTO> foundUser = service.getUser(user.getId());
        assertNotNull(foundUser);
        assertEquals(userDTO.getId(), foundUser.block().getId());
    }

    @Test
    public void testUpdateUser() {
        when(repository.findById(user.getId())).thenReturn(Mono.just(user));
        when(repository.save(any(User.class))).thenReturn(Mono.just(user));
        doNothing().when(kafkaProducer).sendMessage(any(String.class), any(UserDTO.class));

        Mono<UserDTO> updatedUser = service.updateUser(Mono.just(userDTO), user.getId());
        assertNotNull(updatedUser);
        assertEquals(userDTO.getId(), updatedUser.block().getId());
    }

    @Test
    public void testDeleteUser() {
        when(repository.deleteById(user.getId())).thenReturn(Mono.empty());

        Mono<Void> result = service.deleteUser(user.getId());
        assertNotNull(result);

        StepVerifier.create(result)
            .verifyComplete();
    }
}