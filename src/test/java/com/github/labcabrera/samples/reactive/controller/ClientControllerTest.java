package com.github.labcabrera.samples.reactive.controller;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.labcabrera.samples.reactive.SampleApplication;
import com.github.labcabrera.samples.reactive.domain.Customer;
import com.github.labcabrera.samples.reactive.repository.CustomerRepository;

import io.restassured.RestAssured;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SampleApplication.class)
public class ClientControllerTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Value("${server.port:8080}")
	private int port;

	@Test
	public void testFindById() {
		Customer customer = customerRepository.findByFirstNameAndLastName("John", "Doe").toStream()
			.findFirst()
			.orElseThrow(() -> new RuntimeException("Missing data"));

		RestAssured.given()
			.port(port)
			.when()
			.get("customers/{id}", customer.getId())
			.then()
			.assertThat()
			.statusCode(HttpStatus.OK.value())
			.body("id", Matchers.is(customer.getId()));
	}

}
