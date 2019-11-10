package com.jit.resto.service;

import com.jit.resto.model.CategoryEntity;
import com.jit.resto.model.TableEntity;
import com.jit.resto.repository.TableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TableService extends AbstractService<TableEntity,Long> {
    @Autowired
    private TableRepository tableRepository;


    @Override
    protected JpaRepository<TableEntity, Long> getRepository() {
        return tableRepository;
    }

    public List<TableEntity> findAll(){
        return tableRepository.findAll();
    }

    public List<TableEntity> getFreetable(boolean stat){
        return tableRepository.findByStatus(stat);
    }
}
