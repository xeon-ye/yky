package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.EquipmentTransformQueryForm;
import com.deloitte.services.project.entity.EquipmentTransform;
import com.deloitte.services.project.mapper.EquipmentTransformMapper;
import com.deloitte.services.project.service.IEquipmentTransformService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  EquipmentTransform服务实现类
 * @Modified :
 */
@Service
@Transactional
public class EquipmentTransformServiceImpl extends ServiceImpl<EquipmentTransformMapper, EquipmentTransform> implements IEquipmentTransformService {


    @Autowired
    private EquipmentTransformMapper equipmentTransformMapper;

    @Override
    public IPage<EquipmentTransform> selectPage(EquipmentTransformQueryForm queryForm ) {
        QueryWrapper<EquipmentTransform> queryWrapper = new QueryWrapper <EquipmentTransform>();
        //getQueryWrapper(queryWrapper,queryForm);
        return equipmentTransformMapper.selectPage(new Page<EquipmentTransform>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<EquipmentTransform> selectList(EquipmentTransformQueryForm queryForm) {
        QueryWrapper<EquipmentTransform> queryWrapper = new QueryWrapper <EquipmentTransform>();
        //getQueryWrapper(queryWrapper,queryForm);
        return equipmentTransformMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<EquipmentTransform> getQueryWrapper(QueryWrapper<EquipmentTransform> queryWrapper,EquipmentTransformQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getImprovementsId())){
            queryWrapper.eq(EquipmentTransform.IMPROVEMENTS_ID,queryForm.getImprovementsId());
        }
        if(StringUtils.isNotBlank(queryForm.getAttachmentId())){
            queryWrapper.eq(EquipmentTransform.ATTACHMENT_ID,queryForm.getAttachmentId());
        }
        if(StringUtils.isNotBlank(queryForm.getEquAbstract())){
            queryWrapper.eq(EquipmentTransform.EQU_ABSTRACT,queryForm.getEquAbstract());
        }
        if(StringUtils.isNotBlank(queryForm.getEquDate())){
            queryWrapper.eq(EquipmentTransform.EQU_DATE,queryForm.getEquDate());
        }
        if(StringUtils.isNotBlank(queryForm.getEquValue())){
            queryWrapper.eq(EquipmentTransform.EQU_VALUE,queryForm.getEquValue());
        }
        if(StringUtils.isNotBlank(queryForm.getEquUse())){
            queryWrapper.eq(EquipmentTransform.EQU_USE,queryForm.getEquUse());
        }
        if(StringUtils.isNotBlank(queryForm.getEquCycle())){
            queryWrapper.eq(EquipmentTransform.EQU_CYCLE,queryForm.getEquCycle());
        }
        if(StringUtils.isNotBlank(queryForm.getFundingCenter())){
            queryWrapper.eq(EquipmentTransform.FUNDING_CENTER,queryForm.getFundingCenter());
        }
        if(StringUtils.isNotBlank(queryForm.getFundingDirector())){
            queryWrapper.eq(EquipmentTransform.FUNDING_DIRECTOR,queryForm.getFundingDirector());
        }
        if(StringUtils.isNotBlank(queryForm.getFundingOther())){
            queryWrapper.eq(EquipmentTransform.FUNDING_OTHER,queryForm.getFundingOther());
        }
        if(StringUtils.isNotBlank(queryForm.getFundingTotal())){
            queryWrapper.eq(EquipmentTransform.FUNDING_TOTAL,queryForm.getFundingTotal());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(EquipmentTransform.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(EquipmentTransform.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(EquipmentTransform.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(EquipmentTransform.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(EquipmentTransform.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(EquipmentTransform.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(EquipmentTransform.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(EquipmentTransform.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(EquipmentTransform.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(EquipmentTransform.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(EquipmentTransform.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

