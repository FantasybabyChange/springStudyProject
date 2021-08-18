package com.fantasybaby.spring.redis.repository.repository.mongo;

import com.fantasybaby.spring.redis.repository.model.mongo.Coffee;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CoffeeMongoRepository extends MongoRepository<Coffee, String> {
    Optional<Coffee> findByName(String name);
}
