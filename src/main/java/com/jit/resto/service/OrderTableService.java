package com.jit.resto.service;

import com.jit.resto.model.Ordertable;
import com.jit.resto.repository.OrderTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderTableService extends AbstractService<Ordertable,Long> {
    @Autowired
    private OrderTableRepository orderTableRepository;


    @Override
    protected JpaRepository<Ordertable, Long> getRepository() {
        return orderTableRepository;
    }

    public List<Ordertable> findAllBtOrderStatus(String status){
        return orderTableRepository.findAllByOrderStatus(status);
    }



}
