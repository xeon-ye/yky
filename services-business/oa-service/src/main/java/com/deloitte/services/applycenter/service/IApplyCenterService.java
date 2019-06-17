package com.deloitte.services.applycenter.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.oaservice.applycenter.param.ApplyCenterQueryForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.ApplyCenterForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.applycenter.entity.ApplyCenter;

import java.util.List;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-12
 * @Description : ApplyCenter服务类接口
 * @Modified :
 */
public interface IApplyCenterService extends IService<ApplyCenter> {

    /**
     * 新增
     * @param applyCenterForm
     * @return
     */
    Result save(ApplyCenterForm applyCenterForm);

    /**
     * 分页查询
     *
     * @param queryForm
     * @return IPage<ApplyCenter>
     */
    GdcPage<ApplyCenter> selectPage(ApplyCenterQueryForm queryForm);

    /**
     * 条件查询
     *
     * @param queryForm
     * @return List<ApplyCenter>
     */
    List<ApplyCenter> selectList(ApplyCenterQueryForm queryForm);
}
