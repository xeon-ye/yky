package com.deloitte.services.fssc.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.base.param.BaseExpenseMainTypeUnitQueryForm;
import com.deloitte.services.fssc.base.entity.BaseExpenseMainTypeUnit;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-02-21
 * @Description : BaseIncomeMainTypeOrgUnit服务类接口
 * @Modified :
 */
@Deprecated
public interface IBaseExpenseMainTypeOrgUnitService extends IService<BaseExpenseMainTypeUnit> {

    /**
     * 更新
     */
    boolean updateMainTypeOrgUnit(BaseExpenseMainTypeUnit mainTypeOrgUnit);

    /**
     * 分页查询
     *
     * @return IPage<BaseExpenseMainTypeUnit>
     */
    IPage<BaseExpenseMainTypeUnit> selectPage(BaseExpenseMainTypeUnitQueryForm queryForm);

    /**
     * 条件查询
     *
     * @return List<BaseIncomeMainTypeOrgUnit>
     */
    List<BaseExpenseMainTypeUnit> selectList(BaseExpenseMainTypeUnitQueryForm queryForm);

    /**
     * 生效/失效
     */
    boolean modifyValidFlag(List<Long> idList, String validFlag);

}
