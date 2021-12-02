package xbc.moka.cloudsc.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * t_product
 * @author 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "产品封装类Product", description = "产品相关信息封装，用于接口传参")
public class Product implements Serializable {
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", prodCode='" + prodCode + '\'' +
                ", prodName='" + prodName + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                '}';
    }

    private Integer id;

    @ApiModelProperty(value = "产品代码")
    private String prodCode;

    private String prodName;

    private Integer amount;

    private BigDecimal price;

    private static final long serialVersionUID = 1L;
}