package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.ProcessExecuterTransferQueryForm;
import com.deloitte.services.contract.entity.ProcessExecuterTransfer;
import com.deloitte.services.contract.mapper.ProcessExecuterTransferMapper;
import com.deloitte.services.contract.service.IProcessExecuterTransferService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  ProcessExecuterTransfer服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ProcessExecuterTransferServiceImpl extends ServiceImpl<ProcessExecuterTransferMapper, ProcessExecuterTransfer> implements IProcessExecuterTransferService {


    @Autowired
    private ProcessExecuterTransferMapper processExecuterTransferMapper;

    @Override
    public IPage<ProcessExecuterTransfer> selectPage(ProcessExecuterTransferQueryForm queryForm ) {
        QueryWrapper<ProcessExecuterTransfer> queryWrapper = new QueryWrapper <ProcessExecuterTransfer>();
        //getQueryWrapper(queryWrapper,queryForm);
        return processExecuterTransferMapper.selectPage(new Page<ProcessExecuterTransfer>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ProcessExecuterTransfer> selectList(ProcessExecuterTransferQueryForm queryForm) {
        QueryWrapper<ProcessExecuterTransfer> queryWrapper = new QueryWrapper <ProcessExecuterTransfer>();
        //getQueryWrapper(queryWrapper,queryForm);
        return processExecuterTransferMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ProcessExecuterTransfer> getQueryWrapper(QueryWrapper<ProcessExecuterTransfer> queryWrapper,ProcessExecuterTransferQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getContractId())){
            queryWrapper.eq(ProcessExecuterTransfer.CONTRACT_ID,queryForm.getContractId());
        }
        if(StringUtils.isNotBlank(queryForm.getExecuterCode())){
            queryWrapper.eq(ProcessExecuterTransfer.EXECUTER_CODE,queryForm.getExecuterCode());
        }
        if(StringUtils.isNotBlank(queryForm.getExecuter())){
            queryWrapper.eq(ProcessExecuterTransfer.EXECUTER,queryForm.getExecuter());
        }
        if(StringUtils.isNotBlank(queryForm.getNewExecuterCode())){
            queryWrapper.eq(ProcessExecuterTransfer.NEW_EXECUTER_CODE,queryForm.getNewExecuterCode());
        }
        if(StringUtils.isNotBlank(queryForm.getNewExecuter())){
            queryWrapper.eq(ProcessExecuterTransfer.NEW_EXECUTER,queryForm.getNewExecuter());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessId())){
            queryWrapper.eq(ProcessExecuterTransfer.PROCESS_ID,queryForm.getProcessId());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessInstanceId())){
            queryWrapper.eq(ProcessExecuterTransfer.PROCESS_INSTANCE_ID,queryForm.getProcessInstanceId());
        }
        if(StringUtils.isNotBlank(queryForm.getStatue())){
            queryWrapper.eq(ProcessExecuterTransfer.STATUE,queryForm.getStatue());
        }
        if(StringUtils.isNotBlank(queryForm.getSendTime())){
            queryWrapper.eq(ProcessExecuterTransfer.SEND_TIME,queryForm.getSendTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ProcessExecuterTransfer.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(ProcessExecuterTransfer.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(ProcessExecuterTransfer.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(ProcessExecuterTransfer.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(ProcessExecuterTransfer.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(ProcessExecuterTransfer.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        return queryWrapper;
    }
     */
}

