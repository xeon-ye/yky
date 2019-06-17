package com.deloitte.services.fssc.engine.automatic.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.engine.automatic.param.AvAccountElementQueryForm;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvChartOfAccountQueryForm;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvUtilQueryForm;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountElement;
import com.deloitte.services.fssc.engine.automatic.entity.AvBaseElement;
import com.deloitte.services.fssc.engine.automatic.entity.AvChartOfAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description : AvChartOfAccount服务类接口
 * @Modified :
 */
public interface IAvChartOfAccountService extends IService<AvChartOfAccount> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<AvChartOfAccount>
     */
    IPage<AvChartOfAccount> selectPage(AvChartOfAccountQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<AvChartOfAccount>
     */
    List<AvChartOfAccount> selectList(AvChartOfAccountQueryForm queryForm);

    /**
     * 查询账薄单位
     * @param queryForm
     * @return
     */
    List<AvBaseElement> selectUnitList(AvUtilQueryForm queryForm);
    /**
     * 查询账薄单位总个数
     * @param queryForm
     * @return
     */
    Integer selectUnitListCount(AvUtilQueryForm queryForm);

    /**
     * 查询会计结构含有哪些段
     * @return
     */
    List<AvAccountElement> selectAccountFrameList(Long ledgerId);

    /**
     * 为收入小类提供该人员上面该单位的会计科目值（类型分为“财”和“预”）
     * @param code
     * @param type
     * @return
     */
    List<AvBaseElement> selectBaseElementForAccount(String code,String type);
}
