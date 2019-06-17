package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.SubactQueryForm;
import com.deloitte.services.project.entity.Subact;
import com.deloitte.services.project.mapper.SubactMapper;
import com.deloitte.services.project.service.ISubactService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-28
 * @Description :  Subact服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SubactServiceImpl extends ServiceImpl<SubactMapper, Subact> implements ISubactService {


    @Autowired
    private SubactMapper subactMapper;

    @Override
    public IPage<Subact> selectPage(SubactQueryForm queryForm ) {
        QueryWrapper<Subact> queryWrapper = new QueryWrapper <Subact>();
        //getQueryWrapper(queryWrapper,queryForm);
        return subactMapper.selectPage(new Page<Subact>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Subact> selectList(SubactQueryForm queryForm) {
        QueryWrapper<Subact> queryWrapper = new QueryWrapper <Subact>();
        //getQueryWrapper(queryWrapper,queryForm);
        return subactMapper.selectList(queryWrapper);
    }

    @Override
    public List<Subact> getSubActList(String actId) {
        QueryWrapper<Subact> queryWrapper = new QueryWrapper <Subact>();
        queryWrapper.eq(Subact.ACT_ID, actId);
        return subactMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteSubActList(String actId) {
        QueryWrapper<Subact> queryWrapper = new QueryWrapper <Subact>();
        queryWrapper.eq(Subact.ACT_ID, actId);
        subactMapper.delete(queryWrapper);
    }
    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Subact> getQueryWrapper(QueryWrapper<Subact> queryWrapper,SubactQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getSubactId())){
            queryWrapper.eq(Subact.SUBACT_ID,queryForm.getSubactId());
        }
        if(StringUtils.isNotBlank(queryForm.getActId())){
            queryWrapper.eq(Subact.ACT_ID,queryForm.getActId());
        }
        if(StringUtils.isNotBlank(queryForm.getSubact())){
            queryWrapper.eq(Subact.SUBACT,queryForm.getSubact());
        }
        if(StringUtils.isNotBlank(queryForm.getSubactAbs())){
            queryWrapper.eq(Subact.SUBACT_ABS,queryForm.getSubactAbs());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Subact.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Subact.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Subact.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Subact.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewAmount())){
            queryWrapper.eq(Subact.REVIEW_AMOUNT,queryForm.getReviewAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(Subact.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(Subact.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(Subact.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(Subact.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(Subact.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(Subact.ORG_PATH,queryForm.getOrgPath());
        }
        if(StringUtils.isNotBlank(queryForm.getIsRelated())){
            queryWrapper.eq(Subact.IS_RELATED,queryForm.getIsRelated());
        }
        return queryWrapper;
    }
     */
}

