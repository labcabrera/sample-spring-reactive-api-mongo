package org.lab.samples.reactive.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
//@SuperBuilder
public class Customer extends Person {

	private boolean delete;

}
