package com.jit.resto.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Ordertable extends  AbstractModel<Long> {
    @Column
    private Float grandTotal;
    @Column
    private String orderStatus;
    @Column
    private String tableName;
    @Column
    private String orderDate;
    @Column
    private String orderTime;

    /*public List<Orderentity> getOrderentities() {
        return orderentities;
    }

    public void setOrderentities(List<Orderentity> orderentities) {
        this.orderentities = orderentities;
    }*/

    public Float getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(Float grandTotal) {
        this.grandTotal = grandTotal;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }


}
