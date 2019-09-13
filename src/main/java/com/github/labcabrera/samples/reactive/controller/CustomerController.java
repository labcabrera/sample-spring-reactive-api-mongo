package com.github.labcabrera.samples.reactive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RestController;

import com.github.labcabrera.samples.reactive.domain.Customer;
import com.github.labcabrera.samples.reactive.services.CustomerService;

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
	public Mono<Customer> delete(String id) {
		return customerService.delete(id);
	}

	@Override
	public Mono<Customer> update(String id, Customer customer) {
		return customerService.updateCustomer(id, customer);
	}
}
