package xbc.moka.cloudsc.product.mapper;

import xbc.moka.cloudsc.common.entity.Product;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    /**
     * 根据prodCode查询产品信息
     *
     * @param prodCode
     * @return
     */
    Product selectByCode(String prodCode);

    /**
     * 通过prodCode更新产品信息
     *
     * @param prodCode
     * @return
     */
    int deleteByProdCode(String prodCode);

    int updateByProdCode(Product product);
}