package xbc.moka.cloudsc.feign.account;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xbc.moka.cloudsc.common.dto.AccountDTO;
import xbc.moka.cloudsc.common.entity.Account;
import xbc.moka.cloudsc.common.enums.ValidGroup;
import xbc.moka.cloudsc.common.exception.CloudScException;
import xbc.moka.cloudsc.common.rsp.ResultData;

import java.math.BigDecimal;

@FeignClient(name = "account-service")
@RequestMapping("/account")//feign的接口必须和service的接口地址完全一样。为了不改动AccountController，在这里加上 RequestMapping
public interface AccountFeign {
    @GetMapping("/{acctCode}")
    @ApiOperation(value = "通过账户名查询账户")
    public ResultData<Account> getByCode(@ApiParam(value = "账户名，非空") @PathVariable(value = "acctCode") String acctCode) throws CloudScException;

    @GetMapping("pay")
    @ApiOperation(value = "扣减账户金额")
    public ResultData<String> reduce(@RequestParam("acctCode") String acctCode, @RequestParam("amount") BigDecimal amount);

    @PutMapping("update")
    @ApiOperation(value = "通过账户名更新账户信息")
    public ResultData<Integer> updateAccount(@Validated(value = ValidGroup.Crud.Update.class) @RequestBody AccountDTO accountDTO);

    @PostMapping("/add")
    @ApiOperation(value = "新增账户")
    public ResultData<Integer> addAccount(@Validated(value = ValidGroup.Crud.Create.class) @RequestBody AccountDTO accountDTO);

    @DeleteMapping("/del/{acctCode}")
    @ApiOperation(value = "通过账户名删除账户")
    public ResultData<Integer> delAccount(@ApiParam(value = "账户名，非空") @PathVariable(value = "acctCode") String acctCode);

}
