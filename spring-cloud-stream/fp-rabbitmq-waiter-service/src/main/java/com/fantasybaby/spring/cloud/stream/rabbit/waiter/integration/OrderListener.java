package com.fantasybaby.spring.cloud.stream.rabbit.waiter.integration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;
import java.util.function.Function;

@Component
@Slf4j
public class OrderListener {

    @Bean
    public Consumer<Long> finishedOrders() {
        return id -> log.info("We've finished an order [{}].", id);
    }
}
