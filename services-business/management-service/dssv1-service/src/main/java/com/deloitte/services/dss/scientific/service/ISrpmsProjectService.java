package com.deloitte.services.dss.scientific.service;

import com.deloitte.platform.api.dss.scientific.vo.*;

import java.util.List;
import java.util.Map;

/**
 * 科研项目接口
 */
public interface ISrpmsProjectService {

    /**
     * 统计各阶段项目数量
     * @return
     */
    SrpmsProjectVo queryProjectStage(String category);


    /**
     * 各单位申请项目数量和金额
     * @param category
     * @return
     */
    List<DeptApplyProjectVo> queryDeptApplyNumAndFunds(String category);


    /**
     * 各单位立项项目数量和金额及单位参与人数
     * @param category
     * @return
     */
    List<DeptApplyProjectVo> queryApprovalNumAndFunds(String category);



    /**
     * 申请个阶段数量
     * @param category
     * @return
     */
    ProjectApplyNumVo  queryApplyNums(String category);
    /**
     * 各单位申请项目数量和金额及单位参与人数
     * @param param
     * @return
     */
    List<DeptProjectPersonVo> queryDeptProjectPerson(Map param);


    /**
     * 立项项目项目预算情况饼状图
     * @param category
     * @return
     */
    List<ProjectAmountPercentageVo> queryAmountPercentage(String category);



    /**
     * 立项项目成员比例饼状图
     * @param category
     * @return
     */
    ProjectPersonPieChartVo queryPersonPercentage(String category);




    /**
     * 各所院项目至今经费执行情况
     * @param category
     * @return
     */
    List<DeptAllAmountPayVo> queryAllAmountPay(String category);


    /**
     * 各所院项目本年经费执行
     * @return
     */
    List<DeptAllAmountPayVo> queryAmountThisYear(String category);


    /**
     * 各所院结题项目数量分布情况
     * @return
     */
    List<DeptApplyProjectVo> queryConclude(String category);


    /**
     * 各所院结题项目数量分布情况
     * @return
     */
    List<ConcludeProjectFundVo> queryConcludeFund(String category);


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
