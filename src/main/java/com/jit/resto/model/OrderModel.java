package com.jit.resto.model;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import java.util.Collection;
import java.util.List;


public class OrderModel {
    Long id;
    Collection<Orderentity> orderentities;
    Float grandTotal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
