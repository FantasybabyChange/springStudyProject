package com.fantasybaby.spring.boot.tomcat.demo.repository;

import com.fantasybaby.spring.boot.tomcat.demo.model.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {
}
