package com.fantasybaby.spring.aspect.demo.repository;

import com.fantasybaby.spring.aspect.demo.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
