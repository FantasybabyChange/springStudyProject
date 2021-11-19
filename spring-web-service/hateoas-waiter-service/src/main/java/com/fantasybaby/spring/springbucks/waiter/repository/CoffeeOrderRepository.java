package com.fantasybaby.spring.springbucks.waiter.repository;

import com.fantasybaby.spring.springbucks.waiter.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
