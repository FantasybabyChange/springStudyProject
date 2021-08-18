package com.fantasybaby.spring.jedis.repository;

import com.fantasybaby.spring.jedis.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
