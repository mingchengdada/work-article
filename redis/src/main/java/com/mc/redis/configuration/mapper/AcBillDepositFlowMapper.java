package com.mc.redis.configuration.mapper;

import com.mc.redis.configuration.bean.AcBillDepositFlow;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AcBillDepositFlowMapper {


    List<AcBillDepositFlow> selectDepositByUserIdAndBookId(@Param("userId") String userId,@Param("bookId") String bookId
    ,@Param("bookId1") String bookId1);
}