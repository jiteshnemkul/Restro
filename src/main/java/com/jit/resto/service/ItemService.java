package com.jit.resto.service;

import com.jit.resto.model.ItemEntity;
import com.jit.resto.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService extends AbstractService<ItemEntity,Long> {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    protected JpaRepository<ItemEntity, Long> getRepository() {
        return itemRepository;
    }

    public List<ItemEntity> findAll(){
        return itemRepository.findAll();
    }

    public List<ItemEntity> findBycategoryid(Long categoryid){
return itemRepository.findByCategoryid(categoryid);
    }

    public  ItemEntity findByItemName(String name){
        return itemRepository.findAllByItemname(name).get(0);
    }
}
