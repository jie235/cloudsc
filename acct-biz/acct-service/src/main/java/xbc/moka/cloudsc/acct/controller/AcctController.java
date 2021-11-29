package xbc.moka.cloudsc.acct.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xbc.moka.cloudsc.acct.service.AcctService;
import xbc.moka.cloudsc.common.dto.AccountDTO;
import xbc.moka.cloudsc.common.entity.Account;
import xbc.moka.cloudsc.common.enums.ValidGroup;
import xbc.moka.cloudsc.common.exception.CloudScException;

@RestController
@RequestMapping("/account")
@Slf4j
@Api(tags = "账户相关接口")
public class AcctController {

    @Autowired
    private AcctService acctService;

    @GetMapping("/{acctCode}")
    @ApiOperation(value = "通过账户名查询账户")
    @SentinelResource(value = "acctGetAccountByCode")
    public Account getByCode(@ApiParam(value = "账户名，非空") @PathVariable(value = "acctCode") String acctCode) throws CloudScException {
        log.info("get account detail, acctCode is: {}", acctCode);
        Account account = acctService.selectByCode(acctCode);
        System.out.println(account);
        return account;
    }

    @PutMapping("update")
    @ApiOperation(value = "通过账户名更新账户信息")
    public Integer updateAccount(@Validated(value = ValidGroup.Crud.Update.class) @RequestBody AccountDTO accountDTO){
        log.info("update account: {}", accountDTO);
        return acctService.updateAccount(accountDTO);
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增账户")
    public Integer addAccount(@Validated(value = ValidGroup.Crud.Create.class) @RequestBody AccountDTO accountDTO){
        log.info("insert account: {}", accountDTO);
        return acctService.insertAccount(accountDTO);
    }

    @DeleteMapping("/del/{acctCode}")
    @ApiOperation(value = "通过账户名删除账户")
    public int delAccount(@ApiParam(value = "账户名，非空") @PathVariable(value = "acctCode") String acctCode){
        return acctService.deleteAccount(acctCode);
    }


}
