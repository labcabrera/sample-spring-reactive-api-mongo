package com.github.labcabrera.samples.reactive.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import com.github.labcabrera.samples.reactive.domain.Customer;
import com.github.labcabrera.samples.reactive.repository.CustomerRepository;
import com.github.labcabrera.samples.reactive.repository.RsqlParser;
import com.github.labcabrera.samples.reactive.services.CustomerService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private ReactiveMongoTemplate template;

	@Autowired
	private RsqlParser rsqlParser;

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
	public Flux<Customer> find(String rsql, Pageable pageable) {
		Query query = rsqlParser.apply(rsql, Customer.class);
		return template
			.find(query, Customer.class)
			.skip(pageable.getPageNumber() * pageable.getPageSize())
			.take(pageable.getPageSize());
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
