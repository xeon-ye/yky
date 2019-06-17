package com.deloitte.services.fssc.engine.automatic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.engine.automatic.param.AvAccountElementQueryForm;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountElement;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.fssc.engine.automatic.entity.AvBaseElement;
import com.deloitte.services.fssc.engine.automatic.entity.AvChartOfAccount;

import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description : AvAccountElement服务类接口
 * @Modified :
 */
public interface IAvAccountElementService extends IService<AvAccountElement> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<AvAccountElement>
     */
    IPage<AvAccountElement> selectPage(AvAccountElementQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<AvAccountElement>
     */
    List<AvAccountElement> selectList(AvAccountElementQueryForm queryForm);

    /**
     * 查询核算要素是否被使用
     * @param elementId
     * @return
     */
    List<AvAccountElement> whetherEnabledById(Long elementId);

    /**
     * 根据核算要素Id查询关联的要素值
     * @param queryForm
     * @return
     */
    List<AvBaseElement> baseValueListByElementId(AvAccountElementQueryForm queryForm);

    /**
     * 根据核算要素Id查询关联的要素总数
     * @param queryForm
     * @return
     */
    Integer baseValueCountByElementId(AvAccountElementQueryForm queryForm);

    /**
     * 保存核算要素对应的数据来源信息，并且同步关系数据到相应表中！
     * @param entity
     * @return
     */
    boolean saveSourceFromObtain(AvAccountElement entity);

    /**
     * 查询核算要素的分配到哪些账薄下
     * @param form
     * @return
     */
    List<AvChartOfAccount> elementReleationLedgerList (AvAccountElementQueryForm form);

    Integer elementReleationLedgerListCount (AvAccountElementQueryForm form);

    /**
     * 查询分页的核算要素值
     * @param form
     * @return
     */
    List<AvAccountElement> queryPageListByParam(AvAccountElementQueryForm form);

    /**
     *
     */
    int queryPageListByParamCount(AvAccountElementQueryForm form);
}
