package com.fantasybaby.spring.http2.waiter.repository;

import com.fantasybaby.spring.http2.waiter.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
