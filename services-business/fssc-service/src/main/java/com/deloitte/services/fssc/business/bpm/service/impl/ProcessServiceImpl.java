package com.deloitte.services.fssc.business.bpm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.bpm.param.ProcessQueryForm;
import com.deloitte.services.fssc.business.bpm.entity.Process;
import com.deloitte.services.fssc.business.bpm.mapper.ProcessMapper;
import com.deloitte.services.fssc.business.bpm.service.IProcessService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-03-18
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
        //getQueryWrapper(queryWrapper,queryForm);
        return processMapper.selectList(queryWrapper);
    }

    public String findStatus(Long documentId,String documentType){
        return processMapper.findStatus(documentId,documentType);
    }
    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Process> getQueryWrapper(QueryWrapper<Process> queryWrapper,ProcessQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Process.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(Process.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Process.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Process.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Process.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(Process.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getEx1())){
            queryWrapper.eq(Process.EX1,queryForm.getEx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEx2())){
            queryWrapper.eq(Process.EX2,queryForm.getEx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEx3())){
            queryWrapper.eq(Process.EX3,queryForm.getEx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEx4())){
            queryWrapper.eq(Process.EX4,queryForm.getEx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEx5())){
            queryWrapper.eq(Process.EX5,queryForm.getEx5());
        }
        if(StringUtils.isNotBlank(queryForm.getEx6())){
            queryWrapper.eq(Process.EX6,queryForm.getEx6());
        }
        if(StringUtils.isNotBlank(queryForm.getEx7())){
            queryWrapper.eq(Process.EX7,queryForm.getEx7());
        }
        if(StringUtils.isNotBlank(queryForm.getEx8())){
            queryWrapper.eq(Process.EX8,queryForm.getEx8());
        }
        if(StringUtils.isNotBlank(queryForm.getEx9())){
            queryWrapper.eq(Process.EX9,queryForm.getEx9());
        }
        if(StringUtils.isNotBlank(queryForm.getEx10())){
            queryWrapper.eq(Process.EX10,queryForm.getEx10());
        }
        if(StringUtils.isNotBlank(queryForm.getEx11())){
            queryWrapper.eq(Process.EX11,queryForm.getEx11());
        }
        if(StringUtils.isNotBlank(queryForm.getEx12())){
            queryWrapper.eq(Process.EX12,queryForm.getEx12());
        }
        if(StringUtils.isNotBlank(queryForm.getEx13())){
            queryWrapper.eq(Process.EX13,queryForm.getEx13());
        }
        if(StringUtils.isNotBlank(queryForm.getEx14())){
            queryWrapper.eq(Process.EX14,queryForm.getEx14());
        }
        if(StringUtils.isNotBlank(queryForm.getEx15())){
            queryWrapper.eq(Process.EX15,queryForm.getEx15());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessId())){
            queryWrapper.eq(Process.PROCESS_ID,queryForm.getProcessId());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessDefId())){
            queryWrapper.eq(Process.PROCESS_DEF_ID,queryForm.getProcessDefId());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessStatus())){
            queryWrapper.eq(Process.PROCESS_STATUS,queryForm.getProcessStatus());
        }
        if(StringUtils.isNotBlank(queryForm.getIsFinshed())){
            queryWrapper.eq(Process.IS_FINSHED,queryForm.getIsFinshed());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentId())){
            queryWrapper.eq(Process.DOCUMENT_ID,queryForm.getDocumentId());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentType())){
            queryWrapper.eq(Process.DOCUMENT_TYPE,queryForm.getDocumentType());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentNum())){
            queryWrapper.eq(Process.DOCUMENT_NUM,queryForm.getDocumentNum());
        }
        if(StringUtils.isNotBlank(queryForm.getIsValid())){
            queryWrapper.eq(Process.IS_VALID,queryForm.getIsValid());
        }
        if(StringUtils.isNotBlank(queryForm.getEndTime())){
            queryWrapper.eq(Process.END_TIME,queryForm.getEndTime());
        }
        return queryWrapper;
    }
     */
}

