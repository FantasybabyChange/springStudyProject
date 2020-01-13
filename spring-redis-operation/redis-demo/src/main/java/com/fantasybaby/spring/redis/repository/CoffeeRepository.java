package com.fantasybaby.spring.redis.repository;

import com.fantasybaby.spring.redis.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
