package com.ibm.products;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class ProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsApplication.class, args);
	}

	@Bean
	public OpenAPI customDocOpenApi(){
		return new OpenAPI()
				.info(new Info()
						.title("SpringDoc for products")
						.version("v1.0.0")
						.description("Demo Bootcamp Microservicios")
						.termsOfService("https://springdoc.org/")
						.contact(new Contact()
								.name("Gabriel Castillo")
								.email("gabcast@mx1.ibm.com")
								.url("https://github.ibm.com/gabcast"))
						.license(new License().name("Apache 2.0").url("https://springdoc.org/"))
				);
	}

}
