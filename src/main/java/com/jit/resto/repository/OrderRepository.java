package com.jit.resto.repository;

import com.jit.resto.model.Orderentity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Orderentity,Long> {

}
