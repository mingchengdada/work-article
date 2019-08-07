package com.mc.redis.configuration.mapper;

import com.mc.redis.configuration.bean.AcPersonalBook;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AcPersonalBookMapper {
    int deleteByPrimaryKey(Long id);

    int deleteBycurrencyTypeIdByUserId(@Param("isMulti") Integer isMulti
            , @Param("userId") String userId);

    /**
     * 账本迁移，物理删除老账本信息
     *
     * @param userId 用户编码
     * @param ismMulti 是否多账本
     * @return
     */
    int deleteRecordByUserId(@Param("userId") String userId, @Param("isMulti") Integer ismMulti);

    int insert(AcPersonalBook record);

    int insertSelective(AcPersonalBook record);

    AcPersonalBook selectByPrimaryKey(Long id);

    int updateBalanceByAcctBookId(@Param("balance") BigDecimal balance, @Param("acctBookId") String acctBookId);

    int updateByPrimaryKeySelective(AcPersonalBook record);

    int updateByPrimaryKeyWithBLOBs(AcPersonalBook record);

    int updateByPrimaryKey(AcPersonalBook record);

    int updateByAcctBookId(AcPersonalBook record);

    int updateByAcctBookIds(AcPersonalBook record);

    AcPersonalBook selectByAcctBookId(String acctBookId);

    AcPersonalBook selectByAcctBookIdAndUserid(@Param("acctBookId") String acctBookId, @Param("userId") String userId);

    List<AcPersonalBook> selectByUserIdAndCurrencyTypeId(@Param("currencyTypeId") String currencyTypeId
            , @Param("userIdContainU") String userIdContainU);

    int updateByAcctBookIdAndAmount(@Param("acctBookId") String acctBookId, @Param("amount") BigDecimal amount,
                                    @Param("userId") String userId);

    int updateByAcctBookIdAndDealAmount(@Param("acctBookId") String acctBookId, @Param("amount") BigDecimal amount,
                                        @Param("userId") String userId);

    int updateByAcctBookIdAmountExpiryDate(@Param("acctBookId") String acctBookId, @Param("amount") BigDecimal amount,
                                           @Param("userId") String userId, @Param("expiryDate") Date expiryDate);


    List<AcPersonalBook> selectByPrimaryListKey(@Param("userId") String userId, @Param("acctBookId") String acctBookId,
                                                @Param("currencyTypeIds") List<String> currencyTypeIds,
                                                @Param("effectStatus") String effectStatus,
                                                @Param("expireStatus") String expireStatus,
                                                @Param("balanceStatus") String balanceStatus,
                                                @Param("begTime") String begTime, @Param("endTime") String endTime,
                                                @Param("startNum") Integer startNum,
                                                @Param("endNum") Integer endNum);


    List<AcPersonalBook> selectByPrimaryListKeyVideo(@Param("userId") String userId,
                                                     @Param("acctBookId") String acctBookId,
                                                     @Param("currencyTypeIds") List<String> currencyTypeIds,
                                                     @Param("effectStatus") String effectStatus,
                                                     @Param("expireStatus") String expireStatus,
                                                     @Param("balanceStatus") String balanceStatus,
                                                     @Param("begTime") String begTime, @Param("endTime") String endTime);
    /**
     * 根据账本细类编号（如果有）与用户编号 查询用户所有账本
     *
     * @param currencyTypeIds [currencyTypeIds]
     * @param userIdContainU [userIdContainU]
     * @return
     */
    List<AcPersonalBook> selectByUserIdAndCurrencyTypeIds(
            @Param("currencyTypeIds") List<String> currencyTypeIds,
            @Param("userId") String userIdContainU);

    List<AcPersonalBook> selectByUserIdAndCurrencyTypeIdsAndAcctStatus(@Param("currencyTypeIds") List<String> currencyTypeIds
            , @Param("userId") String userIdContainU, @Param("acctStatus") String acctStatus);

    /**
     * 批量根据账本编号查询账本信息
     *
     * @param acctBookIdList
     * @return
     */
    List<AcPersonalBook> batchQueryAcPersonalBookList(@Param("userId") String userId,
                                                      @Param("list") List<String> acctBookIdList);

    void updateByAcctBookIdSub(AcPersonalBook acPersonalBook);


    List<AcPersonalBook> batchGetByUserIdAndCurrencyTypeId(@Param("list") List<String> currencyTypeIdList
            , @Param("userId") String userIdContainU);

    List<AcPersonalBook> batchGetByUserIdAndCurrencyTypeIdAndAcctBookId(@Param("list") List<String> currencyTypeIdList
            , @Param("userId") String userIdContainU, @Param("acctBookId") String acctBookId);

    List<AcPersonalBook> selectByAcctBookByUserId(@Param("userId") String userId);

    void updatePersonalBookByUserId(@Param("userId") String userId, @Param("acctStatus") String acctStatus,
                                    @Param("isMulti") Integer isMulti);

    //批量插入个人账本信息
    int batchInsertAcPersonBook(List<AcPersonalBook> acPersonalBookList);

    /*获取前一天所有失效的账本信息*/
    List<AcPersonalBook> getYestardayUnUsefulBook(@Param("yestarday") String yestarday, @Param("today") String today);

    List<AcPersonalBook> getUnUsefulHistoryBook(@Param("today") String today);

    int insertAction(AcPersonalBook record);

    void batchDeleteCleanData(@Param("list") List<AcPersonalBook> personalBookLists);

    void deleteByAccountBookId(AcPersonalBook acPersonalBook);

    /**
     * 删除个人账本，单个
     *
     * @param acctBookId 账本编号
     * @return
     */
    int delPersonalBook(@Param("acctBookId") String acctBookId);

    int updateBalanceByAcctBookIdAndUserId(@Param("balance") BigDecimal balance, @Param("acctBookId")
            String acctBookId, @Param("userId") String userId, @Param("gmtUpdate") Date gmtUpdate);

    List<AcPersonalBook> selectAcctBookByUserId(@Param("userId") String userId);

    /**
     * 批量修改账本状态
     *
     * @param updateTime 修改日期
     * @param status 修改后的状态
     * @param userId 用户编码
     * @param acctBookIds 待修改的账本ID稽核
     * @return
     */
    int batchUpdateByAcctBookId(@Param("updateTime") Date updateTime,
                                @Param("status") String status,
                                @Param("userId") String userId,
                                @Param("ids") List<String> acctBookIds);

    /**
     * 根据userId和卡号获取账本
     *
     * @param userIdContainU 用户编码
     * @param cardNo 卡号
     * @return
     */
    List<AcPersonalBook> getByUserIdAndCardNo(@Param("userId") String userIdContainU
            , @Param("cardNo") String cardNo, @Param("acctBookId") String acctBookId);
}