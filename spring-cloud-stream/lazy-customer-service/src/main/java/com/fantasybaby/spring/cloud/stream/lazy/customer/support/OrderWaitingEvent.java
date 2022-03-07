package com.fantasybaby.spring.cloud.stream.lazy.customer.support;

import com.fantasybaby.spring.cloud.stream.lazy.customer.model.CoffeeOrder;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

@Data
public class OrderWaitingEvent extends ApplicationEvent {
    private CoffeeOrder order;

    public OrderWaitingEvent(CoffeeOrder order) {
        super(order);
        this.order = order;
    }
}
