package com.fantasybaby.spring.cache.demo.service;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheType;
import com.alicp.jetcache.anno.Cached;
import com.alicp.jetcache.anno.CreateCache;
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
public class CoffeeService {
    @Autowired
    private CoffeeRepository coffeeRepository;

    @Cached(name = "coffees",expire = 100,cacheType = CacheType.LOCAL)
    public List<Coffee> findAllCoffee() {
        return coffeeRepository.findAll();
    }

    @CacheInvalidate(name = "coffees")
    public void reloadCoffee() {
    }
    @Cached(name = "singleCoffee",expire = 100,key = "#name",cacheType = CacheType.LOCAL)
    public Optional<Coffee> findOneCoffee(String name,Long id) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("name", exact().ignoreCase()).withMatcher("id",exact());
        Coffee newCoffee = new Coffee();
        newCoffee.setName(name);
        newCoffee.setId(id);
        Optional<Coffee> coffee = coffeeRepository.findOne(
                Example.of(newCoffee, matcher));
        log.info("Coffee Found: {}", coffee);
        return coffee;
    }
}
