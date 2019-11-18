package com.jit.resto.service;

import com.jit.resto.model.Orderentity;
import com.jit.resto.model.Ordertable;
import com.jit.resto.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService extends AbstractService<Orderentity,Long> {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    protected JpaRepository<Orderentity, Long> getRepository() {
        return orderRepository;
    }

    public void saveAll(List<Orderentity> orderentities){
        orderRepository.save(orderentities);
    }

    public List<Orderentity> findAllByOrderTable(Ordertable ordertable){
        return orderRepository.findAllByOrdertable(ordertable);
    }

    public List<Orderentity> findAllByOrderStatusAndOrderTable(String status,Ordertable ordertable){
        return orderRepository.findAllByOrderStatusAndOrdertable(status,ordertable);
    }
}
