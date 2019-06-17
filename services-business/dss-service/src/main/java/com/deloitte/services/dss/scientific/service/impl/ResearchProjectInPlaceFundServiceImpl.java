package com.deloitte.services.dss.scientific.service.impl;

import com.deloitte.platform.api.dss.scientific.vo.BudgetAmountImplementationRateVo;
import com.deloitte.platform.api.dss.scientific.vo.ProjectDetailInformationVo;
import com.deloitte.platform.api.dss.scientific.vo.ResearchProjectInPlaceFundVo;
import com.deloitte.services.dss.scientific.mapper.ResearchProjectInPlaceFundMapper;
import com.deloitte.services.dss.scientific.service.IResearchProjectInPlaceFundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName: ResearchProjectInPlaceFundServiceImpl
 * @Description: 到位经费
 * @Auther: wangyanyun
 * @Date: 2019-03-11
 * @version: v1.0
 */
@Service
@Transactional
public class ResearchProjectInPlaceFundServiceImpl implements IResearchProjectInPlaceFundService {

    @Autowired
    private ResearchProjectInPlaceFundMapper fundMapper;

    @Override
    public List<ResearchProjectInPlaceFundVo> getAnnualInPlaceFund() {
        return fundMapper.getAnnualInPlaceFund();
    }

    @Override
    public List<ResearchProjectInPlaceFundVo> getSubclassNewAndContinuationFund(String category) {
        return fundMapper.getSubclassNewAndContinuationFund(category);
    }

    @Override
    public List<ResearchProjectInPlaceFundVo> getSubclassResearchAndConclusionFund(String category) {
        return fundMapper.getSubclassResearchAndConclusionFund(category);
    }

    @Override
    public List<BudgetAmountImplementationRateVo> getDeptBudgetAndExpendFund(String category) {
        return fundMapper.getDeptBudgetAndExpendFund(category);
    }

    @Override
    public List<BudgetAmountImplementationRateVo> getProjectBudgetAndExpendFundByDept(String category, Long deptId) {
        return fundMapper.getProjectBudgetAndExpendFundByDept(category,deptId);
    }


    @Override
    public ProjectDetailInformationVo getProjectDetail(String projectNum) {
        return fundMapper.getProjectDetail(projectNum);
    }

    public ProjectDetailInformationVo queryPItotal(String projectNum){
        return fundMapper.queryPItotal(projectNum);
    }


}
