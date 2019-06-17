package com.deloitte.services.contract.mapper;

import com.deloitte.platform.api.contract.vo.FinanceInfoVo;
import com.deloitte.services.contract.entity.FinanceInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 合同财务信息 Mapper 接口
 * </p>
 *
 * @author yangyq
 * @since 2019-04-17
 */
public interface FinanceInfoMapper extends BaseMapper<FinanceInfo> {
    //根据contractId查询财务信息
    List<FinanceInfoVo> selectFinanceInfo(String contractId);

}
