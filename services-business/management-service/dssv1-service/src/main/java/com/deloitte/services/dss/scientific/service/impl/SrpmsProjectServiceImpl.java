package com.deloitte.services.dss.scientific.service.impl;

import com.deloitte.platform.api.dss.scientific.vo.*;
import com.deloitte.services.dss.scientific.mapper.SrpmsProjectMapper;
import com.deloitte.services.dss.scientific.service.ICategoryService;
import com.deloitte.services.dss.scientific.service.ISrpmsProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


@Service
@Transactional
public class SrpmsProjectServiceImpl implements ISrpmsProjectService {

    @Autowired
    private SrpmsProjectMapper srpmsProjectMapper;
    @Autowired
    private ICategoryService categoryService;

    @Override
    public SrpmsProjectVo queryProjectStage(String category) {
        return srpmsProjectMapper.queryProjectStage(category);
    }

    @Override
    public List<DeptApplyProjectVo> queryDeptApplyNumAndFunds(String category) {
        return srpmsProjectMapper.queryDeptApplyNumAndFunds(category);
    }

    /**
     * 各单位立项项目数量和金额及单位参与人数
     * @param category
     * @return
     */
    @Override
    public  List<DeptApplyProjectVo> queryApprovalNumAndFunds(String category){
        return srpmsProjectMapper.queryApprovalNumAndFunds(category);
    }


    @Override
    public ProjectApplyNumVo queryApplyNums(String category) {
        return srpmsProjectMapper.queryApplyNums(category);
    }

    @Override
    public List<DeptProjectPersonVo> queryDeptProjectPerson(Map param) {
        return srpmsProjectMapper.queryDeptProjectPerson(param);
    }

    @Override
    public List<ProjectAmountPercentageVo> queryAmountPercentage(String category) {
        return srpmsProjectMapper.queryAmountPercentage(category);
    }

    @Override
    public ProjectPersonPieChartVo queryPersonPercentage(String category) {
        return srpmsProjectMapper.queryPersonPercentage(category);
    }

    @Override
    public List<DeptAllAmountPayVo> queryAllAmountPay(String category) {
        String categoryTotal = categoryService.getCategory(category);
        return srpmsProjectMapper.queryAllAmountPay(categoryTotal);
    }

    @Override
    public List<DeptAllAmountPayVo> queryAmountThisYear(String category) {
        String categoryData = categoryService.getCategory(category);
        return srpmsProjectMapper.queryAmountThisYear(categoryData);
    }

    @Override
    public List<DeptApplyProjectVo> queryConclude(String category) {
        String categoryData = categoryService.getCategory(category);
        return srpmsProjectMapper.queryConclude(categoryData);
    }

    @Override
    public List<ConcludeProjectFundVo> queryConcludeFund(String category) {
        String categoryData = categoryService.getCategory(category);
        return srpmsProjectMapper.queryConcludeFund(categoryData);
    }

    @Override
    public List<DeptAchievementVo> queryAchievement() {
        return srpmsProjectMapper.queryAchievement();
    }

    @Override
    public List<DeptPaperSCIVo> queryPaper() {
        return srpmsProjectMapper.queryPaper();
    }
}
