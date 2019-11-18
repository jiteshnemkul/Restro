package com.jit.resto.repository;

import com.jit.resto.model.Ordertable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderTableRepository extends JpaRepository<Ordertable,Long> {

    public List<Ordertable> findAllByOrderStatus(String status);

}
