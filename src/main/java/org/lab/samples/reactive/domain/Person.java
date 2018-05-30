package org.lab.samples.reactive.domain;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public abstract class Person {

	@Id
	protected String id;

	protected String firstName;

	protected String lastName;

	protected ContactInfo contactInfo;

}
