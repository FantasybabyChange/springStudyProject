package com.fantasybaby.spring.propertysource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PropertySourceDemoApplication implements ApplicationRunner {
    @Value("${fantasybaby.property}")
    private String property;
    @Value("${fantasybaby.yaml-property}")
    private String yamlProperty;
    @Value("${fantasybaby.property1}")
    private String property1;

    public static void main(String[] args) {
        SpringApplication.run(PropertySourceDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        log.info("first:{}", property);
        log.info("second:{}", yamlProperty);
        log.info("last:{}", property1);

    }
}
