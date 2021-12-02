package xbc.moka.cloudsc.product.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "产品微服务接口")
public class ProductController implements ProductFeign {

    @Autowired
    private ProductService productService;

    @Override
    @ApiOperation(value = "根据产品代码查询品种详细信息")
    //name -> 参数名, value -> 参数说明, required -> 参数是否必传, paramType -> 参数放在那个地方
    //paramType 的取值：header -> 对应@RequestHeader, query -> 对应@RequestParam, path -> 对应@PathVariable
    //                  body -> 对应@RequestBody, form -> 对应表单提交
    @ApiImplicitParam(name = "prodCode", required = true, value = "产品代码", paramType = "query")
    public Product getByCode(@PathVariable(value = "prodCode") String prodCode) throws CloudScException {
        log.info("get product detail: {}", prodCode);
        return productService.selectByCode(prodCode);
    }

    @Override
    public void reduce(String prodCode, Integer count) {
        log.info("Preparing deduce product amount");
        productService.deduce(prodCode, count);
    }

    @Override
    public Integer addProduct(@RequestBody Product product) {
        log.info("add product: {}", product);
        return productService.insertProduct(product);
    }

    @Override
    public Integer update(@RequestBody Product product) {
        log.info("update product: {}", product);
        return productService.updateProduct(product);
    }

    @Override
    public int del(@PathVariable(value = "prodCode") String prodCode) {
        log.info("delete product: {}", prodCode);
        return productService.deleteProduct(prodCode);
    }

}
