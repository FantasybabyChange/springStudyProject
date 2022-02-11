package com.fantasybaby.spring.circuit.bulkhead.customer.model;

public enum OrderState {
    INIT, PAID, BREWING, BREWED, TAKEN, CANCELLED
}