package com.fantasybaby.spring.cloud.stream.rabbit.barista.integration;

import com.fantasybaby.spring.cloud.stream.rabbit.barista.model.CoffeeOrder;
import com.fantasybaby.spring.cloud.stream.rabbit.barista.model.OrderState;
import com.fantasybaby.spring.cloud.stream.rabbit.barista.repository.CoffeeOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Function;

@Component
@Slf4j
@Transactional
public class OrderListener {
    @Autowired
    private CoffeeOrderRepository orderRepository;
    @Autowired
    private StreamBridge streamBridge;
    @Value("${order.barista-prefix}${random.uuid}")
    private String barista;

    @Bean
    public Function<Long, String> newOrders() {
        return id -> {
            CoffeeOrder o = orderRepository.getById(id);
            if (o == null) {
                log.warn("Order id {} is NOT valid.", id);
                return "hello";
            }
            log.info("Receive a new Order {}. Waiter: {}. Customer: {}",
                    id, o.getWaiter(), o.getCustomer());
            o.setState(OrderState.BREWED);
            o.setBarista(barista);
            orderRepository.save(o);
            log.info("Order {} is READY.", id);
            streamBridge.send("finishedOrders-out-0", MessageBuilder.withPayload(id).build());
            return "hello";
        };

    }


}
