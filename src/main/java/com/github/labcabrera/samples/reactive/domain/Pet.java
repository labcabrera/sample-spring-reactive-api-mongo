package com.github.labcabrera.samples.reactive.domain;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "pets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Pet {

	@Id
	private String id;

	@NotNull
	private String name;

	private Gender gender;

	//NOTE: we can't use DBRef using reactive mongo
	@NotNull
	private String customerId;

}
