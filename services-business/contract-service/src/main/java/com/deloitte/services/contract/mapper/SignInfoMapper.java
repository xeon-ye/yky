package com.deloitte.services.contract.mapper;

import com.deloitte.services.contract.entity.SignInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 合同签署信息 Mapper 接口
 * </p>
 *
 * @author zhengchun
 * @since 2019-03-26
 */
public interface SignInfoMapper extends BaseMapper<SignInfo> {
    //根据contractId查询合同签署信息
    List<SignInfo> querySignInfo(String contractId);
}
