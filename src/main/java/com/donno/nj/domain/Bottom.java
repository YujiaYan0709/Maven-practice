package com.donno.nj.domain;


import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Date;

public class Bottom implements Serializable {

    private Long id;
    private String name;
    private String facture;
    private int size;
    private String location;
    private Date createTime;
    private Date updateTime;

    public Bottom() {

    }
    public int getSize() {return size;}

    public void setSize(int size) {this.size = size;}

    public String getLocation() {return location;}

    public void setLocation(String location) {this.location = location;}

    public String getFacture() {return facture;}

    public void setFacture(String facture) {this.facture = facture;}

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
                .add("facture", facture)
                .add("size", size)
                .add("location", location)
                .add("createTime", createTime)
                .add("updateTime", updateTime)
                .toString();
    }
}