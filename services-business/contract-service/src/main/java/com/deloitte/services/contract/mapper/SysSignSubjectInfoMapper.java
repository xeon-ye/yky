package com.deloitte.services.contract.mapper;

import com.deloitte.platform.api.contract.vo.SysSignSubjectInfoForm;
import com.deloitte.platform.api.contract.vo.SysSignSubjectInfoVo;
import com.deloitte.services.contract.entity.SysSignSubjectInfo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 签约主体 数据来源：HR系统 Mapper 接口
 * </p>
 *
 * @author zhengchun
 * @since 2019-04-12
 */
public interface SysSignSubjectInfoMapper extends BaseMapper<SysSignSubjectInfo> {
    //复制合同查询签约主体
    List<SysSignSubjectInfoForm> queryCopyInfo(String contractId);

}
