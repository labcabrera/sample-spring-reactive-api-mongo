package org.lab.samples.reactive.controller;

import org.lab.samples.reactive.domain.Customer;
import org.lab.samples.reactive.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CustomerController implements CustomerControllerDefinition {

	@Autowired
	private CustomerService customerService;

	@Override
	public Mono<Customer> findOne(String id) {
		return customerService.findOne(id);
	}

	@Override
	public Flux<Customer> find(String search, int page, int size) {
		return customerService.find(search, PageRequest.of(page, size));
	}

	@Override
	public Mono<Customer> create(Customer customer) {
		return customerService.createCustomer(customer);
	}

	@Override
	public Mono<Boolean> delete(String id) {
		return customerService.delete(id);
	}

	@Override
	public Mono<Customer> update(String id, Customer customer) {
		return customerService.updateCustomer(id, customer);
	}
}
