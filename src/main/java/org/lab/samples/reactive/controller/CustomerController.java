package org.lab.samples.reactive.controller;

import javax.validation.Valid;

import org.lab.samples.reactive.domain.Customer;
import org.lab.samples.reactive.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(value = "/api/v1/customers", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Get Customer by id", response = Customer.class)
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid username supplied"),
			@ApiResponse(code = 404, message = "Customer doesn't exist") })
	public Mono<Customer> findOne(
			@ApiParam(value = "The ID that needs to be fetched.", required = true) @PathVariable String id) {
		return customerService.findOne(id);
	}

	@RequestMapping(method = RequestMethod.GET)
	@ApiOperation(value = "Get all Customers", response = Customer.class)
	public Flux<Customer> find(
			@ApiParam(value = "Search default value", required = false, example = "") @RequestParam(name = "search", defaultValue = "") String search,
			@ApiParam(value = "Page default value", required = false, example = "0") final @RequestParam(name = "page", defaultValue = "0") int page,
			@ApiParam(value = "Size default value", required = false, example = "10") final @RequestParam(name = "size", defaultValue = "10") int size) {
		return customerService.find(search, PageRequest.of(page, size));
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@ApiOperation(value = "Creates Customer with given input object")
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Customer> create(
			@ApiParam(value = "Customer object", required = true) @RequestBody @Valid Customer customer) {
		return customerService.createCustomer(customer);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete customer")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid customer id supplied"),
			@ApiResponse(code = 404, message = "User not found") })
	public Mono<Boolean> delete(
			@ApiParam(value = "The customer's id that needs to be deleted") @PathVariable String id) {
		return customerService.delete(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseBody
	@ApiOperation(value = "Updated customer")
	@ApiResponses(value = { @ApiResponse(code = 400, message = "Invalid user supplied"),
			@ApiResponse(code = 404, message = "User not found") })
	public Mono<Customer> update(
			@ApiParam(value = "The customer's id that needs to be updated") @PathVariable String id,
			@ApiParam(value = "Updated customer object", required = true) @RequestBody @Valid Customer customer) {
		return customerService.updateCustomer(id, customer);
	}
}
