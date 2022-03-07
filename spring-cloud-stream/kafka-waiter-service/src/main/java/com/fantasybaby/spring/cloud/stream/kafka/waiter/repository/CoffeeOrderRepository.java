package com.fantasybaby.spring.cloud.stream.kafka.waiter.repository;

import com.fantasybaby.spring.cloud.stream.kafka.waiter.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
