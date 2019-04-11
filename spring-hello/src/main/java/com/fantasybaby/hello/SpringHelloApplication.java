package com.fantasybaby.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class SpringHelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringHelloApplication.class, args);
	}
	@RequestMapping("/hello")
	public String hello(){
		return "done";
	}
}
