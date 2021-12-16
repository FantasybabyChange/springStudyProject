package com.fantasybaby.spring.actuator.mertrics.repository;

import com.fantasybaby.spring.actuator.mertrics.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
