package xbc.moka.cloudsc.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import xbc.moka.cloudsc.common.entity.Product;
import xbc.moka.cloudsc.common.enums.CloudScEnum;
import xbc.moka.cloudsc.common.exception.CloudScException;
import xbc.moka.cloudsc.product.mapper.ProductMapper;
import xbc.moka.cloudsc.product.service.ProductService;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Product selectByCode(String code) throws CloudScException {
        if (!StringUtils.hasText(code)) {
            throw new CloudScException(CloudScEnum.FIELD_REQUIRED);
        }
        Product product = productMapper.selectByCode(code);
        return product;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
    public void deduce(String prodCode, Integer count) {
        Product product = productMapper.selectByCode(prodCode);
        if(product.getAmount() < count){
            throw new RuntimeException("不要意思欸，库存不足哦！");
        }
        product.setAmount(product.getAmount() - count);
        productMapper.updateByProdCode(product);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Integer updateProduct(Product product) {
        return Optional.ofNullable(product)
                .filter(product1 -> StringUtils.hasText(product1.getProdCode()))
                .map(item -> productMapper.updateByProdCode(item)).get();
    }

    @Override
    @Transactional(rollbackFor = SQLException.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Integer insertProduct(Product product) {
        return Optional.of(product)
                .map(item -> productMapper.insertSelective(product)).get();
    }

    @Override
    @Transactional(rollbackFor = SQLException.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public int deleteProduct(String prodCode) {
        if (StringUtils.hasText(prodCode)) {
            return productMapper.deleteByProdCode(prodCode);
        }
        return 0;
    }

}
