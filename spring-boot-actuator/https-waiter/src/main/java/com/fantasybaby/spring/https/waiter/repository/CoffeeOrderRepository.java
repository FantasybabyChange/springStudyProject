package com.fantasybaby.spring.https.waiter.repository;

import com.fantasybaby.spring.https.waiter.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
