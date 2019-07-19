package org.lab.samples.reactive.services;

import org.lab.samples.reactive.domain.Customer;
import org.lab.samples.reactive.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

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
			customer("John", "Smith"),
			customer("Franz", "Kafka"),
			customer("Milan", "Kundera"),
			customer("Paul", "Auster"),
			customer("John", "Sample01"),
			customer("John", "Sample02"),
			customer("John", "Sample03"),
			customer("John", "Sample04"),
			customer("John", "Sample05"),
			customer("John", "Sample06"),
			customer("John", "Sample07"),
			customer("John", "Sample08"),
			customer("John", "Sample09"),
			customer("John", "Sample10"),
			customer("John", "Sample11"),
			customer("John", "Sample12"));
		customerRepository.saveAll(customers).subscribe();
	}

	private Customer customer(String firstName, String lastName) {
		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		return customer;
	}

}
