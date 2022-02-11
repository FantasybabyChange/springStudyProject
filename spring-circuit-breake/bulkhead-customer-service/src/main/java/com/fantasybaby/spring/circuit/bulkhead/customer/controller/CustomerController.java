package com.fantasybaby.spring.circuit.bulkhead.customer.controller;

import com.fantasybaby.spring.circuit.bulkhead.customer.integration.CoffeeOrderService;
import com.fantasybaby.spring.circuit.bulkhead.customer.integration.CoffeeService;
import com.fantasybaby.spring.circuit.bulkhead.customer.model.Coffee;
import com.fantasybaby.spring.circuit.bulkhead.customer.model.CoffeeOrder;
import com.fantasybaby.spring.circuit.bulkhead.customer.model.NewOrderRequest;
import io.github.resilience4j.bulkhead.Bulkhead;
import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.bulkhead.BulkheadRegistry;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/customer")
@Slf4j
public class CustomerController {
    @Autowired
    private CoffeeService coffeeService;
    @Autowired
    private CoffeeOrderService coffeeOrderService;
    private CircuitBreaker circuitBreaker;
    private Bulkhead bulkhead;

    public CustomerController(CircuitBreakerRegistry circuitBreakerRegistry,
                              BulkheadRegistry bulkheadRegistry) {
        circuitBreaker = circuitBreakerRegistry.circuitBreaker("menu");
        bulkhead = bulkheadRegistry.bulkhead("menu");
    }

    @GetMapping("/menu")
    public List<Coffee> readMenu() {
        log.info("read menu");
        List<Coffee> coffees = Try.ofSupplier(
                Bulkhead.decorateSupplier(bulkhead,
                        CircuitBreaker.decorateSupplier(circuitBreaker,
                                () -> {
                                    try {
                                        TimeUnit.SECONDS.sleep(1L);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    return coffeeService.getAll();
                                })))
                .recover(CallNotPermittedException.class, Collections.emptyList())
                .recover(BulkheadFullException.class, Collections.emptyList())
                .get();


        return coffees;
    }

    @PostMapping("/order")
    @io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "order")
    @io.github.resilience4j.bulkhead.annotation.Bulkhead(name = "order")
    public CoffeeOrder createOrder() {
        NewOrderRequest orderRequest = NewOrderRequest.builder()
                .customer("Li Lei")
                .items(Arrays.asList("capuccino"))
                .build();
        CoffeeOrder order = coffeeOrderService.create(orderRequest);
        log.info("Order ID: {}", order != null ? order.getId() : "-");
        return order;
    }
}
