package com.jit.resto.repository;

import com.jit.resto.model.Orderentity;
import com.jit.resto.model.Ordertable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Orderentity,Long> {
    List<Orderentity> findAllByOrdertable(Ordertable ordertable);

    List<Orderentity>findAllByOrderStatusAndOrdertable(String status,Ordertable ordertable);

}
