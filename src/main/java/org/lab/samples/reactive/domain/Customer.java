package org.lab.samples.reactive.domain;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Document(collection = "customers")
@Data
@EqualsAndHashCode(callSuper = true)
public class Customer extends Person {

	private boolean delete;

}
