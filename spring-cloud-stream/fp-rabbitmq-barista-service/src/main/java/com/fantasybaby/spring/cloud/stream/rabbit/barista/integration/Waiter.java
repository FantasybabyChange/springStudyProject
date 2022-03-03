package com.fantasybaby.spring.cloud.stream.rabbit.barista.integration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

public interface Waiter {
    String NEW_ORDERS = "newOrders";
    String FINISHED_ORDERS = "finishedOrders";



    @Output(FINISHED_ORDERS)
    MessageChannel finishedOrders();
}
