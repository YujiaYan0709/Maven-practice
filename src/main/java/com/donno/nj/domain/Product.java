package com.donno.nj.domain;


import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Date;

public class Product implements Serializable {

    private Long id;
    private String name;
    private Double price;
    private Date produceTime;
    private Date bestBy;
    private Date createTime;
    private Date updateTime;

    public Product() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Date getProduceTime() {
        return produceTime;
    }

    public void setProduceTime(Date produceTime) {
        this.produceTime = produceTime;
    }

    public Date getBestBy() {
        return bestBy;
    }

    public void setBestBy(Date bestBy) {
        this.bestBy = bestBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("price", price)
                .add("produceTime", produceTime)
                .add("bestBy", bestBy)
                .add("createTime", createTime)
                .add("updateTime", updateTime)
                .toString();
    }
}