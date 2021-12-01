package xbc.moka.cloudsc.acct.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import xbc.moka.cloudsc.acct.mapper.AccountMapper;
import xbc.moka.cloudsc.acct.service.AcctService;
import xbc.moka.cloudsc.common.dto.AccountDTO;
import xbc.moka.cloudsc.common.entity.Account;
import xbc.moka.cloudsc.common.enums.CloudScEnum;
import xbc.moka.cloudsc.common.exception.CloudScException;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;

@Service
public class AcctServiceImpl implements AcctService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Account selectByCode(String code) throws CloudScException {
        if (!StringUtils.hasText(code)) {
            throw new CloudScException(CloudScEnum.FIELD_REQUIRED);
        }
        Account account = accountMapper.selectByCode(code);
        return account;
    }

    @Override
    @Transactional(rollbackFor = {RuntimeException.class, SQLException.class})
    public void reduce(String acctCode, BigDecimal amount){
        Account account = accountMapper.selectByCode(acctCode);
        if(account.getAmount().compareTo(amount) < 0){
            throw new RuntimeException("账户余额不够啦！");
        }
        account.setAmount(account.getAmount().subtract(amount));
        accountMapper.updateByAcctCode(account);
    }

    @Override
    @Transactional(rollbackFor = SQLException.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Integer updateAccount(AccountDTO accountDTO) {
        Account account = Optional.of(accountDTO).map(u -> {
            Account acct = new Account();
            BeanUtils.copyProperties(u, acct);
            return acct;
        }).get();
        return Optional.ofNullable(account)
                .filter(account1 -> StringUtils.hasText(account1.getAcctCode()))
                .map(item -> accountMapper.updateByAcctCode(item)).get();
    }

    @Override
    @Transactional(rollbackFor = SQLException.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Integer insertAccount(AccountDTO accountDTO) {
        Account account = Optional.of(accountDTO).map(u -> {
            Account acct = new Account();
            BeanUtils.copyProperties(u, acct);
            return acct;
        }).get();
        return Optional.ofNullable(account)
                .map(u -> accountMapper.insertSelective(u)).get();
    }

    @Override
    @Transactional(rollbackFor = SQLException.class, propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public int deleteAccount(String acctCode) {
        if (StringUtils.hasText(acctCode)) {
            return accountMapper.deleteByAcctCode(acctCode);
        }
        return 0;
    }
}
