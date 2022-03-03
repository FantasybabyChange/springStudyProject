package com.fantasybaby.spring.cloud.stream.rabbit.barista.repository;

import com.fantasybaby.spring.cloud.stream.rabbit.barista.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
