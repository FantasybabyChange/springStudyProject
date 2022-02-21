package com.fantasybaby.spring.config.nacos.waiter.repository;

import com.fantasybaby.spring.config.nacos.waiter.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
