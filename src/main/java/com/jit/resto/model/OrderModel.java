package com.jit.resto.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collection;
import java.util.List;


public class OrderModel {

    Collection<Orderentity> orderentities;
    Float grandTotal;
    Long orderId;
    String orderTime;
    String tableName;
    Integer rowIndexForNewOrderDisplay;


    public Collection<Orderentity> getOrderentities() {
        return orderentities;
    }

    public void setOrderentities(Collection<Orderentity> orderentities) {
        this.orderentities = orderentities;
    }

    public Float getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Float grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getRowIndexForNewOrderDisplay() {
        return rowIndexForNewOrderDisplay;
    }

    public void setRowIndexForNewOrderDisplay(Integer rowIndexForNewOrderDisplay) {
        this.rowIndexForNewOrderDisplay = rowIndexForNewOrderDisplay;
    }
}
