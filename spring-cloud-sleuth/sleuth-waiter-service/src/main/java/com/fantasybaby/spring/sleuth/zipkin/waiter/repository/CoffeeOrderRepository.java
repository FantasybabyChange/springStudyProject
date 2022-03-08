package com.fantasybaby.spring.sleuth.zipkin.waiter.repository;

import com.fantasybaby.spring.sleuth.zipkin.waiter.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
