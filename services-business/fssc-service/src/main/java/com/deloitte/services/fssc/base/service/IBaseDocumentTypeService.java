package com.deloitte.services.fssc.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.base.param.BaseDocumentTypeQueryForm;
import com.deloitte.services.fssc.base.entity.BaseDocumentType;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-18
 * @Description : BaseDocumentType服务类接口
 * @Modified :
 */
public interface IBaseDocumentTypeService extends IService<BaseDocumentType> {

    /**
     * 根据编码查询单据类型
     * @param code
     * @param unitCode 单位编码
     * @param validFlag
     * @return BaseDocumentType
     */
    List<BaseDocumentType> getDocTypeByCode(String code,String unitCode,String validFlag);

    /**
     * 根据功能模块查询单据类型,只查询启用状态的数据
     * @param functionModule
     * @param unitCode 单位编码
     * @return BaseDocumentType
     */
    BaseDocumentType getDocTypeByFunction(String functionModule,String unitCode);


    /**
     * 分页查询
     * @param queryForm
     * @return IPage<BaseDocumentType>
     */
    IPage<BaseDocumentType> selectPage(BaseDocumentTypeQueryForm queryForm);

    /**
     * 条件查询
     * @param queryForm
     * @return List<BaseDocumentType>
     */
    List<BaseDocumentType> selectList(BaseDocumentTypeQueryForm queryForm);

    /**
     * 生效/失效,如果新状态与原有状态相同将抛异常
     * @param idList
     * @param validFlag
     * @return boolean
     */
    boolean modifyValidFlag(List<Long> idList, String validFlag);
}
