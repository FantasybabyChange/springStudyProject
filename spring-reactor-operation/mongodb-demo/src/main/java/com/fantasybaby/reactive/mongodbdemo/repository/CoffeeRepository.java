package com.fantasybaby.reactive.mongodbdemo.repository;

import com.fantasybaby.reactive.mongodbdemo.model.Coffee;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import java.util.List;

public interface CoffeeRepository extends ReactiveMongoRepository<Coffee, String> {
    List<Coffee> findByName(String name);
}
