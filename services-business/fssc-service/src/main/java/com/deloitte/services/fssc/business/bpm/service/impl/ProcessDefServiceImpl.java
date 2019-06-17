package com.deloitte.services.fssc.business.bpm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.bpm.param.ProcessDefQueryForm;
import com.deloitte.services.fssc.business.bpm.entity.ProcessDef;
import com.deloitte.services.fssc.business.bpm.mapper.ProcessDefMapper;
import com.deloitte.services.fssc.business.bpm.service.IProcessDefService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-03-18
 * @Description :  ProcessDef服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ProcessDefServiceImpl extends ServiceImpl<ProcessDefMapper, ProcessDef> implements IProcessDefService {


    @Autowired
    private ProcessDefMapper processDefMapper;

    @Override
    public IPage<ProcessDef> selectPage(ProcessDefQueryForm queryForm ) {
        QueryWrapper<ProcessDef> queryWrapper = new QueryWrapper <ProcessDef>();
        //getQueryWrapper(queryWrapper,queryForm);
        return processDefMapper.selectPage(new Page<ProcessDef>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ProcessDef> selectList(ProcessDefQueryForm queryForm) {
        QueryWrapper<ProcessDef> queryWrapper = new QueryWrapper <ProcessDef>();
        //getQueryWrapper(queryWrapper,queryForm);
        return processDefMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ProcessDef> getQueryWrapper(QueryWrapper<ProcessDef> queryWrapper,ProcessDefQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ProcessDef.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(ProcessDef.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ProcessDef.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ProcessDef.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ProcessDef.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(ProcessDef.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getEx1())){
            queryWrapper.eq(ProcessDef.EX1,queryForm.getEx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEx2())){
            queryWrapper.eq(ProcessDef.EX2,queryForm.getEx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEx3())){
            queryWrapper.eq(ProcessDef.EX3,queryForm.getEx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEx4())){
            queryWrapper.eq(ProcessDef.EX4,queryForm.getEx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEx5())){
            queryWrapper.eq(ProcessDef.EX5,queryForm.getEx5());
        }
        if(StringUtils.isNotBlank(queryForm.getEx6())){
            queryWrapper.eq(ProcessDef.EX6,queryForm.getEx6());
        }
        if(StringUtils.isNotBlank(queryForm.getEx7())){
            queryWrapper.eq(ProcessDef.EX7,queryForm.getEx7());
        }
        if(StringUtils.isNotBlank(queryForm.getEx8())){
            queryWrapper.eq(ProcessDef.EX8,queryForm.getEx8());
        }
        if(StringUtils.isNotBlank(queryForm.getEx9())){
            queryWrapper.eq(ProcessDef.EX9,queryForm.getEx9());
        }
        if(StringUtils.isNotBlank(queryForm.getEx10())){
            queryWrapper.eq(ProcessDef.EX10,queryForm.getEx10());
        }
        if(StringUtils.isNotBlank(queryForm.getEx11())){
            queryWrapper.eq(ProcessDef.EX11,queryForm.getEx11());
        }
        if(StringUtils.isNotBlank(queryForm.getEx12())){
            queryWrapper.eq(ProcessDef.EX12,queryForm.getEx12());
        }
        if(StringUtils.isNotBlank(queryForm.getEx13())){
            queryWrapper.eq(ProcessDef.EX13,queryForm.getEx13());
        }
        if(StringUtils.isNotBlank(queryForm.getEx14())){
            queryWrapper.eq(ProcessDef.EX14,queryForm.getEx14());
        }
        if(StringUtils.isNotBlank(queryForm.getEx15())){
            queryWrapper.eq(ProcessDef.EX15,queryForm.getEx15());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessKey())){
            queryWrapper.eq(ProcessDef.PROCESS_KEY,queryForm.getProcessKey());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessName())){
            queryWrapper.eq(ProcessDef.PROCESS_NAME,queryForm.getProcessName());
        }
        if(StringUtils.isNotBlank(queryForm.getProcessType())){
            queryWrapper.eq(ProcessDef.PROCESS_TYPE,queryForm.getProcessType());
        }
        if(StringUtils.isNotBlank(queryForm.getDocumentTable())){
            queryWrapper.eq(ProcessDef.DOCUMENT_TABLE,queryForm.getDocumentTable());
        }
        if(StringUtils.isNotBlank(queryForm.getIsValid())){
            queryWrapper.eq(ProcessDef.IS_VALID,queryForm.getIsValid());
        }
        return queryWrapper;
    }
     */
}

