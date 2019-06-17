package com.deloitte.services.fssc.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.base.param.BaseExpenseSubTypeUnitQueryForm;
import com.deloitte.services.fssc.base.entity.BaseExpenseSubTypeUnit;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-02
 * @Description : BaseExpenseSubTypeUnit服务类接口
 * @Modified :
 */
@Deprecated
public interface IBaseExpenseSubTypeUnitService extends IService<BaseExpenseSubTypeUnit> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BaseExpenseSubTypeUnit>
     */
    IPage<BaseExpenseSubTypeUnit> selectPage(BaseExpenseSubTypeUnitQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BaseExpenseSubTypeUnit>
     */
    List<BaseExpenseSubTypeUnit> selectList(BaseExpenseSubTypeUnitQueryForm queryForm);

    /**
     * 更新
     * @param subTypeOrgUnit
     * @return
     */
    boolean updateSubTypeOrgUnit(BaseExpenseSubTypeUnit subTypeOrgUnit);

    /**
     * 生效/失效
     * @param idList
     * @param validFlag
     * @return
     */
    boolean modifyValidFlag(List<Long> idList, String validFlag);

}
