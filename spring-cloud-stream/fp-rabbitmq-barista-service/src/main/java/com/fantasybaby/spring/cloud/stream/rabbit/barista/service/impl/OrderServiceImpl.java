package com.fantasybaby.spring.cloud.stream.rabbit.barista.service.impl;

import com.fantasybaby.spring.cloud.stream.rabbit.barista.model.CoffeeOrder;
import com.fantasybaby.spring.cloud.stream.rabbit.barista.model.OrderState;
import com.fantasybaby.spring.cloud.stream.rabbit.barista.repository.CoffeeOrderRepository;
import com.fantasybaby.spring.cloud.stream.rabbit.barista.service.IOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Slf4j
public class OrderServiceImpl implements IOrderService {
    @Resource
    private CoffeeOrderRepository orderRepository;
    @Value("${order.barista-prefix}${random.uuid}")
    private String barista;

    @Override
    @Transactional
    public void getCoffeeOrder(Long id) {
        CoffeeOrder o = orderRepository.getById(id);
        if (o == null) {
            log.warn("Order id {} is NOT valid.", id);
        }
        log.info("Receive a new Order {}. Waiter: {}. Customer: {}",
                id, o.getWaiter(), o.getCustomer());
        o.setState(OrderState.BREWED);
        o.setBarista(barista);
        orderRepository.save(o);
    }
}
