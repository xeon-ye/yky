package com.deloitte.services.fssc.system.attchdef.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.attchdef.param.BaseFileDefLineQueryForm;
import com.deloitte.services.fssc.system.attchdef.entity.BaseFileDefLine;
import com.deloitte.services.fssc.system.attchdef.mapper.BaseFileDefLineMapper;
import com.deloitte.services.fssc.system.attchdef.service.IBaseFileDefLineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-04-09
 * @Description :  BaseFileDefLine服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BaseFileDefLineServiceImpl extends ServiceImpl<BaseFileDefLineMapper, BaseFileDefLine> implements IBaseFileDefLineService {


    @Autowired
    private BaseFileDefLineMapper baseFileDefLineMapper;

    @Override
    public IPage<BaseFileDefLine> selectPage(BaseFileDefLineQueryForm queryForm ) {
        QueryWrapper<BaseFileDefLine> queryWrapper = new QueryWrapper <BaseFileDefLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return baseFileDefLineMapper.selectPage(new Page<BaseFileDefLine>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<BaseFileDefLine> selectList(BaseFileDefLineQueryForm queryForm) {
        QueryWrapper<BaseFileDefLine> queryWrapper = new QueryWrapper <BaseFileDefLine>();
        //getQueryWrapper(queryWrapper,queryForm);
        return baseFileDefLineMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<BaseFileDefLine> getQueryWrapper(QueryWrapper<BaseFileDefLine> queryWrapper,BaseFileDefLineQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(BaseFileDefLine.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateUserName())){
            queryWrapper.eq(BaseFileDefLine.CREATE_USER_NAME,queryForm.getCreateUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(BaseFileDefLine.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(BaseFileDefLine.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(BaseFileDefLine.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getVersion())){
            queryWrapper.eq(BaseFileDefLine.VERSION,queryForm.getVersion());
        }
        if(StringUtils.isNotBlank(queryForm.getFileDefId())){
            queryWrapper.eq(BaseFileDefLine.FILE_DEF_ID,queryForm.getFileDefId());
        }
        if(StringUtils.isNotBlank(queryForm.getAttachName())){
            queryWrapper.eq(BaseFileDefLine.ATTACH_NAME,queryForm.getAttachName());
        }
        if(StringUtils.isNotBlank(queryForm.getEx1())){
            queryWrapper.eq(BaseFileDefLine.EX1,queryForm.getEx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEx2())){
            queryWrapper.eq(BaseFileDefLine.EX2,queryForm.getEx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEx3())){
            queryWrapper.eq(BaseFileDefLine.EX3,queryForm.getEx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEx4())){
            queryWrapper.eq(BaseFileDefLine.EX4,queryForm.getEx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEx5())){
            queryWrapper.eq(BaseFileDefLine.EX5,queryForm.getEx5());
        }
        if(StringUtils.isNotBlank(queryForm.getEx6())){
            queryWrapper.eq(BaseFileDefLine.EX6,queryForm.getEx6());
        }
        if(StringUtils.isNotBlank(queryForm.getEx7())){
            queryWrapper.eq(BaseFileDefLine.EX7,queryForm.getEx7());
        }
        if(StringUtils.isNotBlank(queryForm.getEx8())){
            queryWrapper.eq(BaseFileDefLine.EX8,queryForm.getEx8());
        }
        if(StringUtils.isNotBlank(queryForm.getEx9())){
            queryWrapper.eq(BaseFileDefLine.EX9,queryForm.getEx9());
        }
        if(StringUtils.isNotBlank(queryForm.getEx10())){
            queryWrapper.eq(BaseFileDefLine.EX10,queryForm.getEx10());
        }
        if(StringUtils.isNotBlank(queryForm.getEx11())){
            queryWrapper.eq(BaseFileDefLine.EX11,queryForm.getEx11());
        }
        if(StringUtils.isNotBlank(queryForm.getEx12())){
            queryWrapper.eq(BaseFileDefLine.EX12,queryForm.getEx12());
        }
        if(StringUtils.isNotBlank(queryForm.getEx13())){
            queryWrapper.eq(BaseFileDefLine.EX13,queryForm.getEx13());
        }
        if(StringUtils.isNotBlank(queryForm.getEx14())){
            queryWrapper.eq(BaseFileDefLine.EX14,queryForm.getEx14());
        }
        if(StringUtils.isNotBlank(queryForm.getEx15())){
            queryWrapper.eq(BaseFileDefLine.EX15,queryForm.getEx15());
        }
        return queryWrapper;
    }
     */
}

