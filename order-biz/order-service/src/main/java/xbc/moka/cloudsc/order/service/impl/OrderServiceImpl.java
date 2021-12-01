package xbc.moka.cloudsc.order.service.impl;

import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import xbc.moka.cloudsc.common.entity.Order;
import xbc.moka.cloudsc.common.enums.CloudScEnum;
import xbc.moka.cloudsc.common.exception.CloudScException;
import xbc.moka.cloudsc.feign.account.AccountFeign;
import xbc.moka.cloudsc.feign.product.ProductFeign;
import xbc.moka.cloudsc.order.mapper.OrderMapper;
import xbc.moka.cloudsc.order.service.OrderService;

import java.sql.SQLException;
import java.util.Optional;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private AccountFeign accountFeign;

    @Autowired
    private ProductFeign productFeign;

    @Override
    public Order selectByOrderNo(String orderNo) throws CloudScException {
        if (!StringUtils.hasText(orderNo)) {
            throw new CloudScException(CloudScEnum.FIELD_REQUIRED);
        }
        Order order = orderMapper.selectByOrderNo(orderNo);
        return order;
    }

    @GlobalTransactional(name = "tx_order_create")
    @Override
    public void createOrder(Order order){
        log.info("Preparing creating order");
        this.saveOrder(order);
        accountFeign.reduce(order.getAcctCode(), order.getAmount());
        productFeign.reduce(order.getProdCode(), order.getCnt());
        return;
    }

    @Transactional(rollbackFor = {SQLException.class, RuntimeException.class})
    void saveOrder(Order order){
        orderMapper.insert(order);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Integer updateOrder(Order order) {
        return Optional.ofNullable(order)
                .filter(order1 -> StringUtils.hasText(order1.getOrderNo()))
                .map(item -> orderMapper.updateByOrderNo(item)).get();
    }

    @Override
    @Transactional(rollbackFor = SQLException.class, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public Integer insertOrder(Order order) {
        return Optional.ofNullable(order)
                .map(item -> orderMapper.insertSelective(item)).get();
    }

    @Override
    @Transactional(rollbackFor = SQLException.class, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public int deleteOrder(String orderNo) {
        if (StringUtils.hasText(orderNo)) {
            return orderMapper.deleteByOrderNo(orderNo);
        }
        return 0;
    }
}
