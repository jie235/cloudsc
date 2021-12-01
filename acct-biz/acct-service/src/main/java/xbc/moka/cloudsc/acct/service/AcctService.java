package xbc.moka.cloudsc.acct.service;

import xbc.moka.cloudsc.common.dto.AccountDTO;
import xbc.moka.cloudsc.common.entity.Account;
import xbc.moka.cloudsc.common.exception.CloudScException;

import java.math.BigDecimal;

public interface AcctService {
    /**
     * 根据acctCode查询账户
     * @param code
     * @return
     * @throws CloudScException
     */
    Account selectByCode(String code) throws CloudScException;

    void reduce(String acctCode, BigDecimal amount);

    /**
     * 更新账户
     * @param account
     * @return
     */
    Integer updateAccount(AccountDTO account);

    /**
     * 插入新账户
     * @param account
     * @return
     */
    Integer insertAccount(AccountDTO account);

    /**
     * 删除账户
     * @param acctCode
     * @return
     */
    int deleteAccount(String acctCode);
}
