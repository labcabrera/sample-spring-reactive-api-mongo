package org.lab.samples.reactive;

import org.lab.samples.reactive.services.InitializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux;

@SpringBootApplication
@EnableSwagger2WebFlux
public class Application implements CommandLineRunner {

	@Autowired
	private InitializationService initializationService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initializationService.checkInitialization();
	}

}
