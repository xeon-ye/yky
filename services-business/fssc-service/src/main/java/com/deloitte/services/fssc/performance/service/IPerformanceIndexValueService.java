package com.deloitte.services.fssc.performance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexValueQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexValueForm;
import com.deloitte.services.fssc.performance.entity.PerformanceIndexValue;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-03
 * @Description : PerformanceIndex服务类接口
 * @Modified :
 */
public interface IPerformanceIndexValueService extends IService<PerformanceIndexValue> {


    /**
     * 批量 更新或者保存
     * @param indexValueFormList
     * @return
     */
    void updateOrSaveBatch(List<PerformanceIndexValueForm> indexValueFormList);

    /**
     * 按照编码查询
     * @param code
     * @return
     */
    PerformanceIndexValue getIndexValueByCode(String code);

    /**
     * 生效/失效,如果新状态与原有状态相同将抛异常
     * @param idList
     * @param validFlag
     * @return boolean
     */
    boolean modifyValidFlag(List<Long> idList, String validFlag);

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<PerformanceIndexValue>
     */
    IPage<PerformanceIndexValue> selectPage(PerformanceIndexValueQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<PerformanceIndexValue>
     */
    List<PerformanceIndexValue> selectList(PerformanceIndexValueQueryForm queryForm);
}
