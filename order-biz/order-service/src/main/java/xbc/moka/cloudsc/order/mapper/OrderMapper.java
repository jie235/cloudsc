package xbc.moka.cloudsc.order.mapper;

import xbc.moka.cloudsc.common.entity.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    /**
     * 根据orderNo查询产品信息
     *
     * @param orderNo
     * @return
     */
    Order selectByOrderNo(String orderNo);

    /**
     * 通过prodCode更新产品信息
     *
     * @param order
     * @return
     */
    int updateByOrderNo(Order order);

    /**
     * 通过orderNo删除产品条目
     *
     * @param orderNo
     * @return
     */
    int deleteByOrderNo(String orderNo);
}