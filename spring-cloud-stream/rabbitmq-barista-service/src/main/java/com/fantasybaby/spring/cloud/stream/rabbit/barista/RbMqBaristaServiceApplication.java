package com.fantasybaby.spring.cloud.stream.rabbit.barista;

import com.fantasybaby.spring.cloud.stream.rabbit.barista.integration.Waiter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
@EnableBinding(Waiter.class)
public class RbMqBaristaServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RbMqBaristaServiceApplication.class, args);
	}

}
