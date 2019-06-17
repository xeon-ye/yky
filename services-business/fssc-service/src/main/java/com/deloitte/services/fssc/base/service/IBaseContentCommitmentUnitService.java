package com.deloitte.services.fssc.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.base.param.BaseContentCommitmentUnitQueryForm;
import com.deloitte.services.fssc.base.entity.BaseContentCommitmentUnit;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-07
 * @Description : BaseContentCommitmentUnit服务类接口
 * @Modified :
 */
public interface IBaseContentCommitmentUnitService extends IService<BaseContentCommitmentUnit> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BaseContentCommitmentUnit>
     */
    IPage<BaseContentCommitmentUnit> selectPage(BaseContentCommitmentUnitQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BaseContentCommitmentUnit>
     */
    List<BaseContentCommitmentUnit> selectList(BaseContentCommitmentUnitQueryForm queryForm);

    /**
     * 修改状态
     * @param ids
     * @param status
     * @return
     */
    boolean modifyeContentCommitmentUnitStatus(List<Long> ids,String status);
}
