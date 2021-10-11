package xbc.moka.cloudsc.product.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import xbc.moka.cloudsc.common.entity.Product;
import xbc.moka.cloudsc.common.exception.CloudScException;
import xbc.moka.cloudsc.feign.product.ProductFeign;
import xbc.moka.cloudsc.product.service.ProductService;

@RestController
@Slf4j
//@RequestMapping("/product")这个注解在此处就无关紧要了，因为在feign上已经使用过
public class ProductController implements ProductFeign {

    @Autowired
    private ProductService productService;

    public Product getByCode(@PathVariable(value = "prodCode") String prodCode) throws CloudScException {
        log.info("get product detail: {}", prodCode);
        return productService.selectByCode(prodCode);
    }

    public Integer addProduct(@RequestBody Product product) {
        log.info("add product: {}", product);
        return productService.insertProduct(product);
    }

    public Integer update(@RequestBody Product product) {
        log.info("update product: {}", product);
        return productService.updateProduct(product);
    }

    public int del(@PathVariable(value = "prodCode") String prodCode) {
        log.info("delete product: {}", prodCode);
        return productService.deleteProduct(prodCode);
    }

}
