package org.lab.samples.reactive.services;

import java.util.List;

import org.lab.samples.reactive.domain.Customer;
import org.lab.samples.reactive.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class InitializationService {

	@Autowired
	private CustomerRepository customerRepository;

	public void checkInitialization() {
		customerRepository.count().subscribe(x -> {
			if (x < 1) {
				initialize();
			}
		});
	}

	private void initialize() {
		log.info("Initializing database");

		Flux<Customer> customers = Flux.just(
			customer("John", "Doe"),
			customer("Franz", "Kafka"));
		
		customerRepository.saveAll(customers);
		
		Mono<List<Customer>> tmp = customers.collectList();

	}

	private Customer customer(String firstName, String lastName) {
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		return customer;
	}

}
