package xbc.moka.cloudsc.common.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.validation.annotation.Validated;

/**
 * t_account
 * @author 
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel
@Validated
public class Account implements Serializable {
    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", acctCode='" + acctCode + '\'' +
                ", acctName='" + acctName + '\'' +
                ", amount=" + amount +
                ", testBasicType=" + testBasicType +
                '}';
    }

    @ApiModelProperty(value = "id")
    private Integer id;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "账号")
    private String acctCode;

    @ApiModelProperty(value = "用户名")
    private String acctName;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "基本类型测试")
    private int testBasicType;

    private static final long serialVersionUID = 1L;
}