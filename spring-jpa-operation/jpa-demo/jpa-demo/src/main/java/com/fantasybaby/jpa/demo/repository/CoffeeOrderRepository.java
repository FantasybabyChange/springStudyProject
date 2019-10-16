package com.fantasybaby.jpa.demo.repository;

import com.fantasybaby.jpa.demo.model.CoffeeOrder;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeOrderRepository extends CrudRepository<CoffeeOrder, Long> {
}
