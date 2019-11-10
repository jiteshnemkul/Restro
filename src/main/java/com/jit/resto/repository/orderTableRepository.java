package com.jit.resto.repository;

import com.jit.resto.model.ordertable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface orderTableRepository extends JpaRepository<ordertable,Long> {

}
