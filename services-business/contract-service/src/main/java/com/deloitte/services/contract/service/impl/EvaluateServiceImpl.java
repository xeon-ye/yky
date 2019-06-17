package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.EvaluateQueryForm;
import com.deloitte.services.contract.entity.Evaluate;
import com.deloitte.services.contract.mapper.EvaluateMapper;
import com.deloitte.services.contract.service.IEvaluateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : hemingzheng
 * @Date : Create in 2019-04-25
 * @Description :  Evaluate服务实现类
 * @Modified :
 */
@Service
@Transactional
public class EvaluateServiceImpl extends ServiceImpl<EvaluateMapper, Evaluate> implements IEvaluateService {


    @Autowired
    private EvaluateMapper evaluateMapper;

    @Override
    public IPage<Evaluate> selectPage(EvaluateQueryForm queryForm ) {
        QueryWrapper<Evaluate> queryWrapper = new QueryWrapper <Evaluate>();
        //getQueryWrapper(queryWrapper,queryForm);
        return evaluateMapper.selectPage(new Page<Evaluate>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Evaluate> selectList(EvaluateQueryForm queryForm) {
        QueryWrapper<Evaluate> queryWrapper = new QueryWrapper <Evaluate>();
        //getQueryWrapper(queryWrapper,queryForm);
        return evaluateMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Evaluate> getQueryWrapper(QueryWrapper<Evaluate> queryWrapper,EvaluateQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getContractId())){
            queryWrapper.eq(Evaluate.CONTRACT_ID,queryForm.getContractId());
        }
        if(StringUtils.isNotBlank(queryForm.getContractName())){
            queryWrapper.eq(Evaluate.CONTRACT_NAME,queryForm.getContractName());
        }
        if(StringUtils.isNotBlank(queryForm.getSignStrategyImplementation())){
            queryWrapper.eq(Evaluate.SIGN_STRATEGY_IMPLEMENTATION,queryForm.getSignStrategyImplementation());
        }
        if(StringUtils.isNotBlank(queryForm.getSignBiddingRiskanalysis())){
            queryWrapper.eq(Evaluate.SIGN_BIDDING_RISKANALYSIS,queryForm.getSignBiddingRiskanalysis());
        }
        if(StringUtils.isNotBlank(queryForm.getSignQuotationsLessons())){
            queryWrapper.eq(Evaluate.SIGN_QUOTATIONS_LESSONS,queryForm.getSignQuotationsLessons());
        }
        if(StringUtils.isNotBlank(queryForm.getSignLessonsCareful())){
            queryWrapper.eq(Evaluate.SIGN_LESSONS_CAREFUL,queryForm.getSignLessonsCareful());
        }
        if(StringUtils.isNotBlank(queryForm.getSignCoordinationEvaluation())){
            queryWrapper.eq(Evaluate.SIGN_COORDINATION_EVALUATION,queryForm.getSignCoordinationEvaluation());
        }
        if(StringUtils.isNotBlank(queryForm.getPerformIsNormal())){
            queryWrapper.eq(Evaluate.PERFORM_IS_NORMAL,queryForm.getPerformIsNormal());
        }
        if(StringUtils.isNotBlank(queryForm.getPerformSpecialCase())){
            queryWrapper.eq(Evaluate.PERFORM_SPECIAL_CASE,queryForm.getPerformSpecialCase());
        }
        if(StringUtils.isNotBlank(queryForm.getPerformProsCons())){
            queryWrapper.eq(Evaluate.PERFORM_PROS_CONS,queryForm.getPerformProsCons());
        }
        if(StringUtils.isNotBlank(queryForm.getPerformCoordinatProblem())){
            queryWrapper.eq(Evaluate.PERFORM_COORDINAT_PROBLEM,queryForm.getPerformCoordinatProblem());
        }
        if(StringUtils.isNotBlank(queryForm.getPerformOther())){
            queryWrapper.eq(Evaluate.PERFORM_OTHER,queryForm.getPerformOther());
        }
        if(StringUtils.isNotBlank(queryForm.getTermsProsCons())){
            queryWrapper.eq(Evaluate.TERMS_PROS_CONS,queryForm.getTermsProsCons());
        }
        if(StringUtils.isNotBlank(queryForm.getTermsSpecialpromResult())){
            queryWrapper.eq(Evaluate.TERMS_SPECIALPROM_RESULT,queryForm.getTermsSpecialpromResult());
        }
        if(StringUtils.isNotBlank(queryForm.getTermsExpressionPromotion())){
            queryWrapper.eq(Evaluate.TERMS_EXPRESSION_PROMOTION,queryForm.getTermsExpressionPromotion());
        }
        if(StringUtils.isNotBlank(queryForm.getTermsOther())){
            queryWrapper.eq(Evaluate.TERMS_OTHER,queryForm.getTermsOther());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Evaluate.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Evaluate.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Evaluate.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Evaluate.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(Evaluate.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(Evaluate.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(Evaluate.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(Evaluate.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(Evaluate.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        return queryWrapper;
    }
     */
}

