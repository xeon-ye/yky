package com.deloitte.services.fssc.performance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.performance.param.PerformancePrjMainTypeQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformancePrjMainTypeForm;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainType;
import com.deloitte.services.fssc.performance.entity.PerformancePrjMainType;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-03
 * @Description : PerformancePrjMainType服务类接口
 * @Modified :
 */
public interface IPerformancePrjMainTypeService extends IService<PerformancePrjMainType> {

    /**
     * 批量 更新或者保存
     * @param mainTypeFormList
     * @return
     */
    void updateOrSaveBatch(List<PerformancePrjMainTypeForm> mainTypeFormList);

    /**
     * 按照编码查询
     * @param code
     */
    PerformancePrjMainType getMainTypeByCode(String code);

    /**
     * 生效/失效,如果新状态与原有状态相同将抛异常
     * @param idList
     * @param validFlag
     * @return boolean
     */
    boolean modifyValidFlag(List<Long> idList, String validFlag);

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<PerformancePrjMainType>
     */
    IPage<PerformancePrjMainType> selectPage(PerformancePrjMainTypeQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<PerformancePrjMainType>
     */
    List<PerformancePrjMainType> selectList(PerformancePrjMainTypeQueryForm queryForm);
}
