package xbc.moka.cloudsc.common.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import xbc.moka.cloudsc.common.enums.ValidGroup;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    @ApiModelProperty(value = "id")
    @Null(groups = ValidGroup.Crud.Create.class, message = "id必须为空")
    private Integer id;

    @ApiModelProperty(value = "邮箱")
    @NotBlank(groups = ValidGroup.Crud.Create.class)
    private String email;

    @ApiModelProperty(value = "账号")
    @NotNull(groups = ValidGroup.Crud.Create.class, message = "账号不能为空")
    private String acctCode;

    @ApiModelProperty(value = "用户名")
    private String acctName;

    @ApiModelProperty(value = "金额")
    private BigDecimal amount;

    @ApiModelProperty(value = "基本类型测试")
    private int testBasicType;

    private static final long serialVersionUID = 1L;
}
