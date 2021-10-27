package com.fantasybaby.spring.cache.demo.annotation;

import org.springframework.cache.annotation.Cacheable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created on 10/26/2021.
 *
 * @author Reid Liu
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Cacheable(cacheNames = {"coffees","coffees2"}, key = "#name")
public @interface CoffeeCache {
}
