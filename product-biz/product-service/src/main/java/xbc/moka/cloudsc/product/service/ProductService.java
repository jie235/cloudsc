package xbc.moka.cloudsc.product.service;

import xbc.moka.cloudsc.common.entity.Product;
import xbc.moka.cloudsc.common.exception.CloudScException;

public interface ProductService {

    /**
     * 根据acctCode查询产品
     *
     * @param code
     * @return
     * @throws CloudScException
     */
    Product selectByCode(String code) throws CloudScException;

    /**
     * 更新产品信息
     *
     * @param product
     * @return
     */
    Integer updateProduct(Product product);

    /**
     * 插入新的产品信息
     *
     * @param product
     * @return
     */
    Integer insertProduct(Product product);

    /**
     * 删除某个产品信息
     *
     * @param prodCode
     * @return
     */
    int deleteProduct(String prodCode);
}
