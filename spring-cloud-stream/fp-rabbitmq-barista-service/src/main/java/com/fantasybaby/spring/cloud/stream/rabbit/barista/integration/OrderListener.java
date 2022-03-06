package com.fantasybaby.spring.cloud.stream.rabbit.barista.integration;

import com.fantasybaby.spring.cloud.stream.rabbit.barista.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.function.Function;

@Component
@Slf4j
//@Transactional
public class OrderListener {

    @Autowired
    private StreamBridge streamBridge;
    @Resource
    private IOrderService orderService;

    @Bean
    public Function<Long, String> newOrders() {
        return id -> {
            orderService.getCoffeeOrder(id);
            log.info("Order {} is READY.", id);
            streamBridge.send("finishedOrders-out-0", MessageBuilder.withPayload(id).build());
            return "hello";
        };

    }


}
