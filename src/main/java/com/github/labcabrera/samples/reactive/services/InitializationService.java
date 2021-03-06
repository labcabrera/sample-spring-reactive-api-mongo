package com.github.labcabrera.samples.reactive.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.labcabrera.samples.reactive.domain.ContactInfo;
import com.github.labcabrera.samples.reactive.domain.Customer;
import com.github.labcabrera.samples.reactive.domain.Gender;
import com.github.labcabrera.samples.reactive.repository.CustomerRepository;

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
		customer.setContactInfo(new ContactInfo());
		customer.setGender(Gender.MALE)	;
		customer.getContactInfo().setEmail(String.format("%s-%s@mock.org", firstName, lastName).toLowerCase());
		customer.getContactInfo().setPhoneNumber(RandomStringUtils.randomNumeric(9));
		return customer;
	}

}
