package org.lab.samples.reactive.repository;

import org.lab.samples.reactive.domain.Pet;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import reactor.core.publisher.Flux;

public interface PetRepository extends ReactiveCrudRepository<Pet, String> {

	Flux<Pet> findByName(String name);
}