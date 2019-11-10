package com.jit.resto.service;

import com.jit.resto.model.ordertable;
import com.jit.resto.repository.orderTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class orderTableService extends AbstractService<ordertable,Long> {
    @Autowired
    private orderTableRepository orderTableRepository;


    @Override
    protected JpaRepository<ordertable, Long> getRepository() {
        return orderTableRepository;
    }
}
