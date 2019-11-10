package com.jit.resto.model;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import javax.persistence.Column;
import javax.persistence.Entity;

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

    @Column(nullable = true, length = 40)
    private long Oid;



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

    public long getOid() {
        return Oid;
    }

    public void setOid(long oid) {
        Oid = oid;
    }
}
