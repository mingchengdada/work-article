package com.mc.redis.configuration.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcPersonalBook implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户编码
     */
    private String userId;

    /**
     * 账本编号
     */
    private String acctBookId;

    /**
     * 账本细类编号
     */
    private String currencyTypeId;

    /**
     * 余额
     */
    private BigDecimal balance;

    /**
     * 卡批次
     */
    private String cardBatchId;

    /**
     * 外部编号
     */
    private String externalId;

    /**
     * 是否多账本
     */
    private Byte isMulti;

    /**
     * 是否可延期
     */
    private Byte isDefer;

    /**
     * 营销活动编码
     */
    private String activityId;

    /**
     * 营销活动名称
     */
    private String activityName;

    /**
     * 账本状态: 1可用，0不可用
     */
    private String acctStatus;

    /**
     * 预留字段1: 当前账本objectId
     */
    private String reserved1;

    /**
     * 预留字段2
     */
    private String reserved2;

    /**
     * 预留字段3
     */
    private String reserved3;

    /**
     * 预留字段4
     */
    private String reserved4;

    /**
     * 来源
     */
    private String channelId;

    /**
     * 账本失效时间
     */
    private Date gmtExpiry;

    /**
     * 账本生效时间
     */
    private Date gmtEffective;

    /**
     * 初始失效时间
     */
    private Date gmtInitExpiry;

    /**
     * 创建时间
     */
    private Date gmtCreate;

    /**
     * 修改时间
     */
    private Date gmtUpdate;

    /**
     * 数据状态: 0：无效 1：有效
     */
    private Byte state;

    /**
     * 扩展信息
     */
    private String extInfo;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAcctBookId() {
        return acctBookId;
    }

    public void setAcctBookId(String acctBookId) {
        this.acctBookId = acctBookId;
    }

    public String getCurrencyTypeId() {
        return currencyTypeId;
    }

    public void setCurrencyTypeId(String currencyTypeId) {
        this.currencyTypeId = currencyTypeId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCardBatchId() {
        return cardBatchId;
    }

    public void setCardBatchId(String cardBatchId) {
        this.cardBatchId = cardBatchId;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public Byte getIsMulti() {
        return isMulti;
    }

    public void setIsMulti(Byte isMulti) {
        this.isMulti = isMulti;
    }

    public Byte getIsDefer() {
        return isDefer;
    }

    public void setIsDefer(Byte isDefer) {
        this.isDefer = isDefer;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getAcctStatus() {
        return acctStatus;
    }

    public void setAcctStatus(String acctStatus) {
        this.acctStatus = acctStatus;
    }

    public String getReserved1() {
        return reserved1;
    }

    public void setReserved1(String reserved1) {
        this.reserved1 = reserved1;
    }

    public String getReserved2() {
        return reserved2;
    }

    public void setReserved2(String reserved2) {
        this.reserved2 = reserved2;
    }

    public String getReserved3() {
        return reserved3;
    }

    public void setReserved3(String reserved3) {
        this.reserved3 = reserved3;
    }

    public String getReserved4() {
        return reserved4;
    }

    public void setReserved4(String reserved4) {
        this.reserved4 = reserved4;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public Date getGmtExpiry() {
        return gmtExpiry;
    }

    public void setGmtExpiry(Date gmtExpiry) {
        this.gmtExpiry = gmtExpiry;
    }

    public Date getGmtEffective() {
        return gmtEffective;
    }

    public void setGmtEffective(Date gmtEffective) {
        this.gmtEffective = gmtEffective;
    }

    public Date getGmtInitExpiry() {
        return gmtInitExpiry;
    }

    public void setGmtInitExpiry(Date gmtInitExpiry) {
        this.gmtInitExpiry = gmtInitExpiry;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtUpdate() {
        return gmtUpdate;
    }

    public void setGmtUpdate(Date gmtUpdate) {
        this.gmtUpdate = gmtUpdate;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public String getExtInfo() {
        return extInfo;
    }

    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }
}