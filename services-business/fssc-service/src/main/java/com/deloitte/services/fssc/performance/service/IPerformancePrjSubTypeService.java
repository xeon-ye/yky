package com.deloitte.services.fssc.performance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.base.param.BaseIncomeSubTypeQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseIncomeSubTypeVo;
import com.deloitte.platform.api.fssc.performance.param.PerformancePrjSubTypeQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformancePrjMainTypeForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformancePrjSubTypeForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformancePrjSubTypeVo;
import com.deloitte.services.fssc.performance.entity.PerformancePrjSubType;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-03
 * @Description : PerformancePrjSubType服务类接口
 * @Modified :
 */
public interface IPerformancePrjSubTypeService extends IService<PerformancePrjSubType> {


    /**
     * 批量 更新或者保存
     * @param subTypeFormList
     * @return
     */
    void updateOrSaveBatch(List<PerformancePrjSubTypeForm> subTypeFormList);

    /**
     * 按照编码查询
     * @param code
     * @return PerformancePrjSubType
     */
    PerformancePrjSubType getSubTypeByCode(String code);

    /**
     * 生效/失效,如果新状态与原有状态相同将抛异常
     * @param idList
     * @param validFlag
     * @return boolean
     */
    boolean modifyValidFlag(List<Long> idList, String validFlag);

    /**
     * 根据项目大类id集合查询是否被小类关联
     * @param idList
     * @return
     */
    boolean existsByMainTypeIds(List<Long> idList);

    /**
     * 根据项目大类id集合查询是否被小类关联
     * @param idList
     * @param validFlag 启用状态
     * @return
     */
    boolean existsByMainTypeIds(List<Long> idList,String validFlag);

    /**
     * 分页查询(查询出VO的所有字段)
     * @param queryForm
     * @return
     */
    IPage<PerformancePrjSubTypeVo> selectVoPage(PerformancePrjSubTypeQueryForm queryForm);

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<PerformancePrjSubType>
     */
    IPage<PerformancePrjSubType> selectPage(PerformancePrjSubTypeQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<PerformancePrjSubType>
     */
    List<PerformancePrjSubType> selectList(PerformancePrjSubTypeQueryForm queryForm);
}
