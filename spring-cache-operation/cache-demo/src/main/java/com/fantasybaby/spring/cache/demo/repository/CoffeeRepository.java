package com.fantasybaby.spring.cache.demo.repository;

import com.fantasybaby.spring.cache.demo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
