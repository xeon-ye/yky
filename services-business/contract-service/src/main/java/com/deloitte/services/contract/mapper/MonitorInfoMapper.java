package com.deloitte.services.contract.mapper;

import com.deloitte.platform.api.contract.vo.MonitorInfoVo;
import com.deloitte.services.contract.entity.MonitorInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 合同其他监控信息 Mapper 接口
 * </p>
 *
 * @author yangyq
 * @since 2019-04-20
 */
public interface MonitorInfoMapper extends BaseMapper<MonitorInfo> {
    //根据contractId查询其他监控信息
    List<MonitorInfoVo> queryMonitorInfo(String contractId);

}
