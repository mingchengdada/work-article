/*
 * All rights Reserved, Designed By MiGu
 * Copyright (c) 2019 - 2020
 * Company MiGu Co., Ltd
 */

package com.mc.redis.configuration.mapper;

import com.mc.redis.configuration.bean.AcAssetTransferDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AcAssetTransferDetailMapper {

    List<AcAssetTransferDetail> selectAll();
}