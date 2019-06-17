package com.deloitte.services.fssc.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.base.param.BaseExpenseSubTypeQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseExpenseSubTypeVo;
import com.deloitte.services.fssc.base.entity.BaseExpenseSubType;

import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-02
 * @Description : BaseExpenseSubType服务类接口
 * @Modified :
 */
public interface IBaseExpenseSubTypeService extends IService<BaseExpenseSubType> {

    /**
     * 查询,By ID
     * @param id
     * @return
     */
    BaseExpenseSubTypeVo getSubTypeById(Long id);


    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BaseExpenseSubType>
     */
    IPage<BaseExpenseSubType> selectPage(BaseExpenseSubTypeQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BaseExpenseSubType>
     */
    List<BaseExpenseSubType> selectList(BaseExpenseSubTypeQueryForm queryForm);

    /**
     * 分页查询(查询出VO的所有字段)
     * @param queryForm
     * @return
     */
    IPage<BaseExpenseSubTypeVo> selectVoPage(BaseExpenseSubTypeQueryForm queryForm);

    /**
     *  按照编码查询
     * @param code
     * @return
     */
    BaseExpenseSubType getSubTypeByCode(String code,String unitCode);

    /**
     * 更新支出小类
     * @param subType
     * @return
     */
    boolean updateSubType(BaseExpenseSubType subType);

    /**
     *  生效/失效,如果新状态与原有状态相同将抛异常
     * @param idList
     * @param validFlag
     * @return
     */
    boolean modifyValidFlag(List<Long> idList, String validFlag);

    /**
     * 根据支出大类id查询是关联的小类
     * @param expenseMainTypeIdList
     * @return
     */
    boolean existsByExpenseMainTypeIds(List<Long> expenseMainTypeIdList);


}
