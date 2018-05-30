package org.lab.samples.reactive.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer extends Person {

	private boolean delete = Boolean.FALSE;

}
