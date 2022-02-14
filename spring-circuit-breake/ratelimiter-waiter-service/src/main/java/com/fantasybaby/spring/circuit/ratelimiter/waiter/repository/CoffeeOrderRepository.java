package com.fantasybaby.spring.circuit.ratelimiter.waiter.repository;

import com.fantasybaby.spring.circuit.ratelimiter.waiter.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
