package com.deloitte.services.fssc.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.base.param.BaseIncomeMainTypeOrgUnitQueryForm;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainTypeOrgUnit;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-02-21
 * @Description : BaseIncomeMainTypeOrgUnit服务类接口
 * @Modified :
 */
@Deprecated
public interface IBaseIncomeMainTypeOrgUnitService extends IService<BaseIncomeMainTypeOrgUnit> {

    /**
     * 更新
     */
    boolean updateMainTypeOrgUnit(BaseIncomeMainTypeOrgUnit mainTypeOrgUnit);

    /**
     * 分页查询
     *
     * @return IPage<BaseIncomeMainTypeOrgUnit>
     */
    IPage<BaseIncomeMainTypeOrgUnit> selectPage(BaseIncomeMainTypeOrgUnitQueryForm queryForm);

    /**
     * 条件查询
     *
     * @return List<BaseIncomeMainTypeOrgUnit>
     */
    List<BaseIncomeMainTypeOrgUnit> selectList(BaseIncomeMainTypeOrgUnitQueryForm queryForm);

    /**
     * 生效/失效
     */
    boolean modifyValidFlag(List<Long> idList, String validFlag);


}
