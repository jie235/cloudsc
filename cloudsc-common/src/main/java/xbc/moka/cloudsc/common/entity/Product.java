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
    private Integer id;

    private String prodCode;

    private String prodName;

    private Integer amount;

    private BigDecimal price;

    private static final long serialVersionUID = 1L;
}