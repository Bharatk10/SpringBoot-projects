package com.zettamine.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApigatewayApplication.class, args);
	}
	

		
	@Bean
	public RouteLocator ZettaRouteConfig(RouteLocatorBuilder routeLocatorBuilder) {
		
		return routeLocatorBuilder.routes()
			.route(p->p.path("/zettabank/accounts/**")
					.filters(f->f.rewritePath("/zettabank/accounts/(?<segment>.*)","/${segment}")
					.circuitBreaker(config->config.setName("accountsCircuitBreaker")
							.setFallbackUri("forward:/contactSupport")
							))
					
					.uri("lb://ACCOUNTS"))
			.route(p->p.path("/zettabank/cards/**")
					.filters(f->f.rewritePath("/zettabank/cards/(?<segment>.*)","/${segment}"))
					.uri("lb://CARDS"))
			.route(p->p.path("/zettabank/loans/**")
					.filters(f->f.rewritePath("/zettabank/loans/(?<segment>.*)","/${segment}"))
					.uri("lb://LOANS")).build();
			
	}

}
