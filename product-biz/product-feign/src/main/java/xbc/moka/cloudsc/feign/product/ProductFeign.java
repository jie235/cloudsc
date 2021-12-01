package xbc.moka.cloudsc.feign.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import xbc.moka.cloudsc.common.entity.Product;
import xbc.moka.cloudsc.common.exception.CloudScException;

@FeignClient(name = "product-service")
@RequestMapping("/product")
public interface ProductFeign {

    @GetMapping("/{prodCode}")
    public Product getByCode(@PathVariable(value = "prodCode") String prodCode) throws CloudScException;

    @GetMapping("reduceProduct")
    public void reduce(@RequestParam("prodCode") String prodCode, @RequestParam("count") Integer count);


    @PostMapping("/add")
    public Integer addProduct(@RequestBody Product product) ;

    @PutMapping("update")
    public Integer update(@RequestBody Product product) ;

    @DeleteMapping("/del/{prodCode}")
    public int del(@PathVariable(value = "prodCode") String prodCode) ;
}
