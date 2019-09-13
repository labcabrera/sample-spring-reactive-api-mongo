package com.github.labcabrera.samples.reactive.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;

import com.github.labcabrera.samples.reactive.handler.PetHandler;

@Configuration
public class PetClinicRouter {

	@Bean
	RouterFunction<?> routes(PetHandler handler) {
		return nest(path("/pets"),
			route(RequestPredicates.GET("/{id}"), handler::findById)
				.andRoute(RequestPredicates.GET(""), handler::findAll)
				.andRoute(RequestPredicates.POST(""), handler::save)

		);
	}

}
