package org.lab.samples.reactive.config;

import static java.util.stream.Collectors.toSet;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.ResponseEntity;

import com.fasterxml.classmate.TypeResolver;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.SpringIntegrationWebFluxRequestHandlerProvider;

@Configuration
@Import(SpringIntegrationWebFluxRequestHandlerProvider.class)
public class SwaggerConfig {

	@Autowired
	private TypeResolver resolver;

	@Bean
	Docket docket(List<SecurityScheme> authorizationTypes) {
		return new Docket(DocumentationType.SWAGGER_2).forCodeGeneration(true).select()
				.apis(RequestHandlerSelectors.basePackage("org.lab")).paths(PathSelectors.any()).build()
				.protocols(Stream.of("http", "https").collect(toSet()))
				.alternateTypeRules(new RecursiveAlternateTypeRule(resolver,
						Arrays.asList(
								AlternateTypeRules.newRule(resolver.resolve(Mono.class, WildcardType.class),
										resolver.resolve(WildcardType.class)),
								AlternateTypeRules.newRule(resolver.resolve(ResponseEntity.class, WildcardType.class),
										resolver.resolve(WildcardType.class)))))
				.alternateTypeRules(new RecursiveAlternateTypeRule(resolver,
						Arrays.asList(
								AlternateTypeRules.newRule(resolver.resolve(Flux.class, WildcardType.class),
										resolver.resolve(List.class, WildcardType.class)),
								AlternateTypeRules.newRule(resolver.resolve(ResponseEntity.class, WildcardType.class),
										resolver.resolve(WildcardType.class)))));
	}

}
