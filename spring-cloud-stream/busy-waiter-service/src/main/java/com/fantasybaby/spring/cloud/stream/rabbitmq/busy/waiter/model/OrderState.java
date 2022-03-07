package com.fantasybaby.spring.cloud.stream.rabbitmq.busy.waiter.model;

public enum OrderState {
    INIT, PAID, BREWING, BREWED, TAKEN, CANCELLED
}
