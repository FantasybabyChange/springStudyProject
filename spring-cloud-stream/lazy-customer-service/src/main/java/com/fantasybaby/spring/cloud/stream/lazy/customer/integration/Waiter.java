package com.fantasybaby.spring.cloud.stream.lazy.customer.integration;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface Waiter {
    String NOTIFY_ORDERS = "notifyOrders";

    @Input(NOTIFY_ORDERS)
    SubscribableChannel notification();
}
