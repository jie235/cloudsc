package xbc.moka.cloudsc.common.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * t_order
 *
 * @author
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {
    private Integer id;

    private String orderNo;

    private String acctCode;

    private String prodCode;

    private Integer cnt;

    private BigDecimal amount;

    private static final long serialVersionUID = 1L;
}