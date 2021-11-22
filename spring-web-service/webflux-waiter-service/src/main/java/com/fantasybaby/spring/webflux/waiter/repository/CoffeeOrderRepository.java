package com.fantasybaby.spring.webflux.waiter.repository;

import com.fantasybaby.spring.webflux.waiter.model.Coffee;
import com.fantasybaby.spring.webflux.waiter.model.CoffeeOrder;
import com.fantasybaby.spring.webflux.waiter.model.OrderCoffee;
import com.fantasybaby.spring.webflux.waiter.model.OrderState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

@Repository
public class CoffeeOrderRepository {
    @Autowired
    private DatabaseClient databaseClient;
    @Autowired
    private R2dbcEntityTemplate r2dbcEntityTemplate;

    public Mono<CoffeeOrder> get(Long id) {
        Mono<CoffeeOrder> coffeeOrderMono = databaseClient
                .sql("select * from t_order where id = " + id)
                .map((r, rm) ->
                        CoffeeOrder.builder()
                                .id(id)
                                .customer(r.get("customer", String.class))
                                .state(OrderState.values()[r.get("state", Integer.class)])
                                .createTime(r.get("create_time", Date.class))
                                .updateTime(r.get("update_time", Date.class))
                                .items(new ArrayList<>())
                                .build()
                )
                .first()
                .flatMap(o ->
                        databaseClient
                                .sql("select c.* from t_coffee c, t_order_coffee oc " +
                                        "where c.id = oc.items_id and oc.coffee_order_id = " + id).map(a -> (Coffee) a).all().collect(Collectors.toList())
                                .flatMap(l -> {
                                    o.getItems().addAll(l);
                                    return Mono.just(o);
                                })
                );
        return coffeeOrderMono;
    }

    public Mono<Long> save(CoffeeOrder order) {
        return r2dbcEntityTemplate.insert(CoffeeOrder.class).using(order)
                .flatMap(m -> Flux.fromIterable(order.getItems())
                        .flatMap(c -> r2dbcEntityTemplate.insert(OrderCoffee.class).using(new OrderCoffee(m.getId(), c.getId()))
                                .then()).then(Mono.just(m.getId())));
    }
}
