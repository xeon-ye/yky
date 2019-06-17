package com.deloitte.services.fssc.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.base.param.BasePersonIncomeTypeQueryForm;
import com.deloitte.services.fssc.base.entity.BasePersonIncomeType;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-22
 * @Description : BasePersonIncomeType服务类接口
 * @Modified :
 */
public interface IBasePersonIncomeTypeService extends IService<BasePersonIncomeType> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BasePersonIncomeType>
     */
    IPage<BasePersonIncomeType> selectPage(BasePersonIncomeTypeQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BasePersonIncomeType>
     */
    List<BasePersonIncomeType> selectList(BasePersonIncomeTypeQueryForm queryForm);


    /**
     * 修改启用状态
     * @param ids
     * @param validFlag
     * @return
     */
    boolean modifyValidFlag(List<Long> ids, String validFlag);

    /**
     * 判断是否被人员类型
     * @param ids
     * @return
     */
    boolean existsByDocumentTypeId(List<Long> ids);

    /**
     * 根据单据类型定义查询
     * @param documentId
     * @return
     */
    List<BasePersonIncomeType> selectByDocumentTypeId(Long documentId);

    /**
     * 根据功能模块查询
     * @param functionModule
     * @param unitCode
     * @return
     */
    List<BasePersonIncomeType> selectByFunctionModule(String functionModule,String unitCode);
}
