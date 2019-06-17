package com.deloitte.services.fssc.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.base.param.BaseDocumentTypeIncomeQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseDocumentTypeIncomeVo;
import com.deloitte.services.fssc.base.entity.BaseDocumentTypeIncome;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-18
 * @Description : BaseDocumentTypeIncome服务类接口
 * @Modified :
 */
public interface IBaseDocumentTypeIncomeService extends IService<BaseDocumentTypeIncome> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BaseDocumentTypeIncome>
     */
    IPage<BaseDocumentTypeIncomeVo> selectVoPage(BaseDocumentTypeIncomeQueryForm queryForm);

    /**
     * 列表查询VO
     * @param queryForm
     * @return
     */
    List<BaseDocumentTypeIncomeVo> listVo(BaseDocumentTypeIncomeQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BaseDocumentTypeIncome>
     */
    List<BaseDocumentTypeIncome> selectList(BaseDocumentTypeIncomeQueryForm queryForm);

    /**
     * 修改启用状态
     * @param ids
     * @param validFlag
     * @return
     */
    boolean modifyValidFlag(List<Long> ids, String validFlag);


    /**
     * 单据类型是否被收入大类关联
     * @param ids
     * @return
     */
    boolean existsByIncomeMainTypeIds(List<Long> ids);

    /**
     * 单据类型是否被收入大类关联
     * @param ids
     * @param validFlag
     * @return
     */
    boolean existsByIncomeMainTypeIds(List<Long> ids,String validFlag);

    /**
     * 判断是否已关联收入大类
     * @param ids
     * @return
     */
    boolean existsByDocumentTypeId(List<Long> ids);
}
