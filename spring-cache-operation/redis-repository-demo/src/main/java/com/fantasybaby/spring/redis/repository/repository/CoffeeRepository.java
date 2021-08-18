package com.fantasybaby.spring.redis.repository.repository;

import com.fantasybaby.spring.redis.repository.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
