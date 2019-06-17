package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.SysSignSubjectInfoQueryForm;
import com.deloitte.platform.api.contract.vo.SysSignSubjectInfoForm;
import com.deloitte.platform.api.contract.vo.SysSignSubjectInfoVo;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.contract.entity.SysSignSubjectInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : SysSignSubjectInfo服务类接口
 * @Modified :
 */
public interface ISysSignSubjectInfoService extends IService<SysSignSubjectInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SysSignSubjectInfo>
     */
    IPage<SysSignSubjectInfo> selectPage(SysSignSubjectInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SysSignSubjectInfo>
     */
    List<SysSignSubjectInfo> selectList(SysSignSubjectInfoQueryForm queryForm);

    SysSignSubjectInfo selectObjectById(Long subjectId);
    //复制合同查询签约主体
    List<SysSignSubjectInfoForm> queryCopyInfo(String contractId);

    IPage<SysSignSubjectInfoVo> selectByClient(SysSignSubjectInfoQueryForm sysSignSubjectInfoQueryForm);
}
