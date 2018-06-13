package com.donno.nj.controller;

import com.donno.nj.aspect.OperationLog;
import com.donno.nj.constant.Constant;
import com.donno.nj.domain.Product;
import com.donno.nj.logger.BusinessLogger;
import com.donno.nj.logger.DebugLogger;
import com.donno.nj.logger.OpLogger;
import com.donno.nj.representation.ListRep;
import com.donno.nj.service.ProductService;
import com.google.common.base.Optional;
import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

import static com.donno.nj.util.ParamMapBuilder.paginationParams;
import static com.google.common.collect.Maps.newHashMap;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/api/products", method = RequestMethod.GET, produces = "application/json")
    @OperationLog(desc = "获取用户列表")
    public ResponseEntity retrieveProducts(@RequestParam(value = "name", defaultValue = "") String productName,
                                        @RequestParam(value = "roles", defaultValue = "") String roles,
                                        @RequestParam(value = "orderBy", defaultValue = "updateTime") String orderBy,
                                        @RequestParam(value = "order", defaultValue = "desc") String order,
                                        @RequestParam(value = "pageSize", defaultValue = Constant.PAGE_SIZE) Integer pageSize,
                                        @RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo) {
        BusinessLogger.log("business");
        OpLogger.log("op log");
        DebugLogger.log("debug");

        Map params = newHashMap(ImmutableMap.of("name", productName));
        params.putAll(paginationParams(pageNo, pageSize, orderBy, order));

        List<Product> products = productService.retrieveProducts(params);
        Integer total = productService.count(params);


        return ResponseEntity.ok(ListRep.assemble(products, total));
    }

    @OperationLog(desc = "创建用户")
    @RequestMapping(value = "/api/products", method = RequestMethod.POST)
    public ResponseEntity createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {

        if (productService.findProductByName(product.getName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Product newProduct = productService.createProduct(product);
        URI uri = ucBuilder.path("/api/products/{id}").buildAndExpand(newProduct.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/api/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> getUser(@PathVariable("id") Long id) {

        Optional<Product> curProduct = productService.findById(id);
        if (curProduct.isPresent()) {
            return ResponseEntity.ok(curProduct.get());
        }
        return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
    }

    @OperationLog(desc = "修改用户信息")
    @RequestMapping(value = "/api/products/{id}", method = RequestMethod.PUT)
    public ResponseEntity updateProduct(@PathVariable("id") Long id, @RequestBody Product newProduct) {

        Optional<Product> curProduct = productService.findById(id);
        if (curProduct.isPresent()) {
            productService.updateProduct(curProduct.get(), newProduct);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @OperationLog(desc = "删除用户信息")
    @RequestMapping(value = "/api/products/{id}", method = RequestMethod.DELETE)
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {

        Optional<Product> curProduct = productService.findById(id);
        if (curProduct.isPresent()) {
            productService.deleteProduct(curProduct.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
