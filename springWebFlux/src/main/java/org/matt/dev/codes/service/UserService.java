package org.matt.dev.codes.service;

import org.matt.dev.codes.dto.UserDTO;
import org.matt.dev.codes.repository.UserRepository;
import org.matt.dev.codes.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

	@Autowired
	private UserRepository repository;

	public Flux<UserDTO> getAllUser() {
		return repository.findAll()
				.map(AppUtils::entityToDto);
	}

	public Mono<UserDTO> getUser(String id) {
		return repository.findById(id)
				.map(AppUtils::entityToDto);
	}

	public Mono<UserDTO> createUser(Mono<UserDTO> userDtoMono) {
		return userDtoMono
				.map(AppUtils::dtoToEntity)
				.flatMap(repository::insert)
				.map(AppUtils::entityToDto);

	}

	public Mono<UserDTO> updateUser(Mono<UserDTO> userDtoMono, String id) {
		return repository.findById(id)
				.flatMap(u -> userDtoMono.map(AppUtils::dtoToEntity)
				.doOnNext(e -> e.setId(id)))
				.flatMap(repository::save)
				.map(AppUtils::entityToDto);
	}
	
	public Mono<Void> deleteUser(String id){
		return repository.deleteById(id);
	}

}
