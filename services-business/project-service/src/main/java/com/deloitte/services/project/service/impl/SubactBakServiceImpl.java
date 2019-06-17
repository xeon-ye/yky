package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.SubactBakQueryForm;
import com.deloitte.services.project.entity.SubactBak;
import com.deloitte.services.project.mapper.SubactBakMapper;
import com.deloitte.services.project.service.ISubactBakService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-28
 * @Description :  SubactBak服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SubactBakServiceImpl extends ServiceImpl<SubactBakMapper, SubactBak> implements ISubactBakService {


    @Autowired
    private SubactBakMapper subactBakMapper;

    @Override
    public IPage<SubactBak> selectPage(SubactBakQueryForm queryForm ) {
        QueryWrapper<SubactBak> queryWrapper = new QueryWrapper <SubactBak>();
        //getQueryWrapper(queryWrapper,queryForm);
        return subactBakMapper.selectPage(new Page<SubactBak>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<SubactBak> selectList(SubactBakQueryForm queryForm) {
        QueryWrapper<SubactBak> queryWrapper = new QueryWrapper <SubactBak>();
        //getQueryWrapper(queryWrapper,queryForm);
        return subactBakMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteALLList(String actBakId) {
        QueryWrapper<SubactBak> queryWrapper = new QueryWrapper <SubactBak>();
        queryWrapper.eq(SubactBak.ACT_ID,actBakId);
        subactBakMapper.delete(queryWrapper);
    }

    @Override
    public List<SubactBak> getAllList(String actBakId) {
        QueryWrapper<SubactBak> queryWrapper = new QueryWrapper <SubactBak>();
        queryWrapper.eq(SubactBak.ACT_ID,actBakId);
        return subactBakMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<SubactBak> getQueryWrapper(QueryWrapper<SubactBak> queryWrapper,SubactBakQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getSubactId())){
            queryWrapper.eq(SubactBak.SUBACT_ID,queryForm.getSubactId());
        }
        if(StringUtils.isNotBlank(queryForm.getActId())){
            queryWrapper.eq(SubactBak.ACT_ID,queryForm.getActId());
        }
        if(StringUtils.isNotBlank(queryForm.getSubact())){
            queryWrapper.eq(SubactBak.SUBACT,queryForm.getSubact());
        }
        if(StringUtils.isNotBlank(queryForm.getSubactAbs())){
            queryWrapper.eq(SubactBak.SUBACT_ABS,queryForm.getSubactAbs());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(SubactBak.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(SubactBak.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(SubactBak.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(SubactBak.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewAmount())){
            queryWrapper.eq(SubactBak.REVIEW_AMOUNT,queryForm.getReviewAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(SubactBak.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(SubactBak.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(SubactBak.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(SubactBak.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(SubactBak.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(SubactBak.ORG_PATH,queryForm.getOrgPath());
        }
        if(StringUtils.isNotBlank(queryForm.getIsRelated())){
            queryWrapper.eq(SubactBak.IS_RELATED,queryForm.getIsRelated());
        }
        return queryWrapper;
    }
     */
}

