package com.fantasybaby.spring.autoconfiguration.greeting;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

@Slf4j
public class GreetingApplicationRunner implements ApplicationRunner {
    private String name;

    public GreetingApplicationRunner(String name) {
        this.name = name;
        log.info("Initializing GreetingApplicationRunner.");

    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("Hello {}! We all like Spring! ", name);
    }
}
