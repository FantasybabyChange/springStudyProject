package com.fantasybaby.spring.config.consul.waiter.repository;

import com.fantasybaby.spring.config.consul.waiter.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
