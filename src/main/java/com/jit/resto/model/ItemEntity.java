package com.jit.resto.model;

import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
@Component
@Scope(value="session")
@Entity
public class ItemEntity extends AbstractModel<Long> {


    @Column(name = "itemname")
    private String itemname;
    @Column(name = "description")
    private String description;

    @Column(name = "categoryid")
    private Long categoryid;
    @Column(name = "price")
    private String price;
    @Column(name = "createdate")
    private String createdDate;



    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Long categoryid) {
        this.categoryid = categoryid;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
}
