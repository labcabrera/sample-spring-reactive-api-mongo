package com.github.labcabrera.samples.reactive.controller;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.github.labcabrera.samples.reactive.SampleApplication;
import com.github.labcabrera.samples.reactive.domain.Customer;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class ClientControllerTest {

	@Autowired
	private WebTestClient webTestClient;

	@Autowired
	private ReactiveMongoTemplate template;

	private Customer customer;

	@Before
	public void before() {
		customer = template.findOne(new Query(), Customer.class).block();
		Assume.assumeNotNull(customer);
	}

	@Test
	public void testFindByIdOk() {
		webTestClient
			.get()
			.uri("/customers/{id}", customer.getId())
			.exchange()
			.expectStatus().isOk();
	}

}
