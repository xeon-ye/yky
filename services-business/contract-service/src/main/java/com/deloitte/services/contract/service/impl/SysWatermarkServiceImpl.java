package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.SysWatermarkQueryForm;
import com.deloitte.services.contract.common.enums.ContractErrorType;
import com.deloitte.services.contract.common.util.AssertUtils;
import com.deloitte.services.contract.entity.SysWatermark;
import com.deloitte.services.contract.mapper.SysWatermarkMapper;
import com.deloitte.services.contract.service.ISysWatermarkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  SysWatermark服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SysWatermarkServiceImpl extends ServiceImpl<SysWatermarkMapper, SysWatermark> implements ISysWatermarkService {


    @Autowired
    private SysWatermarkMapper sysWatermarkMapper;

    @Override
    public IPage<SysWatermark> selectPage(SysWatermarkQueryForm queryForm ) {
        QueryWrapper<SysWatermark> queryWrapper = new QueryWrapper <SysWatermark>();
        //getQueryWrapper(queryWrapper,queryForm);
        return sysWatermarkMapper.selectPage(new Page<SysWatermark>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<SysWatermark> selectList(SysWatermarkQueryForm queryForm) {
        QueryWrapper<SysWatermark> queryWrapper = new QueryWrapper <SysWatermark>();
        //getQueryWrapper(queryWrapper,queryForm);
        return sysWatermarkMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<SysWatermark> getQueryWrapper(QueryWrapper<SysWatermark> queryWrapper,SysWatermarkQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCompanyCode())){
            queryWrapper.eq(SysWatermark.COMPANY_CODE,queryForm.getCompanyCode());
        }
        if(StringUtils.isNotBlank(queryForm.getCompanyName())){
            queryWrapper.eq(SysWatermark.COMPANY_NAME,queryForm.getCompanyName());
        }
        if(StringUtils.isNotBlank(queryForm.getDepartmentCode())){
            queryWrapper.eq(SysWatermark.DEPARTMENT_CODE,queryForm.getDepartmentCode());
        }
        if(StringUtils.isNotBlank(queryForm.getDepartmentName())){
            queryWrapper.eq(SysWatermark.DEPARTMENT_NAME,queryForm.getDepartmentName());
        }
        if(StringUtils.isNotBlank(queryForm.getWatermarkText())){
            queryWrapper.eq(SysWatermark.WATERMARK_TEXT,queryForm.getWatermarkText());
        }
        if(StringUtils.isNotBlank(queryForm.getDescribe())){
            queryWrapper.eq(SysWatermark.DESCRIBE,queryForm.getDescribe());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(SysWatermark.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(SysWatermark.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(SysWatermark.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(SysWatermark.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(SysWatermark.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(SysWatermark.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(SysWatermark.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(SysWatermark.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(SysWatermark.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(SysWatermark.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        return queryWrapper;
    }
     */

    public String getWatermark(String departmentCode){
        List<SysWatermark> sysWatermarkList = sysWatermarkMapper.getWatermark(departmentCode);
        SysWatermark sysWatermark = null;
        AssertUtils.asserts(null == sysWatermarkList ||  sysWatermarkList.size() == 0 , ContractErrorType.CONTRACTNO_GET_WATERMARK);
        sysWatermark = sysWatermarkList.get(0);
        String type = sysWatermark.getType();
        String watermarkText = "";
        if ("WAT1000".equals(type)){
            //时间格式
            String text = sysWatermark.getWatermarkText();
            LocalDateTime validTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(text);
            watermarkText = validTime.format(formatter);
        }else if ("WAT2000".equals(type)){
            //文本格式
            watermarkText = sysWatermark.getWatermarkText();
        }
        return watermarkText;
    }
}

