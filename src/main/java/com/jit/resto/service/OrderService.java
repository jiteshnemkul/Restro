package com.jit.resto.service;

import com.jit.resto.model.Orderentity;
import com.jit.resto.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService extends AbstractService<Orderentity,Long> {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    protected JpaRepository<Orderentity, Long> getRepository() {
        return orderRepository;
    }
}
