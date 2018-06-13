package com.donno.nj.service;

import com.donno.nj.domain.Product;
import com.google.common.base.Optional;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
public interface ProductService {

    Optional<Product> findProductByName(String name);

    List<Product> retrieveProducts(Map params);

    Integer count(Map params);

    Product createProduct(Product product);

    Optional<Product> findById(Long id);

    void updateProduct(Product product, Product newProduct);

    void deleteProduct(Product Product);
}
