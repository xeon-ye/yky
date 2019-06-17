package com.deloitte.services.fssc.base.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.base.param.BaseDocumentTypeExpenseQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseDocumentTypeExpenseVo;
import com.deloitte.services.fssc.base.entity.BaseDocumentTypeExpense;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.fssc.base.entity.BaseIncomeMainTypeOrgUnit;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-18
 * @Description : BaseDocumentTypeExpense服务类接口
 * @Modified :
 */
public interface IBaseDocumentTypeExpenseService extends IService<BaseDocumentTypeExpense> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BaseDocumentTypeExpense>
     */
    IPage<BaseDocumentTypeExpenseVo> selectVoPage(BaseDocumentTypeExpenseQueryForm queryForm);

    /**
     * 列表查询VO
     * @param queryForm
     * @return
     */
    List<BaseDocumentTypeExpenseVo> listVo(BaseDocumentTypeExpenseQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BaseDocumentTypeExpense>
     */
    List<BaseDocumentTypeExpense> selectList(BaseDocumentTypeExpenseQueryForm queryForm);

    /**
     * 修改启用状态
     * @param ids
     * @param validFlag
     * @return
     */
    boolean modifyValidFlag(List<Long> ids, String validFlag);

    /**
     * 单据类型是否关联支出大类
     * @param ids
     * @return
     */
    boolean existsByExpenseMainTypeIds(List<Long> ids);

    /**
     * 单据类型是否关联支出大类
     * @param ids
     * @param validFlag
     * @return
     */
    boolean existsByExpenseMainTypeIds(List<Long> ids,String validFlag);

    /**
     * 判断是否已关联支出大类
     * @param ids
     * @return
     */
    boolean existsByDocumentTypeId(List<Long> ids);
}
