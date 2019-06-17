package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.ProcessOperatorTransferQueryForm;
import com.deloitte.services.contract.entity.ProcessOperatorTransfer;
import com.deloitte.services.contract.mapper.ProcessOperatorTransferMapper;
import com.deloitte.services.contract.service.IProcessOperatorTransferService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  ProcessOperatorTransfer服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ProcessOperatorTransferServiceImpl extends ServiceImpl<ProcessOperatorTransferMapper, ProcessOperatorTransfer> implements IProcessOperatorTransferService {


    @Autowired
    private ProcessOperatorTransferMapper processOperatorTransferMapper;

    @Override
    public IPage<ProcessOperatorTransfer> selectPage(ProcessOperatorTransferQueryForm queryForm ) {
        QueryWrapper<ProcessOperatorTransfer> queryWrapper = new QueryWrapper <ProcessOperatorTransfer>();
        //getQueryWrapper(queryWrapper,queryForm);
        return processOperatorTransferMapper.selectPage(new Page<ProcessOperatorTransfer>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ProcessOperatorTransfer> selectList(ProcessOperatorTransferQueryForm queryForm) {
        QueryWrapper<ProcessOperatorTransfer> queryWrapper = new QueryWrapper <ProcessOperatorTransfer>();
        //getQueryWrapper(queryWrapper,queryForm);
        return processOperatorTransferMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ProcessOperatorTransfer> getQueryWrapper(QueryWrapper<ProcessOperatorTransfer> queryWrapper,ProcessOperatorTransferQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getContractId())){
            queryWrapper.eq(ProcessOperatorTransfer.CONTRACT_ID,queryForm.getContractId());
        }
        if(StringUtils.isNotBlank(queryForm.getOperatorCode())){
            queryWrapper.eq(ProcessOperatorTransfer.OPERATOR_CODE,queryForm.getOperatorCode());
        }
        if(StringUtils.isNotBlank(queryForm.getOperator())){
            queryWrapper.eq(ProcessOperatorTransfer.OPERATOR,queryForm.getOperator());
        }
        if(StringUtils.isNotBlank(queryForm.getNewOperatorCode())){
            queryWrapper.eq(ProcessOperatorTransfer.NEW_OPERATOR_CODE,queryForm.getNewOperatorCode());
        }
        if(StringUtils.isNotBlank(queryForm.getNewOperator())){
            queryWrapper.eq(ProcessOperatorTransfer.NEW_OPERATOR,queryForm.getNewOperator());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessId())){
            queryWrapper.eq(ProcessOperatorTransfer.PROCESS_ID,queryForm.getProcessId());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessInstanceId())){
            queryWrapper.eq(ProcessOperatorTransfer.PROCESS_INSTANCE_ID,queryForm.getProcessInstanceId());
        }
        if(StringUtils.isNotBlank(queryForm.getStatue())){
            queryWrapper.eq(ProcessOperatorTransfer.STATUE,queryForm.getStatue());
        }
        if(StringUtils.isNotBlank(queryForm.getSendTime())){
            queryWrapper.eq(ProcessOperatorTransfer.SEND_TIME,queryForm.getSendTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ProcessOperatorTransfer.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(ProcessOperatorTransfer.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(ProcessOperatorTransfer.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(ProcessOperatorTransfer.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(ProcessOperatorTransfer.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(ProcessOperatorTransfer.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        return queryWrapper;
    }
     */
}

