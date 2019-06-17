package com.deloitte.services.contract.mapper;

import com.deloitte.platform.api.contract.vo.TicketInfoVo;
import com.deloitte.services.contract.entity.TicketInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 合同开票信息 Mapper 接口
 * </p>
 *
 * @author yangyq
 * @since 2019-04-16
 */
public interface TicketInfoMapper extends BaseMapper<TicketInfo> {

    List<TicketInfoVo> selectListByContractId(String contractId);
}
