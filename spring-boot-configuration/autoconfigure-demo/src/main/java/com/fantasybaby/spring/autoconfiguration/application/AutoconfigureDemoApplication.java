package com.fantasybaby.spring.autoconfiguration.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.fantasybaby.spring")
public class AutoconfigureDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AutoconfigureDemoApplication.class, args);
    }
	/*@Bean
	public GreetingApplicationRunner greetingApplicationRunner(){
		return new GreetingApplicationRunner("customer");
	}*/
}
