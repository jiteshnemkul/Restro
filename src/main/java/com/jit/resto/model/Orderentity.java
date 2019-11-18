package com.jit.resto.model;

import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Orderentity extends  AbstractModel<Long>{

    @Column(nullable = false, length = 40)
    private String itemname;

    @Column(nullable = false, length = 40)
    private int number;

    @Column(nullable = false, length = 40)
    private int price;

    @Column(nullable = true, length = 40)
    private int total;

    @Column(nullable = true)
    private String orderStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "Oid", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Ordertable ordertable;



    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    /*public long getOid() {
        return Oid;
    }

    public void setOid(long oid) {
        Oid = oid;
    }*/

    public Ordertable getOrdetable() {
        return ordertable;
    }

    public void setOrdertable(Ordertable orderMasterEntity) {
        this.ordertable = orderMasterEntity;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Ordertable getOrdertable() {
        return ordertable;
    }
}
