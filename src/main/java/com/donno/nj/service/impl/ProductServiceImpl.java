package com.donno.nj.service.impl;

import com.donno.nj.dao.ProductDao;
import com.donno.nj.domain.Product;
import com.donno.nj.service.ProductService;
import com.google.common.base.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public Optional<Product> findProductByName(String name) {
        return Optional.fromNullable(productDao.findByName(name));
    }

    @Override
    public List<Product> retrieveProducts(Map params) {
        return productDao.getList(params);
    }

    @Override
    public Integer count(Map params) {
        return productDao.count(params);
    }

    @Override
    public Product createProduct(Product product) {
        productDao.insert(product);
        return product;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return Optional.fromNullable(productDao.findById(id));
    }

    @Override
    public void updateProduct(Product product, Product newProduct) {
        product.setName(newProduct.getName());
        productDao.update(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productDao.delete(product.getId());
    }

}