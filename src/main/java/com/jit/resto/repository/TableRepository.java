package com.jit.resto.repository;

import com.jit.resto.model.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<TableEntity,Long> {

    List<TableEntity> findByStatus(boolean stat);
}
