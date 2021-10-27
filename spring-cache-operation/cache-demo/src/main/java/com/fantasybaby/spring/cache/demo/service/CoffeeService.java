package com.fantasybaby.spring.cache.demo.service;

import com.fantasybaby.spring.cache.demo.annotation.CoffeeCache;
import com.fantasybaby.spring.cache.demo.model.Coffee;
import com.fantasybaby.spring.cache.demo.repository.CoffeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.exact;

@Slf4j
@Service
//@CacheConfig
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

//    @Cacheable({"coffees","coffees2"})
    @CoffeeCache
    public List<Coffee> findAllCoffee() {
        return coffeeRepository.findAll();
    }

    @CacheEvict({"coffees","coffees2"})
    public void reloadCoffee() {
    }
    @Cacheable(cacheNames = "coffees",key = "#name")
    public Optional<Coffee> findOneCoffee(String name) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", exact().ignoreCase());
        Optional<Coffee> coffee = coffeeRepository.findOne(
                Example.of(Coffee.builder().name(name).build(), matcher));
        log.info("Coffee Found: {}", coffee);
        return coffee;
    }
}
