package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaTechRewardTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaTechRewardTable;
import com.deloitte.services.srpmp.project.mpr.mapper.MprEvaTechRewardTableMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaTechRewardTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprEvaTechRewardTable服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprEvaTechRewardTableServiceImpl extends ServiceImpl<MprEvaTechRewardTableMapper, MprEvaTechRewardTable> implements IMprEvaTechRewardTableService {


    @Autowired
    private MprEvaTechRewardTableMapper mprEvaTechRewardTableMapper;

    @Override
    public IPage<MprEvaTechRewardTable> selectPage(MprEvaTechRewardTableQueryForm queryForm ) {
        QueryWrapper<MprEvaTechRewardTable> queryWrapper = new QueryWrapper <MprEvaTechRewardTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaTechRewardTableMapper.selectPage(new Page<MprEvaTechRewardTable>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprEvaTechRewardTable> selectList(MprEvaTechRewardTableQueryForm queryForm) {
        QueryWrapper<MprEvaTechRewardTable> queryWrapper = new QueryWrapper <MprEvaTechRewardTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaTechRewardTableMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprEvaTechRewardTable> getQueryWrapper(QueryWrapper<MprEvaTechRewardTable> queryWrapper,MprEvaTechRewardTableQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprEvaTechRewardTable.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getAchieveName())){
            queryWrapper.eq(MprEvaTechRewardTable.ACHIEVE_NAME,queryForm.getAchieveName());
        }
        if(StringUtils.isNotBlank(queryForm.getObtainPerson())){
            queryWrapper.eq(MprEvaTechRewardTable.OBTAIN_PERSON,queryForm.getObtainPerson());
        }
        if(StringUtils.isNotBlank(queryForm.getAwardCategory())){
            queryWrapper.eq(MprEvaTechRewardTable.AWARD_CATEGORY,queryForm.getAwardCategory());
        }
        if(StringUtils.isNotBlank(queryForm.getAwardLevel())){
            queryWrapper.eq(MprEvaTechRewardTable.AWARD_LEVEL,queryForm.getAwardLevel());
        }
        if(StringUtils.isNotBlank(queryForm.getAwardYear())){
            queryWrapper.eq(MprEvaTechRewardTable.AWARD_YEAR,queryForm.getAwardYear());
        }
        if(StringUtils.isNotBlank(queryForm.getAwardName())){
            queryWrapper.eq(MprEvaTechRewardTable.AWARD_NAME,queryForm.getAwardName());
        }
        if(StringUtils.isNotBlank(queryForm.getCertNum())){
            queryWrapper.eq(MprEvaTechRewardTable.CERT_NUM,queryForm.getCertNum());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprEvaTechRewardTable.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprEvaTechRewardTable.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprEvaTechRewardTable.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprEvaTechRewardTable.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

