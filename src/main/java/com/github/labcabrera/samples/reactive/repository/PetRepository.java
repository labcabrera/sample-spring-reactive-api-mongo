package com.github.labcabrera.samples.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.github.labcabrera.samples.reactive.domain.Pet;

import reactor.core.publisher.Flux;

public interface PetRepository extends ReactiveMongoRepository<Pet, String> {

	Flux<Pet> findByName(String name);
}