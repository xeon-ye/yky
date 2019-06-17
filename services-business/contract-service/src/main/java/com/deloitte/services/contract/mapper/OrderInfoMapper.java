package com.deloitte.services.contract.mapper;

import com.deloitte.platform.api.contract.vo.OrderInfoVo;
import com.deloitte.services.contract.entity.OrderInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 合同订单信息 Mapper 接口
 * </p>
 *
 * @author zhengchun
 * @since 2019-03-26
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {

    List<OrderInfoVo> selectListByContractId(String contractId);
}
