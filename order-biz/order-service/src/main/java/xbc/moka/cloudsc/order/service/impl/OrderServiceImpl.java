package xbc.moka.cloudsc.order.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import xbc.moka.cloudsc.common.entity.Order;
import xbc.moka.cloudsc.common.enums.CloudScEnum;
import xbc.moka.cloudsc.common.exception.CloudScException;
import xbc.moka.cloudsc.order.mapper.OrderMapper;
import xbc.moka.cloudsc.order.service.OrderService;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order selectByOrderNo(String orderNo) throws CloudScException {
        if (!StringUtils.hasText(orderNo)) {
            throw new CloudScException(CloudScEnum.FIELD_REQUIRED);
        }
        Order order = orderMapper.selectByOrderNo(orderNo);
        return order;
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
