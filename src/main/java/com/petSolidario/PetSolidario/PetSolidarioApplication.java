package com.petSolidario.PetSolidario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = "com.petSolidario")
@EntityScan(basePackages = {"com.petSolidario.domains", "com.petSolidario.domains.enums", "com.petSolidario.newDomain"})
@EnableJpaRepositories(basePackages = "com.petSolidario.repositories")
@SpringBootApplication
public class PetSolidarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(PetSolidarioApplication.class, args);
	}

}
