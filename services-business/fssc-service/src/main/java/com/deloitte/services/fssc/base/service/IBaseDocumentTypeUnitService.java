package com.deloitte.services.fssc.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.base.param.BaseDocumentTypeUnitQueryForm;
import com.deloitte.services.fssc.base.entity.BaseDocumentTypeUnit;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-18
 * @Description : BaseDocumentTypeUnit服务类接口
 * @Modified :
 */
public interface IBaseDocumentTypeUnitService extends IService<BaseDocumentTypeUnit> {

    /**
     * 分页查询
     *
     * @return IPage<BaseDocumentTypeUnit>
     */
    IPage<BaseDocumentTypeUnit> selectPage(BaseDocumentTypeUnitQueryForm queryForm);

    /**
     * 条件查询
     *
     * @return List<BaseDocumentTypeUnit>
     */
    List<BaseDocumentTypeUnit> selectList(BaseDocumentTypeUnitQueryForm queryForm);

    /**
     * 修改启用状态
     */
    boolean modifyValidFlag(List<Long> idList, String validFlag);
}
