package com.fantasybaby.spring.interceptor.repository;

import com.fantasybaby.spring.interceptor.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
