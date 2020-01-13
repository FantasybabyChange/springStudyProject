package com.fantasybaby.spring.redis.repository.repository;

import com.fantasybaby.spring.redis.repository.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
