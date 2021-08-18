package com.fantasybaby.spring.cache.demo.repository;

import com.fantasybaby.spring.cache.demo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
