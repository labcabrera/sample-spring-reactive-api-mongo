package org.lab.samples.reactive.repository;

import org.lab.samples.reactive.domain.Pet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;

public interface PetRepository extends ReactiveMongoRepository<Pet, String> {

	Flux<Pet> findByName(String name);
}