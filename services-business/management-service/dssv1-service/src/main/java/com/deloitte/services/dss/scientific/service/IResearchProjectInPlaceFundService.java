package com.deloitte.services.dss.scientific.service;

import com.deloitte.platform.api.dss.scientific.vo.BudgetAmountImplementationRateVo;
import com.deloitte.platform.api.dss.scientific.vo.ProjectDetailInformationVo;
import com.deloitte.platform.api.dss.scientific.vo.ResearchProjectInPlaceFundVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @interfaceName: IResearchProjectInPlaceFundService
 * @Description:
 * @Auther: wangyanyun
 * @Date: 2019-03-11
 * @version: v1.0
 */
public interface IResearchProjectInPlaceFundService {

    /**
     * 各类科研项目到位经费情况
     * @return
     */
    List<ResearchProjectInPlaceFundVo> getAnnualInPlaceFund();

    /**
     *小类项目本年到位经费延续和新获项目分布情况
     * @param category
     * @return
     */
    List<ResearchProjectInPlaceFundVo> getSubclassNewAndContinuationFund(String category);

    /**
     *小类科研项目本年到位经费结题和在研项目分布情况
     * @param category
     * @return
     */
    List<ResearchProjectInPlaceFundVo> getSubclassResearchAndConclusionFund(String category);

    /**
     *各单位本年预算经费和支出
     * @param category
     * @return
     */
    List<BudgetAmountImplementationRateVo> getDeptBudgetAndExpendFund(String category);



    /**
     *项目本年预算支出
     * @param category
     * @param deptId
     * @return
     */
    List<BudgetAmountImplementationRateVo> getProjectBudgetAndExpendFundByDept( String category, Long deptId);



    /**
     *项目详情
     * @param projectNum
     * @return
     */
    ProjectDetailInformationVo getProjectDetail(String projectNum);
    /**
     *PI承担数量
     * @param projectNum
     * @return
     */
    ProjectDetailInformationVo queryPItotal(String projectNum);
}
