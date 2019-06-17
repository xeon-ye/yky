package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.AppAttachmentQueryForm;
import com.deloitte.services.project.entity.AppAttachment;
import com.deloitte.services.project.mapper.AppAttachmentMapper;
import com.deloitte.services.project.service.IAppAttachmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  AppAttachment服务实现类
 * @Modified :
 */
@Service
@Transactional
public class AppAttachmentServiceImpl extends ServiceImpl<AppAttachmentMapper, AppAttachment> implements IAppAttachmentService {


    @Autowired
    private AppAttachmentMapper appAttachmentMapper;

    @Override
    public IPage<AppAttachment> selectPage(AppAttachmentQueryForm queryForm ) {
        QueryWrapper<AppAttachment> queryWrapper = new QueryWrapper <AppAttachment>();
        //getQueryWrapper(queryWrapper,queryForm);
        return appAttachmentMapper.selectPage(new Page<AppAttachment>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<AppAttachment> selectList(AppAttachmentQueryForm queryForm) {
        QueryWrapper<AppAttachment> queryWrapper = new QueryWrapper <AppAttachment>();
        //getQueryWrapper(queryWrapper,queryForm);
        return appAttachmentMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<AppAttachment> getQueryWrapper(QueryWrapper<AppAttachment> queryWrapper,AppAttachmentQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getHouseRenovationId())){
            queryWrapper.eq(AppAttachment.HOUSE_RENOVATION_ID,queryForm.getHouseRenovationId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(AppAttachment.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getAttachmentId())){
            queryWrapper.eq(AppAttachment.ATTACHMENT_ID,queryForm.getAttachmentId());
        }
        if(StringUtils.isNotBlank(queryForm.getStartDate())){
            queryWrapper.eq(AppAttachment.START_DATE,queryForm.getStartDate());
        }
        if(StringUtils.isNotBlank(queryForm.getBuiltUpArea())){
            queryWrapper.eq(AppAttachment.BUILT_UP_AREA,queryForm.getBuiltUpArea());
        }
        if(StringUtils.isNotBlank(queryForm.getRepairArea())){
            queryWrapper.eq(AppAttachment.REPAIR_AREA,queryForm.getRepairArea());
        }
        if(StringUtils.isNotBlank(queryForm.getOperateCycle())){
            queryWrapper.eq(AppAttachment.OPERATE_CYCLE,queryForm.getOperateCycle());
        }
        if(StringUtils.isNotBlank(queryForm.getContents())){
            queryWrapper.eq(AppAttachment.CONTENTS,queryForm.getContents());
        }
        if(StringUtils.isNotBlank(queryForm.getOriginalValue())){
            queryWrapper.eq(AppAttachment.ORIGINAL_VALUE,queryForm.getOriginalValue());
        }
        if(StringUtils.isNotBlank(queryForm.getSpecificationsAndModels())){
            queryWrapper.eq(AppAttachment.SPECIFICATIONS_AND_MODELS,queryForm.getSpecificationsAndModels());
        }
        if(StringUtils.isNotBlank(queryForm.getPlaceOfOrigin())){
            queryWrapper.eq(AppAttachment.PLACE_OF_ORIGIN,queryForm.getPlaceOfOrigin());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(AppAttachment.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(AppAttachment.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(AppAttachment.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(AppAttachment.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(AppAttachment.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(AppAttachment.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(AppAttachment.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(AppAttachment.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(AppAttachment.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(AppAttachment.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(AppAttachment.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

