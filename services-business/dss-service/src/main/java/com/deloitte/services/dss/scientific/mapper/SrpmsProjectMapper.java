package com.deloitte.services.dss.scientific.mapper;

import com.deloitte.platform.api.dss.scientific.vo.*;

import java.util.List;
import java.util.Map;

/**
 * 科研项目接口
 */
public interface SrpmsProjectMapper {

    /**
     * 统计各阶段项目数量
     * @return
     */
    SrpmsProjectVo queryProjectStage(Map param);


    /**
     * 各单位申请项目数量和金额
     * @param param
     * @return
     */
    List<DeptApplyProjectVo> queryDeptApplyNumAndFunds(Map param);



    /**
     * 各单位立项项目数量和金额及单位参与人数
     * @param param
     * @return
     */
    List<DeptApplyProjectVo> queryApprovalNumAndFunds(Map param);


    /**
     * 申请个阶段数量
     * @param map
     * @return
     */
    ProjectApplyNumVo  queryApplyNums(Map map);



    /**
     * 各单位申请项目数量和金额及单位参与人数
     * @param param
     * @return
     */
    List<DeptProjectPersonVo> queryDeptProjectPerson(Map param);

    /**
     * 立项项目项目预算情况饼状图
     * @param param
     * @return
     */
    List<ProjectAmountPercentageVo> queryAmountPercentage(Map param);



    /**
     * 立项项目成员比例饼状图
     * @param param
     * @return
     */
    ProjectPersonPieChartVo queryPersonPercentage(Map param);


    /**
     * 各所院项目至今经费执行情况
     * @param param
     * @return
     */
    List<DeptAllAmountPayVo> queryAllAmountPay(Map param);


    /**
     * 各所院项目本年经费执行
     * @return
     */
    List<DeptAllAmountPayVo> queryAmountThisYear();


    /**
     * 各所院结题项目数量分布情况
     * @return
     */
    List<DeptApplyProjectVo> queryConclude();



    /**
     * 结题项目预算金额及总到位金额情况
     * @return
     */
    List<ConcludeProjectFundVo> queryConcludeFund();


    /**
     * 成果项目--各所院成果转化情况
     * @return
     */
    List<DeptAchievementVo> queryAchievement();


    /**
     * 成果项目--各所院论文发表情况
     * @return
     */
    List<DeptPaperSCIVo> queryPaper();
}
