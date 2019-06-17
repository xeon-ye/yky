package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.CommonReasonQueryForm;
import com.deloitte.services.contract.entity.CommonReason;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-09
 * @Description : CommonReason服务类接口
 * @Modified :
 */
public interface ICommonReasonService extends IService<CommonReason> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<CommonReason>
     */
    IPage<CommonReason> selectPage(CommonReasonQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<CommonReason>
     */
    List<CommonReason> selectList(CommonReasonQueryForm queryForm);
}
