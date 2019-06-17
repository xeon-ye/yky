package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.SysCommonLanguageQueryForm;
import com.deloitte.services.contract.entity.SysCommonLanguage;
import com.deloitte.services.contract.mapper.SysCommonLanguageMapper;
import com.deloitte.services.contract.service.ISysCommonLanguageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  SysCommonLanguage服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SysCommonLanguageServiceImpl extends ServiceImpl<SysCommonLanguageMapper, SysCommonLanguage> implements ISysCommonLanguageService {


    @Autowired
    private SysCommonLanguageMapper sysCommonLanguageMapper;

    @Override
    public IPage<SysCommonLanguage> selectPage(SysCommonLanguageQueryForm queryForm ) {
        QueryWrapper<SysCommonLanguage> queryWrapper = new QueryWrapper <SysCommonLanguage>();
        //getQueryWrapper(queryWrapper,queryForm);
        return sysCommonLanguageMapper.selectPage(new Page<SysCommonLanguage>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<SysCommonLanguage> selectList(SysCommonLanguageQueryForm queryForm) {
        QueryWrapper<SysCommonLanguage> queryWrapper = new QueryWrapper <SysCommonLanguage>();
        //getQueryWrapper(queryWrapper,queryForm);
        return sysCommonLanguageMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<SysCommonLanguage> getQueryWrapper(QueryWrapper<SysCommonLanguage> queryWrapper,SysCommonLanguageQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getApplicationCode())){
            queryWrapper.eq(SysCommonLanguage.APPLICATION_CODE,queryForm.getApplicationCode());
        }
        if(StringUtils.isNotBlank(queryForm.getApplication())){
            queryWrapper.eq(SysCommonLanguage.APPLICATION,queryForm.getApplication());
        }
        if(StringUtils.isNotBlank(queryForm.getContext())){
            queryWrapper.eq(SysCommonLanguage.CONTEXT,queryForm.getContext());
        }
        if(StringUtils.isNotBlank(queryForm.getBelongPerson())){
            queryWrapper.eq(SysCommonLanguage.BELONG_PERSON,queryForm.getBelongPerson());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(SysCommonLanguage.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(SysCommonLanguage.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(SysCommonLanguage.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(SysCommonLanguage.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(SysCommonLanguage.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(SysCommonLanguage.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(SysCommonLanguage.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(SysCommonLanguage.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        return queryWrapper;
    }
     */
}

