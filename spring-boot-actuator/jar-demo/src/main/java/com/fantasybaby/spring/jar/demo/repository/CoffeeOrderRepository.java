package com.fantasybaby.spring.jar.demo.repository;

import com.fantasybaby.spring.jar.demo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
