package org.lab.samples.reactive.config;

import static org.springframework.web.reactive.function.server.RequestPredicates.path;
import static org.springframework.web.reactive.function.server.RouterFunctions.nest;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import org.lab.samples.reactive.handler.PetHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;

@Configuration
public class PetClinicRouter {

	@Bean
	RouterFunction<?> routes(PetHandler handler) { //@formatter:off
		return nest(path("/api/v1/pets"),

			route(RequestPredicates.GET("/{id}"), handler::findById)
			.andRoute(RequestPredicates.GET(""), handler::findAll)
			.andRoute(RequestPredicates.POST(""), handler::save)
			
		);

	} //@formatter:on

}
