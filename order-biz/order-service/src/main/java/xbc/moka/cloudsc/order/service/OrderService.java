package xbc.moka.cloudsc.order.service;

import xbc.moka.cloudsc.common.entity.Order;
import xbc.moka.cloudsc.common.exception.CloudScException;

public interface OrderService {

    /**
     * 根据orderNo查询产品
     *
     * @param orderNo
     * @return
     * @throws CloudScException
     */
    Order selectByOrderNo(String orderNo) throws CloudScException;

    void createOrder(Order order);

    /**
     * 更新产品信息
     *
     * @param order
     * @return
     */
    Integer updateOrder(Order order);

    /**
     * 插入新的产品信息
     *
     * @param order
     * @return
     */
    Integer insertOrder(Order order);

    /**
     * 删除某个产品信息
     *
     * @param orderNo
     * @return
     */
    int deleteOrder(String orderNo);
}
