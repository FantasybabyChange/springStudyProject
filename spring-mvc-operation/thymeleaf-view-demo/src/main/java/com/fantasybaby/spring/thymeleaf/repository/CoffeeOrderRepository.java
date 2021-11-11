package com.fantasybaby.spring.thymeleaf.repository;

import com.fantasybaby.spring.thymeleaf.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
