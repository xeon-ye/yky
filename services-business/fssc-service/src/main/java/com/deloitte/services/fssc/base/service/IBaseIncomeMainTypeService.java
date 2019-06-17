package com.deloitte.services.fssc.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.base.param.BaseIncomeMainTypeQueryForm;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainType;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-02-21
 * @Description : BaseIncomeMainType服务类接口
 * @Modified :
 */
public interface IBaseIncomeMainTypeService extends IService<BaseIncomeMainType> {

    /**
     * 按照编码查询
     */
    List<BaseIncomeMainType> getMainTypeByCode(String code,String unitCode);

    /**
     *
     * @param code
     * @param unitCode
     * @param validFlag
     * @return
     */
    List<BaseIncomeMainType> getMainTypeByCode(String code,String unitCode,String validFlag);

    /**
     * 查询子收入大类
     */
    List<BaseIncomeMainType> getChildMainType(String parentCode,String unitCode);

    /**
     * 是否存在生效状态的子大类
     */
    boolean existChildMainType(String parentCode, String validFlag,String unitCode);

    /**
     * 是否存在子大类
     *
     * @param validFlag 启用状态
     */
    boolean existChildMainType(List<Long> ids, String validFlag);

    /**
     * 更新收入大类
     */
    boolean updateMainType(BaseIncomeMainType mainType);

    /**
     * 分页查询
     *
     * @return IPage<BaseIncomeMainType>
     */
    IPage<BaseIncomeMainType> selectPage(BaseIncomeMainTypeQueryForm queryForm);

    /**
     * 条件查询
     *
     * @return List<BaseIncomeMainType>
     */
    List<BaseIncomeMainType> selectList(BaseIncomeMainTypeQueryForm queryForm);


    /**
     * 生效/失效,如果新状态与原有状态相同将抛异常
     */
    boolean modifyValidFlag(List<Long> idList, String validFlag);

}
