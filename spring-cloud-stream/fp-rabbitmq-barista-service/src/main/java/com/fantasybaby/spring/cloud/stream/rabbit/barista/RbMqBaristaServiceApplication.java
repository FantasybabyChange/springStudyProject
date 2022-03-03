package com.fantasybaby.spring.cloud.stream.rabbit.barista;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class RbMqBaristaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbMqBaristaServiceApplication.class, args);
	}

}
