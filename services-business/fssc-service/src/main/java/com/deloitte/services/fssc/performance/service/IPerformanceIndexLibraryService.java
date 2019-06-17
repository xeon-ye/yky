package com.deloitte.services.fssc.performance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexLibraryQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexLibraryForm;
import com.deloitte.services.fssc.performance.entity.PerformanceIndexLibrary;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-03
 * @Description : PerformanceIndexLibrary服务类接口
 * @Modified :
 */
public interface IPerformanceIndexLibraryService extends IService<PerformanceIndexLibrary> {



    /**
     * 批量 更新或者保存
     * @param indexLibraryFormList
     * @return
     */
    void updateOrSaveBatch(List<PerformanceIndexLibraryForm> indexLibraryFormList);

    /**
     * 按照编码查询
     * @param code
     * @return
     */
    PerformanceIndexLibrary getIndexLibraryByCode(String code);

    /**
     * 生效/失效,如果新状态与原有状态相同将抛异常
     * @param idList
     * @param validFlag
     * @return boolean
     */
    boolean modifyValidFlag(List<Long> idList, String validFlag);

    /**
     * 根据项目小类id集合查询是否被指标库关联
     * @param idList
     * @return
     */
    boolean existsBySubTypeIds(List<Long> idList);

    /**
     * 根据项目大类id集合查询是否被小类关联
     * @param idList
     * @param validFlag 启用状态
     * @return
     */
    boolean existsBySubTypeIds(List<Long> idList,String validFlag);

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<PerformanceIndexLibrary>
     */
    IPage<PerformanceIndexLibrary> selectPage(PerformanceIndexLibraryQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<PerformanceIndexLibrary>
     */
    List<PerformanceIndexLibrary> selectList(PerformanceIndexLibraryQueryForm queryForm);

}
