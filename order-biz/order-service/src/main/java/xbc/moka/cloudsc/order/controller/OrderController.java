package xbc.moka.cloudsc.order.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.reflect.MethodDelegate;
import org.springframework.web.bind.annotation.*;
import xbc.moka.cloudsc.common.dto.AccountDTO;
import xbc.moka.cloudsc.common.entity.Account;
import xbc.moka.cloudsc.common.entity.Order;
import xbc.moka.cloudsc.common.entity.Product;
import xbc.moka.cloudsc.common.exception.CloudScException;
import xbc.moka.cloudsc.common.rsp.ResultData;
import xbc.moka.cloudsc.feign.account.AccountFeign;
import xbc.moka.cloudsc.feign.product.ProductFeign;
import xbc.moka.cloudsc.order.service.OrderService;

import javax.xml.transform.Result;
import java.math.BigDecimal;
import java.security.KeyStore;
import java.util.UUID;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private AccountFeign accountFeign;

    @Autowired
    private ProductFeign productFeign;

    @PostMapping("create")
    public void create(@RequestBody Order order) throws CloudScException {
        log.info("create order: {}", order);
        order.setOrderNo(UUID.randomUUID().toString());
        String prodCode = order.getProdCode();
        Product byCode = productFeign.getByCode(prodCode);
        order.setAmount(byCode.getPrice().multiply(new BigDecimal(order.getCnt())));
        orderService.createOrder(order);
    }

    @PostMapping("/insertAccount")
    public ResultData<Integer> add(@RequestBody AccountDTO accountDTO){
        return accountFeign.addAccount(accountDTO);
    }

    @PutMapping("/updateAccount")
    public ResultData<Integer> update(@RequestBody AccountDTO accountDTO){
        return accountFeign.updateAccount(accountDTO);
    }

    @DeleteMapping("/deleteAccount/{acctCode}")
    public ResultData<Integer> del(@PathVariable(value = "acctCode") String acctCode){
        return accountFeign.delAccount(acctCode);
    }

    //接口存在问题：在accountService中，统一处理了返回值，而这里用了Account接收。（但是用Object接收也有问题）
    @GetMapping("/getAccount")
    public ResultData<Account> getPage(@RequestParam String acctCode) throws CloudScException {
        ResultData<Account> byCode = accountFeign.getByCode(acctCode);
        System.out.println(byCode.toString());
        return byCode;
    }

    @GetMapping("/getProduct")
    public Object getProduct(@RequestParam String prodCode) throws CloudScException {
        Product byCode = productFeign.getByCode(prodCode);
        System.out.println(byCode);
        return byCode;
    }

    @GetMapping("/{orderNo}")
    public Order getByOrderNo(@PathVariable(value = "orderNo") String orderNo) throws CloudScException {
        log.info("get order detail: {}", orderNo);
        return orderService.selectByOrderNo(orderNo);
    }

    @PutMapping("/update")
    public Integer updateOrder(@RequestBody Order order) {
        log.info("update ordre: {}", order);
        return orderService.updateOrder(order);
    }

    @PostMapping("/add")
    public Integer addOrder(@RequestBody Order order) {
        log.info("add order: {}", order);
        return orderService.insertOrder(order);
    }

    @DeleteMapping("/del/{orderNo}")
    public Integer delOrder(@PathVariable(value = "orderNo") String orderNo) {
        log.info("delete order: {}", orderNo);
        return orderService.deleteOrder(orderNo);
    }

}
