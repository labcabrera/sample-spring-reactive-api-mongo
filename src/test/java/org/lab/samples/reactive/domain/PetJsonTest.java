package org.lab.samples.reactive.domain;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PetJsonTest {

	@Autowired
	private ObjectMapper objectMapper;

	// Check for custom lowecase gender serialization
	@Test
	public void test() throws JsonParseException, JsonMappingException, IOException {
		Pet pet = Pet.builder()
			.id("12345")
			.name("Chin")
			.gender(Gender.MALE)
			.build();

		String json = objectMapper.writeValueAsString(pet);

		Pet readed = objectMapper.readValue(json, Pet.class);

		Assert.assertEquals(Gender.MALE, readed.getGender());
	}

}
