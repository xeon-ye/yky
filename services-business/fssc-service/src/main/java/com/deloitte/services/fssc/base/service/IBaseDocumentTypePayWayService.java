package com.deloitte.services.fssc.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.base.param.BaseDocumentTypePayWayQueryForm;
import com.deloitte.platform.api.fssc.base.vo.BaseDocumentTypePayWayForm;
import com.deloitte.services.fssc.base.entity.BaseDocumentTypePayWay;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-11
 * @Description : BaseDocumentTypePayWay服务类接口
 * @Modified :
 */
public interface IBaseDocumentTypePayWayService extends IService<BaseDocumentTypePayWay> {

    /**
     * 批量 更新或者保存
     * @param payWayFormList
     * @return
     */
    void updateOrSaveBatch(List<BaseDocumentTypePayWayForm> payWayFormList);

    /**
     * 根据编码查询
     * @param code
     * @param documentTypeId
     * @return
     */
    BaseDocumentTypePayWay getPayWayByCode(String code,Long documentTypeId);

    /**
     * 启用/失效
     * @param idList
     * @param validFlag
     * @return
     */
    boolean modifyValidFlag(List<Long> idList, String validFlag);

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BaseDocumentTypePayWay>
     */
    IPage<BaseDocumentTypePayWay> selectPage(BaseDocumentTypePayWayQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BaseDocumentTypePayWay>
     */
    List<BaseDocumentTypePayWay> selectList(BaseDocumentTypePayWayQueryForm queryForm);
}
