package com.github.labcabrera.samples.reactive.converter;

import java.time.LocalDate;

import org.springframework.core.convert.converter.Converter;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {

	@Override
	public LocalDate convert(String source) {
		return LocalDate.parse(source);
	}

}