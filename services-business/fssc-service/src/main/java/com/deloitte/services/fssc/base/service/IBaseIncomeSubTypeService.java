package com.deloitte.services.fssc.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.base.param.BaseIncomeSubTypeQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseIncomeSubTypeVo;
import com.deloitte.services.fssc.base.entity.BaseIncomeSubType;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-02-21
 * @Description : BaseIncomeMainType服务类接口
 * @Modified :
 */
public interface IBaseIncomeSubTypeService extends IService<BaseIncomeSubType> {

    /**
     * 查询,By ID
     * @param id
     * @return
     */
    BaseIncomeSubTypeVo getSubTypeById(Long id);

    /**
     *  按照编码查询
     * @param code
     * @return
     */
    List<BaseIncomeSubType> getSubTypeByCode(String code,String unitCode,String validFlag);

    /**
     * 更新收入小类
     * @param subType
     * @return
     */
    boolean updateSubType(BaseIncomeSubType subType);

    /**
     * 分页查询
     * @param queryForm
     * @return
     */
    IPage<BaseIncomeSubType> selectPage(BaseIncomeSubTypeQueryForm queryForm);

    /**
     * 分页查询(查询出VO的所有字段)
     * @param queryForm
     * @return
     */
    IPage<BaseIncomeSubTypeVo> selectVoPage(BaseIncomeSubTypeQueryForm queryForm);

    /**
     * 条件查询
     * @param queryForm
     * @return
     */
    List<BaseIncomeSubType> selectList(BaseIncomeSubTypeQueryForm queryForm);

    /**
     *  生效/失效,如果新状态与原有状态相同将抛异常
     * @param idList
     * @param validFlag
     * @return
     */
    boolean modifyValidFlag(List<Long> idList, String validFlag);

    /**
     * 根据收入大类ID判断是否存在已关联的小类
     * @param incomeMainTypeIdList 收入大类id列表
     * @return
     */
    boolean existsByIncomeMainTypeIds(List<Long> incomeMainTypeIdList);

    /**
     * 根据收入大类ID判断是否存在已关联的小类,支持筛选状态
     * @param incomeMainTypeIdList 收入大类id列表
     * @param validFlag 启用标志
     * @return
     */
    boolean existsByIncomeMainTypeIds(List<Long> incomeMainTypeIdList,String validFlag);

}
