package org.lab.samples.reactive.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Pet {

	@Id
	private String id;

	@NotNull
	private String name;

	private Gender gender;

	@NotNull
	@DBRef
	private Customer customer;

}
