package org.lab.samples.reactive.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Pet {

	@Id
	private String id;

	private String name;

	private Gender gender;

	@DBRef
	private Customer customer;

}
