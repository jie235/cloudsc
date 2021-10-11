package xbc.moka.cloudsc.acct.mapper;

import xbc.moka.cloudsc.common.entity.Account;

public interface AccountMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Account record);

    int insertSelective(Account record);

    Account selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    /**
     * 根据acctCode查询账户信息
     *
     * @param acctCode
     * @return
     */
    Account selectByCode(String acctCode);

    /**
     * 通过acctCode更新账户信息
     *
     * @param account
     * @return
     */
    int updateByAcctCode(Account account);

    /**
     * 通过acctCode删除账户
     *
     * @param acctCode
     * @return
     */
    int deleteByAcctCode(String acctCode);

}