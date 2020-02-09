package com.fantasybaby.spring.aspect.demo.repository;

import com.fantasybaby.spring.aspect.demo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
