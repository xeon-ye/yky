package com.deloitte.services.contract.mapper;

import com.deloitte.platform.api.contract.vo.SysProjectInfoVo;
import com.deloitte.services.contract.entity.SysProjectInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 合同项目信息 数据来源：科研或者通用项目 Mapper 接口
 * </p>
 *
 * @author zhengchun
 * @since 2019-03-26
 */
public interface SysProjectInfoMapper extends BaseMapper<SysProjectInfo> {
    //根据contractId查询项目信息
    List<SysProjectInfoVo> querySysProjectInfo(String contractId);

}
