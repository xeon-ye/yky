package com.deloitte.services.dss.scientific.service.impl;

import com.deloitte.platform.api.dss.scientific.vo.*;
import com.deloitte.services.dss.scientific.mapper.SrpmsProjectMapper;
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

    @Override
    public SrpmsProjectVo queryProjectStage(Map param) {
        return srpmsProjectMapper.queryProjectStage(param);
    }

    @Override
    public List<DeptApplyProjectVo> queryDeptApplyNumAndFunds(Map param) {
        return srpmsProjectMapper.queryDeptApplyNumAndFunds(param);
    }

    /**
     * 各单位立项项目数量和金额及单位参与人数
     * @param param
     * @return
     */
    @Override
    public  List<DeptApplyProjectVo> queryApprovalNumAndFunds(Map param){
        return srpmsProjectMapper.queryApprovalNumAndFunds(param);
    }


    @Override
    public ProjectApplyNumVo queryApplyNums(Map map) {
        return srpmsProjectMapper.queryApplyNums(map);
    }

    @Override
    public List<DeptProjectPersonVo> queryDeptProjectPerson(Map param) {
        return srpmsProjectMapper.queryDeptProjectPerson(param);
    }

    @Override
    public List<ProjectAmountPercentageVo> queryAmountPercentage(Map param) {
        return srpmsProjectMapper.queryAmountPercentage(param);
    }

    @Override
    public ProjectPersonPieChartVo queryPersonPercentage(Map param) {
        return srpmsProjectMapper.queryPersonPercentage(param);
    }

    @Override
    public List<DeptAllAmountPayVo> queryAllAmountPay(Map param) {
        return srpmsProjectMapper.queryAllAmountPay(param);
    }

    @Override
    public List<DeptAllAmountPayVo> queryAmountThisYear() {
        return srpmsProjectMapper.queryAmountThisYear();
    }

    @Override
    public List<DeptApplyProjectVo> queryConclude() {
        return srpmsProjectMapper.queryConclude();
    }

    @Override
    public List<ConcludeProjectFundVo> queryConcludeFund() {
        return srpmsProjectMapper.queryConcludeFund();
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
