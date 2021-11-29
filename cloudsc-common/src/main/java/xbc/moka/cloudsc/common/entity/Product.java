package xbc.moka.cloudsc.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.*;

/**
 * t_product
 * @author 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    private String prodCode;

    private String prodName;

    private Integer amount;

    private BigDecimal price;

    private static final long serialVersionUID = 1L;
}