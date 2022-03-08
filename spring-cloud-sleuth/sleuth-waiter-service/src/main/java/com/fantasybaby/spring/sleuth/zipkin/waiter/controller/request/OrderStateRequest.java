package com.fantasybaby.spring.sleuth.zipkin.waiter.controller.request;

import com.fantasybaby.spring.sleuth.zipkin.waiter.model.OrderState;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderStateRequest {
    private OrderState state;
}
