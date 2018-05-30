package org.lab.samples.reactive.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public abstract class Person {

	@Id
	protected String id;

	@NotNull
	protected String firstName;

	@NotNull
	protected String lastName;

	protected ContactInfo contactInfo;

}
