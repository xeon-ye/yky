package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.EquipmentQueryForm;
import com.deloitte.services.project.entity.Equipment;
import com.deloitte.services.project.mapper.EquipmentMapper;
import com.deloitte.services.project.service.IEquipmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  Equipment服务实现类
 * @Modified :
 */
@Service
@Transactional
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements IEquipmentService {


    @Autowired
    private EquipmentMapper equipmentMapper;

    @Override
    public IPage<Equipment> selectPage(EquipmentQueryForm queryForm ) {
        QueryWrapper<Equipment> queryWrapper = new QueryWrapper <Equipment>();
        //getQueryWrapper(queryWrapper,queryForm);
        return equipmentMapper.selectPage(new Page<Equipment>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Equipment> selectList(EquipmentQueryForm queryForm) {
        QueryWrapper<Equipment> queryWrapper = new QueryWrapper <Equipment>();
        //getQueryWrapper(queryWrapper,queryForm);
        return equipmentMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Equipment> getQueryWrapper(QueryWrapper<Equipment> queryWrapper,EquipmentQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getImprovementsId())){
            queryWrapper.eq(Equipment.IMPROVEMENTS_ID,queryForm.getImprovementsId());
        }
        if(StringUtils.isNotBlank(queryForm.getAttachmentId())){
            queryWrapper.eq(Equipment.ATTACHMENT_ID,queryForm.getAttachmentId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectAbs())){
            queryWrapper.eq(Equipment.PROJECT_ABS,queryForm.getProjectAbs());
        }
        if(StringUtils.isNotBlank(queryForm.getModel())){
            queryWrapper.eq(Equipment.MODEL,queryForm.getModel());
        }
        if(StringUtils.isNotBlank(queryForm.getEquAddress())){
            queryWrapper.eq(Equipment.EQU_ADDRESS,queryForm.getEquAddress());
        }
        if(StringUtils.isNotBlank(queryForm.getEquUse())){
            queryWrapper.eq(Equipment.EQU_USE,queryForm.getEquUse());
        }
        if(StringUtils.isNotBlank(queryForm.getEquNum())){
            queryWrapper.eq(Equipment.EQU_NUM,queryForm.getEquNum());
        }
        if(StringUtils.isNotBlank(queryForm.getEquCycle())){
            queryWrapper.eq(Equipment.EQU_CYCLE,queryForm.getEquCycle());
        }
        if(StringUtils.isNotBlank(queryForm.getFundingCenter())){
            queryWrapper.eq(Equipment.FUNDING_CENTER,queryForm.getFundingCenter());
        }
        if(StringUtils.isNotBlank(queryForm.getFundingDirector())){
            queryWrapper.eq(Equipment.FUNDING_DIRECTOR,queryForm.getFundingDirector());
        }
        if(StringUtils.isNotBlank(queryForm.getFundingOther())){
            queryWrapper.eq(Equipment.FUNDING_OTHER,queryForm.getFundingOther());
        }
        if(StringUtils.isNotBlank(queryForm.getFundingTotal())){
            queryWrapper.eq(Equipment.FUNDING_TOTAL,queryForm.getFundingTotal());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Equipment.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Equipment.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Equipment.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Equipment.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(Equipment.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(Equipment.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(Equipment.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(Equipment.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(Equipment.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(Equipment.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(Equipment.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

