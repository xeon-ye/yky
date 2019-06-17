package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.ProcessContractStopQueryForm;
import com.deloitte.services.contract.entity.ProcessContractStop;
import com.deloitte.services.contract.mapper.ProcessContractStopMapper;
import com.deloitte.services.contract.service.IProcessContractStopService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  ProcessContractStop服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ProcessContractStopServiceImpl extends ServiceImpl<ProcessContractStopMapper, ProcessContractStop> implements IProcessContractStopService {


    @Autowired
    private ProcessContractStopMapper processContractStopMapper;

    @Override
    public IPage<ProcessContractStop> selectPage(ProcessContractStopQueryForm queryForm ) {
        QueryWrapper<ProcessContractStop> queryWrapper = new QueryWrapper <ProcessContractStop>();
        //getQueryWrapper(queryWrapper,queryForm);
        return processContractStopMapper.selectPage(new Page<ProcessContractStop>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ProcessContractStop> selectList(ProcessContractStopQueryForm queryForm) {
        QueryWrapper<ProcessContractStop> queryWrapper = new QueryWrapper <ProcessContractStop>();
        //getQueryWrapper(queryWrapper,queryForm);
        return processContractStopMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ProcessContractStop> getQueryWrapper(QueryWrapper<ProcessContractStop> queryWrapper,ProcessContractStopQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getContractId())){
            queryWrapper.eq(ProcessContractStop.CONTRACT_ID,queryForm.getContractId());
        }
        if(StringUtils.isNotBlank(queryForm.getSenderCode())){
            queryWrapper.eq(ProcessContractStop.SENDER_CODE,queryForm.getSenderCode());
        }
        if(StringUtils.isNotBlank(queryForm.getSneder())){
            queryWrapper.eq(ProcessContractStop.SNEDER,queryForm.getSneder());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessId())){
            queryWrapper.eq(ProcessContractStop.PROCESS_ID,queryForm.getProcessId());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessInstanceId())){
            queryWrapper.eq(ProcessContractStop.PROCESS_INSTANCE_ID,queryForm.getProcessInstanceId());
        }
        if(StringUtils.isNotBlank(queryForm.getStatue())){
            queryWrapper.eq(ProcessContractStop.STATUE,queryForm.getStatue());
        }
        if(StringUtils.isNotBlank(queryForm.getSendTime())){
            queryWrapper.eq(ProcessContractStop.SEND_TIME,queryForm.getSendTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ProcessContractStop.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(ProcessContractStop.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(ProcessContractStop.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(ProcessContractStop.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(ProcessContractStop.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(ProcessContractStop.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        return queryWrapper;
    }
     */
}

