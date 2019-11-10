package com.jit.resto.service;

import com.jit.resto.model.CategoryEntity;
import com.jit.resto.model.Customers;
import com.jit.resto.model.ItemEntity;
import com.jit.resto.repository.CategoryRepository;
import com.jit.resto.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService extends AbstractService<CategoryEntity, Long> {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    protected JpaRepository<CategoryEntity, Long> getRepository() {
        return categoryRepository;
    }

    public List<CategoryEntity> findAll(){
        return categoryRepository.findAll();
    }

public Long getcategoryid(String name) {
    Long categoryid=categoryRepository.findByName(name);
    return categoryid;
}


}
