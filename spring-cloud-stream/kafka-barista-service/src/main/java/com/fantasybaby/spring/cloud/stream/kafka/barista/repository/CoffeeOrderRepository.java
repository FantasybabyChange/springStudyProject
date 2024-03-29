package com.fantasybaby.spring.cloud.stream.kafka.barista.repository;

import com.fantasybaby.spring.cloud.stream.kafka.barista.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
