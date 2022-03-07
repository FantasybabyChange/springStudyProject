package com.fantasybaby.spring.cloud.stream.rabbitmq.busy.waiter.controller.request;

import com.fantasybaby.spring.cloud.stream.rabbitmq.busy.waiter.model.OrderState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderStateRequest {
    private OrderState state;
}
