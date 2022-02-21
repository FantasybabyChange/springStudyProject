package com.fantasybaby.spring.config.zk.waiter.repository;

import com.fantasybaby.spring.config.zk.waiter.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
