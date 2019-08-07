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
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AcBillDepositFlow implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 用户编码
     */
    private String userId;

    /**
     * 账单编号
     */
    private String billFlowId;

    /**
     * 账本编号
     */
    private String acctBookId;

    /**
     * 账本类型: 1-账本、2-清算账本
     */
    private String bookType;

    /**
     * 账单类型 : 0-充值 1-扣费 2-补款 3-预留扣费
     */
    private String billType;

    /**
     * 该次操作金额
     */
    private BigDecimal amount;

    /**
     * 操作前金额
     */
    private BigDecimal beforeAmount;

    /**
     * 操作后金额
     */
    private BigDecimal afterAmount;

    /**
     * 内部编号
     */
    private String innerId;

    /**
     * 外部编号
     */
    private String externalId;

    /**
     * 操作来源（赠送 领取 奖励 反冲）
     */
    private String opCode;

    /**
     * 平台类型编码
     */
    private String platform;

    /**
     * 接入类型编码
     */
    private String portalType;

    /**
     * 产品线编码
     */
    private String productLine;

    /**
     * 迁移前的userid
     */
    private String reserved1;

    /**
     * 迁移前账本objectId
     */
    private String reserved2;

    /**
     * 当前账本objectId
     */
    private String reserved3;

    /**
     * 预留字段
     */
    private String reserved4;

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
     * 扩展信息:原始金额是否单独列出？
     */
    private String extInfo;

    /**
     * 备注
     */
    private String remark;

    private String currencyTypeId;
    /**
     * 账本失效时间
     */
    private Date acctExpiryDate;

}