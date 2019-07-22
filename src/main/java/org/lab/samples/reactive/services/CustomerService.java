package org.lab.samples.reactive.services;

import org.lab.samples.reactive.domain.Customer;
import org.springframework.data.domain.Pageable;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

	Mono<Customer> findOne(String id);

	Flux<Customer> find(String rsql, Pageable page);

	Flux<Customer> findByFirstNameAndLastName(String firstName, String lastName);

	Mono<Customer> createCustomer(Customer customer);

	Mono<Customer> updateCustomer(String id, Customer customer);

	Mono<Boolean> delete(String id);
}
