package com.fantasybaby.spring.propertysource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.env.PropertiesPropertySourceLoader;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Slf4j
public class CustomerEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private PropertiesPropertySourceLoader loader = new PropertiesPropertySourceLoader();
    private YamlPropertySourceLoader yamlPropertySourceLoader = new YamlPropertySourceLoader();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        MutablePropertySources propertySources = environment.getPropertySources();
        Resource resource = new ClassPathResource("customer.properties");
        Resource yamlResource = new ClassPathResource("customer.yaml");
        try {
            PropertySource ps = loader.load("first", resource)
                    .get(0);
            propertySources.addFirst(ps);

            PropertySource<?> propertySource = yamlPropertySourceLoader.load("second", yamlResource).get(0);
            propertySources.addAfter("first", propertySource);
        } catch (Exception e) {
            log.error("Exception!", e);
        }

    }
}
