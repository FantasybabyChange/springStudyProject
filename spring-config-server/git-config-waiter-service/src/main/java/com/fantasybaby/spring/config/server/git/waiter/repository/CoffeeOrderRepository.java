package com.fantasybaby.spring.config.server.git.waiter.repository;

import com.fantasybaby.spring.config.server.git.waiter.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
