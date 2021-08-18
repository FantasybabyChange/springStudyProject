package com.fantasybaby.spring.jedis.repository;

import com.fantasybaby.spring.jedis.model.Coffee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeRepository extends JpaRepository<Coffee, Long> {
}
