package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.InsandequipQueryForm;
import com.deloitte.services.project.entity.Insandequip;
import com.deloitte.services.project.mapper.InsandequipMapper;
import com.deloitte.services.project.service.IInsandequipService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-31
 * @Description :  Insandequip服务实现类
 * @Modified :
 */
@Service
@Transactional
public class InsandequipServiceImpl extends ServiceImpl<InsandequipMapper, Insandequip> implements IInsandequipService {


    @Autowired
    private InsandequipMapper insandequipMapper;

    @Override
    public IPage<Insandequip> selectPage(InsandequipQueryForm queryForm ) {
        QueryWrapper<Insandequip> queryWrapper = new QueryWrapper <Insandequip>();
        //getQueryWrapper(queryWrapper,queryForm);
        return insandequipMapper.selectPage(new Page<Insandequip>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Insandequip> selectList(InsandequipQueryForm queryForm) {
        QueryWrapper<Insandequip> queryWrapper = new QueryWrapper <Insandequip>();
        //getQueryWrapper(queryWrapper,queryForm);
        return insandequipMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Insandequip> getQueryWrapper(QueryWrapper<Insandequip> queryWrapper,InsandequipQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Insandequip.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Insandequip.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Insandequip.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Insandequip.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(Insandequip.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(Insandequip.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(Insandequip.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(Insandequip.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(Insandequip.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(Insandequip.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(Insandequip.ORG_PATH,queryForm.getOrgPath());
        }
        if(StringUtils.isNotBlank(queryForm.getId())){
            queryWrapper.eq(Insandequip.ID,queryForm.getId());
        }
        if(StringUtils.isNotBlank(queryForm.getInsandequipId())){
            queryWrapper.eq(Insandequip.INSANDEQUIP_ID,queryForm.getInsandequipId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(Insandequip.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getClassifiedStatisticsCode())){
            queryWrapper.eq(Insandequip.CLASSIFIED_STATISTICS_CODE,queryForm.getClassifiedStatisticsCode());
        }
        if(StringUtils.isNotBlank(queryForm.getClassifiedStatisticsName())){
            queryWrapper.eq(Insandequip.CLASSIFIED_STATISTICS_NAME,queryForm.getClassifiedStatisticsName());
        }
        if(StringUtils.isNotBlank(queryForm.getTotalNumber())){
            queryWrapper.eq(Insandequip.TOTAL_NUMBER,queryForm.getTotalNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getUseNumber())){
            queryWrapper.eq(Insandequip.USE_NUMBER,queryForm.getUseNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getOriginalValue())){
            queryWrapper.eq(Insandequip.ORIGINAL_VALUE,queryForm.getOriginalValue());
        }
        if(StringUtils.isNotBlank(queryForm.getUseOriginalValue())){
            queryWrapper.eq(Insandequip.USE_ORIGINAL_VALUE,queryForm.getUseOriginalValue());
        }
        return queryWrapper;
    }
     */
}

