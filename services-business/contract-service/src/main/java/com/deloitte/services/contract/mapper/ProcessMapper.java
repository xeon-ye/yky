package com.deloitte.services.contract.mapper;

import com.deloitte.services.contract.entity.Process;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 合同管理系统 业务流程表 Mapper 接口
 * </p>
 *
 * @author zhengchun
 * @since 2019-03-30
 */
public interface ProcessMapper extends BaseMapper<Process> {

    Process getEndKeyByStartKey(String processDefineKey);
}
