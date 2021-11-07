package com.fantasybaby.spring.mvc.repository;

import com.fantasybaby.spring.mvc.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
