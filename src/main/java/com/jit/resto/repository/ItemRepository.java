package com.jit.resto.repository;

import com.jit.resto.model.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity,Long> {


    List<ItemEntity> findByCategoryid(Long id);
    List<ItemEntity> findAllByItemname(String itemName);

}
