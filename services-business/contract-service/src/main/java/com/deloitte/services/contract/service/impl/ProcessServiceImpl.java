package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.ProcessQueryForm;
import com.deloitte.services.contract.entity.Process;
import com.deloitte.services.contract.mapper.ProcessMapper;
import com.deloitte.services.contract.service.IProcessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-30
 * @Description :  Process服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ProcessServiceImpl extends ServiceImpl<ProcessMapper, Process> implements IProcessService {


    @Autowired
    private ProcessMapper processMapper;

    @Override
    public IPage<Process> selectPage(ProcessQueryForm queryForm ) {
        QueryWrapper<Process> queryWrapper = new QueryWrapper <Process>();
        //getQueryWrapper(queryWrapper,queryForm);
        return processMapper.selectPage(new Page<Process>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Process> selectList(ProcessQueryForm queryForm) {
        QueryWrapper<Process> queryWrapper = new QueryWrapper <Process>();
        getQueryWrapper(queryWrapper,queryForm);
        return processMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     * */
    public QueryWrapper<Process> getQueryWrapper(QueryWrapper<Process> queryWrapper,ProcessQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getOrgCode())){
            queryWrapper.eq(Process.ORG_CODE,queryForm.getOrgCode());
        }
        if(StringUtils.isNotBlank(queryForm.getIncomeExpenditureTypeCode())){
            queryWrapper.eq(Process.INCOME_EXPENDITURE_TYPE_CODE,queryForm.getIncomeExpenditureTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getContractAmountMin())){
            queryWrapper.lt(Process.CONTRACT_AMOUNT_MIN,queryForm.getContractAmountMin());
        }
        if(StringUtils.isNotBlank(queryForm.getContractAmountMax())){
            queryWrapper.ge(Process.CONTRACT_AMOUNT_MAX,queryForm.getContractAmountMax());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessType())){
            queryWrapper.eq(Process.PROCESS_TYPE,queryForm.getProcessType());
        }
        /*if(StringUtils.isNotBlank(queryForm.getProcessDefineKey())){
            queryWrapper.eq(Process.PROCESS_DEFINE_KEY,queryForm.getProcessDefineKey());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessDefineName())){
            queryWrapper.eq(Process.PROCESS_DEFINE_NAME,queryForm.getProcessDefineName());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Process.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Process.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Process.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Process.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(Process.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(Process.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(Process.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(Process.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(Process.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(Process.SPARE_FIELD_5,queryForm.getSpareField5());
        }*/
        return queryWrapper;
    }

    /**
     * 根据审批选择的key获取作废时需要用的key
     * @param processDefineKey
     * @return
     */
    public Process getEndKeyByStartKey(String processDefineKey){
        return processMapper.getEndKeyByStartKey(processDefineKey);
    }
}

