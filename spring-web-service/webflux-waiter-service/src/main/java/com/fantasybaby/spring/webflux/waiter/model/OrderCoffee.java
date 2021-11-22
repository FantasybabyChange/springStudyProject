package com.fantasybaby.spring.webflux.waiter.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Table("t_order_coffee")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderCoffee implements Serializable {
    private Long coffeeOrderId;
    private Long itemsId;
}
