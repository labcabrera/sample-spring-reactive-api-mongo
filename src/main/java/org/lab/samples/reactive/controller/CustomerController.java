package org.lab.samples.reactive.controller;

import javax.validation.Valid;

import org.lab.samples.reactive.domain.Customer;
import org.lab.samples.reactive.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/{id}")
	public Mono<Customer> findOne(@PathVariable String id) {
		return customerService.findOne(id);
	}

	@GetMapping
	public Flux<Customer> find(
		@RequestParam(name = "search", defaultValue = "") String search,
		final @RequestParam(name = "page", defaultValue = "0") int page,
		final @RequestParam(name = "size", defaultValue = "10") int size) {
		return customerService.find(search, PageRequest.of(page, size));
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Customer> create(@RequestBody @Valid Customer customer) {
		return customerService.createCustomer(customer);
	}

	@DeleteMapping("/{id}")
	public Mono<Boolean> delete(@PathVariable String id) {
		return customerService.delete(id);
	}

	@PutMapping("/{id}")
	public Mono<Customer> update(@PathVariable String id, @RequestBody @Valid Customer customer) {
		return customerService.updateCustomer(id, customer);
	}
}
