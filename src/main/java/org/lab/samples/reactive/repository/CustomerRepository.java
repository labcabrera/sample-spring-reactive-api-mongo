package org.lab.samples.reactive.repository;

import org.lab.samples.reactive.domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import reactor.core.publisher.Flux;

public interface CustomerRepository extends ReactiveMongoRepository<Customer, String> {

	Flux<Customer> findByFirstNameAndLastName(String firstName, String lastName);

}