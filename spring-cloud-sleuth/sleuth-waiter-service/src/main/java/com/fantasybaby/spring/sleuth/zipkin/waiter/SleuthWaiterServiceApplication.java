package com.fantasybaby.spring.sleuth.zipkin.waiter;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fantasybaby.spring.sleuth.zipkin.waiter.controller.PerformanceInteceptor;
import com.fantasybaby.spring.sleuth.zipkin.waiter.integration.Barista;
import com.fantasybaby.spring.sleuth.zipkin.waiter.integration.Customer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.TimeZone;

@SpringBootApplication
@EnableJpaRepositories
@EnableCaching
@EnableDiscoveryClient
@EnableBinding({ Barista.class, Customer.class })
public class SleuthWaiterServiceApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(SleuthWaiterServiceApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PerformanceInteceptor())
                .addPathPatterns("/coffee/**").addPathPatterns("/order/**");
    }

    @Bean
    public Hibernate5Module hibernate5Module() {
        return new Hibernate5Module();
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
        return builder -> {
            builder.indentOutput(true);
            builder.timeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        };
    }
}
