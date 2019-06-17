package com.deloitte.services.fssc.business.advance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.advance.param.VerificationDetailQueryForm;
import com.deloitte.services.fssc.business.advance.entity.VerificationDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-12
 * @Description : BmVerificationDetail服务类接口
 * @Modified :
 */
public interface IVerificationDetailService extends IService<VerificationDetail> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<VerificationDetail>
     */
    IPage<VerificationDetail> selectPage(VerificationDetailQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<VerificationDetail>
     */
    List<VerificationDetail> selectList(VerificationDetailQueryForm queryForm);
}
