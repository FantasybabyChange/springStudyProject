package com.fantasybaby.spring.cloud.stream.kafka.waiter.controller.request;

import com.fantasybaby.spring.cloud.stream.kafka.waiter.model.OrderState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderStateRequest {
    private OrderState state;
}
