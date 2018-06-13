package com.donno.nj.dao;


import com.donno.nj.dao.base.BaseDao;
import com.donno.nj.domain.Product;

public interface ProductDao extends BaseDao<Product> {

    Product findByName(String userName);

}
