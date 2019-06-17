package com.deloitte.services.fssc.business.carryforward.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.carryforward.param.DssScientificPayQueryForm;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.fssc.business.carryforward.entity.DssScientificPay;
import com.deloitte.services.fssc.business.carryforward.mapper.DssScientificPayMapper;
import com.deloitte.services.fssc.business.carryforward.service.IDssScientificPayService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : chenx
 * @Date : Create in 2019-06-11
 * @Description :  DssScientificPay服务实现类
 * @Modified :
 */
@Service
@Transactional
public class DssScientificPayServiceImpl extends ServiceImpl<DssScientificPayMapper, DssScientificPay> implements IDssScientificPayService {


    @Autowired
    private DssScientificPayMapper dssScientificPayMapper;

    @Override
    public IPage<DssScientificPay> selectPage(DssScientificPayQueryForm queryForm ) {
        QueryWrapper<DssScientificPay> queryWrapper = new QueryWrapper <DssScientificPay>();
        getQueryWrapper(queryWrapper,queryForm);
        return dssScientificPayMapper.selectPage(new Page<DssScientificPay>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<DssScientificPay> selectList(DssScientificPayQueryForm queryForm) {
        QueryWrapper<DssScientificPay> queryWrapper = new QueryWrapper <DssScientificPay>();
        getQueryWrapper(queryWrapper,queryForm);
        return dssScientificPayMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     * */
    public QueryWrapper<DssScientificPay> getQueryWrapper(QueryWrapper<DssScientificPay> queryWrapper,DssScientificPayQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getFromSystem())){
            queryWrapper.eq(DssScientificPay.FROM_SYSTEM,queryForm.getFromSystem());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentNum())){
            queryWrapper.eq(DssScientificPay.DOCUMENT_NUM,queryForm.getDocumentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getPayDate())){
            queryWrapper.eq(DssScientificPay.PAY_DATE,queryForm.getPayDate());
        }
        if(queryForm.getFunds()!=null){
            queryWrapper.eq(DssScientificPay.FUNDS,queryForm.getFunds());
        }
        if(StringUtils.isNotBlank(queryForm.getProCode())){
            queryWrapper.eq(DssScientificPay.PRO_CODE,queryForm.getProCode());
        }
        if(StringUtils.isNotBlank(queryForm.getTaskCode())){
            queryWrapper.eq(DssScientificPay.TASK_CODE,queryForm.getTaskCode());
        }
        if(StringUtils.isNotBlank(queryForm.getBudgetYear())){
            queryWrapper.eq(DssScientificPay.BUDGET_YEAR,queryForm.getBudgetYear());
        }
        if(StringUtils.isNotBlank(queryForm.getPhyletic())){
            queryWrapper.eq(DssScientificPay.PHYLETIC,queryForm.getPhyletic());
        }
        if(StringUtils.isNotBlank(queryForm.getPhyleticName())){
            queryWrapper.eq(DssScientificPay.PHYLETIC_NAME,queryForm.getPhyleticName());
        }

        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(DssScientificPay.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getStatus())){
            queryWrapper.eq(DssScientificPay.STATUS,queryForm.getStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getEconomicClassificationCode())){
            queryWrapper.eq(DssScientificPay.ECONOMIC_CLASSIFICATION_CODE,queryForm.getEconomicClassificationCode());
        }
        return queryWrapper;
    }

}

