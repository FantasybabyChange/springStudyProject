package com.fantasybaby.spring.sleuth.zipkin.barista.repository;

import com.fantasybaby.spring.sleuth.zipkin.barista.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
