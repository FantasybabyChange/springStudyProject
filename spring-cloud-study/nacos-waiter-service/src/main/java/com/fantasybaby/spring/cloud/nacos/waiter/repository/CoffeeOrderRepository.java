package com.fantasybaby.spring.cloud.nacos.waiter.repository;

import com.fantasybaby.spring.cloud.nacos.waiter.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
