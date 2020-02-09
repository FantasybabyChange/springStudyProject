package com.fantasybaby.jpa.demo.repository;

import com.fantasybaby.jpa.demo.model.Coffee;
import org.springframework.data.repository.CrudRepository;

public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
}
