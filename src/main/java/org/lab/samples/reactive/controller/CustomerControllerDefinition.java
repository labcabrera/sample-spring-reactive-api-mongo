package org.lab.samples.reactive.controller;

import javax.validation.Valid;

import org.lab.samples.reactive.domain.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RequestMapping(value = "/api/v1/customers", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/customer")
public interface CustomerControllerDefinition {

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Find customer by id", response = Customer.class)
	Mono<Customer> findOne(@ApiParam(value = "Customer identifier", required = true) @PathVariable final String id);

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Find customers by RSQL", response = Customer.class)
	Flux<Customer> find(
		@ApiParam(value = "Search default value", required = false, example = "") @RequestParam(name = "search", defaultValue = "") final String search,
		@ApiParam(value = "Page default value", required = false, example = "0") @RequestParam(name = "page", defaultValue = "0") final int page,
		@ApiParam(value = "Size default value", required = false, example = "10") @RequestParam(name = "size", defaultValue = "10") final int size);

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "Customer creation")
	@ResponseStatus(HttpStatus.CREATED)
	Mono<Customer> create(
		@ApiParam(value = "Customer object", required = true) @RequestBody @Valid Customer customer);

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Customer deletion")
	Mono<Boolean> delete(
		@ApiParam(value = "The customer's id that needs to be deleted") @PathVariable String id);

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value = "Customer update")
	Mono<Customer> update(
		@ApiParam(value = "Customer identifier") @PathVariable String id,
		@ApiParam(value = "Updated customer data", required = true) @RequestBody @Valid Customer customer);
}
