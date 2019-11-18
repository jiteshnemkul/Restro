package com.jit.resto.repository;

import com.jit.resto.model.TableEntity;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<TableEntity,Long> {

    List<TableEntity> findByStatus(boolean stat);

    @Transactional
    @Modifying
    @Query(value = "update TableEntity t set t.status=?2 where t.Tablename=?1")
    void updateTableReservasion(String tableName, Boolean status);
}
