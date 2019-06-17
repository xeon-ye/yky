package com.deloitte.services.fssc.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.base.param.BaseIncomeSubTypeOrgUnitQueryForm;
import com.deloitte.services.fssc.base.entity.BaseIncomeSubTypeOrgUnit;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-02-21
 * @Description : BaseIncomeSubTypeOrgUnit服务类接口
 * @Modified :
 */
@Deprecated
public interface IBaseIncomeSubTypeOrgUnitService extends IService<BaseIncomeSubTypeOrgUnit> {

    /**
     * 更新
     * @param subTypeOrgUnit
     * @return
     */
    boolean updateSubTypeOrgUnit(BaseIncomeSubTypeOrgUnit subTypeOrgUnit);

    /**
     * 分页查询
     * @param queryForm
     * @return
     */
    IPage<BaseIncomeSubTypeOrgUnit> selectPage(BaseIncomeSubTypeOrgUnitQueryForm queryForm);

    /**
     * 条件查询
     * @param queryForm
     * @return
     */
    List<BaseIncomeSubTypeOrgUnit> selectList(BaseIncomeSubTypeOrgUnitQueryForm queryForm);

    /**
     * 生效/失效
     * @param idList
     * @param validFlag
     * @return
     */
    boolean modifyValidFlag(List<Long> idList, String validFlag);


}
