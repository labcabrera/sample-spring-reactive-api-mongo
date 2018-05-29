package org.lab.samples.reactive.handler;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.lab.samples.reactive.domain.Pet;
import org.lab.samples.reactive.repository.PetRepository;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class PetHandler {

	private final PetRepository repository;
	private final ReactiveMongoOperations operations;

	public Mono<ServerResponse> findById(ServerRequest request) {
		String id = request.pathVariable("id");
		Mono<Pet> monoPet = this.repository.findById(id);
		return ServerResponse.ok().contentType(APPLICATION_JSON).body(monoPet, Pet.class);
	}

	public Mono<ServerResponse> findAll(ServerRequest request) {
		Flux<Pet> pets = repository.findAll();
		return ServerResponse.ok().contentType(APPLICATION_JSON).body(pets, Pet.class);
	}

	// TODO remove operations
	public Mono<ServerResponse> save(ServerRequest request) {
		Mono<Pet> monoPet = request.bodyToMono(Pet.class);
		Mono<Pet> inserted = operations.insert(monoPet);
		return ServerResponse.ok().body(inserted, Pet.class);
	}

}
