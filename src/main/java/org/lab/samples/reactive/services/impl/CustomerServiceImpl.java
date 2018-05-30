package org.lab.samples.reactive.services.impl;

import org.lab.samples.reactive.domain.Customer;
import org.lab.samples.reactive.repository.CustomerRepository;
import org.lab.samples.reactive.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Override
	public Mono<Customer> createCustomer(Customer customer) {
		return repository.insert(customer);
	}

	@Override
	public Mono<Customer> updateCustomer(String id, Customer customer) {
		return findOne(id).doOnSuccess(current -> {
			current.setFirstName(customer.getFirstName());
			current.setLastName(customer.getLastName());
			current.setContactInfo(customer.getContactInfo());
			repository.save(current).subscribe();
		});
	}

	@Override
	public Flux<Customer> findAll() {
		return repository.findAll();
	}

	@Override
	public Mono<Customer> findOne(String id) {
		return repository.findById(id);
	}

	@Override
	public Flux<Customer> findByFirstNameAndLastName(String firstName, String lastName) {
		return repository.findByFirstNameAndLastName(firstName, lastName);
	}

	@Override
	public Mono<Boolean> delete(String id) {
		return findOne(id).doOnSuccess(current -> {
			current.setDelete(true);
			repository.save(current).subscribe();
		}).flatMap(blog -> Mono.just(Boolean.TRUE));
	}

}
