package com.deloitte.services.fssc.performance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.performance.param.PerformanceIndexQueryForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexValueForm;
import com.deloitte.platform.api.fssc.performance.vo.PerformanceIndexVo;
import com.deloitte.services.fssc.performance.entity.PerformanceIndex;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.fssc.performance.entity.PerformanceIndexValue;
import java.util.List;
import java.util.Map;

/**
 * @Author : jaws
 * @Date : Create in 2019-04-08
 * @Description : PerformanceIndex服务类接口
 * @Modified :
 */
public interface IPerformanceIndexService extends IService<PerformanceIndex> {


    /**
     * 批量 更新或者保存
     * @param indexFormList
     * @return
     */
    void updateOrSaveBatch(List<PerformanceIndexForm> indexFormList);

    /**
     * 按照编码查询
     * @param code
     * @return
     */
    PerformanceIndex getIndexByCode(String code);

    /**
     *
     * @param codeList
     * @return
     */
    Map<String,PerformanceIndex> selectIndexByCodes(List<String> codeList);

    /**
     * 根据关键字查询
     * @param name 指标名称
     * @param indexLibraryId 指标库id
     * @param valueJudgeCondition 判断条件
     * @param valueUnitId 指标单位id
     * @param level 级别
     * @return
     */
    PerformanceIndex getIndexByKeyWord(String name,Long indexLibraryId,String valueJudgeCondition,
                                       Long valueUnitId,String level);

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
     * @return IPage<PerformanceIndexVo>
     */
    IPage<PerformanceIndexVo> selectVoPage(PerformanceIndexQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<PerformanceIndex>
     */
    List<PerformanceIndex> selectList(PerformanceIndexQueryForm queryForm);

    /**
     * 根据指标库id集合查询是否被指标关联
     * @param idList
     * @return
     */
    boolean existsByLibraryIds(List<Long> idList);

    /**
     * 根据指标库id集合查询是否被指标关联
     * @param idList
     * @param validFlag 启用状态
     * @return
     */
    boolean existsByLibraryIds(List<Long> idList,String validFlag);

    /**
     * 根据指标值id集合查询是否被指标关联
     * @param idList
     * @return
     */
    boolean existsByValueIds(List<Long> idList);

    /**
     * 根据指标值id集合查询是否被指标关联
     * @param idList
     * @param validFlag 启用状态
     * @return
     */
    boolean existsByValueIds(List<Long> idList,String validFlag);

    /**
     * 查询是否被子指标关联
     * @param idList
     * @return
     */
    boolean existsByChild(List<Long> idList);

    /**
     * 查询是否被子指标关联
     * @param idList
     * @param validFlag 启用状态
     * @return
     */
    boolean existsByChild(List<Long> idList,String validFlag);

}
