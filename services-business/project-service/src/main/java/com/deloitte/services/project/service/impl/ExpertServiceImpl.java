package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.ExpertQueryForm;
import com.deloitte.services.project.entity.Expert;
import com.deloitte.services.project.mapper.ExpertMapper;
import com.deloitte.services.project.service.IExpertService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-16
 * @Description :  Expert服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ExpertServiceImpl extends ServiceImpl<ExpertMapper, Expert> implements IExpertService {


    @Autowired
    private ExpertMapper expertMapper;

    @Override
    public IPage<Expert> selectPage(ExpertQueryForm queryForm ) {
        QueryWrapper<Expert> queryWrapper = new QueryWrapper <Expert>();
        //getQueryWrapper(queryWrapper,queryForm);
        return expertMapper.selectPage(new Page<Expert>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Expert> selectList(ExpertQueryForm queryForm) {
        QueryWrapper<Expert> queryWrapper = new QueryWrapper <Expert>();
        //getQueryWrapper(queryWrapper,queryForm);
        return expertMapper.selectList(queryWrapper);
    }

    @Override
    public List<Expert> getList(String applicationId) {
        QueryWrapper<Expert> queryWrapper = new QueryWrapper <Expert>();
        queryWrapper.eq(Expert.APPLICATION_ID, applicationId);
        return expertMapper.selectList(queryWrapper);
    }

    @Override
    public void deleteList(String applicationId) {
        QueryWrapper<Expert> queryWrapper = new QueryWrapper <Expert>();
        queryWrapper.eq(Expert.APPLICATION_ID, applicationId);
        expertMapper.delete(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Expert> getQueryWrapper(QueryWrapper<Expert> queryWrapper,ExpertQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getExpertId())){
            queryWrapper.eq(Expert.EXPERT_ID,queryForm.getExpertId());
        }
        if(StringUtils.isNotBlank(queryForm.getStaffNo())){
            queryWrapper.eq(Expert.STAFF_NO,queryForm.getStaffNo());
        }
        if(StringUtils.isNotBlank(queryForm.getExpertName())){
            queryWrapper.eq(Expert.EXPERT_NAME,queryForm.getExpertName());
        }
        if(StringUtils.isNotBlank(queryForm.getGender())){
            queryWrapper.eq(Expert.GENDER,queryForm.getGender());
        }
        if(StringUtils.isNotBlank(queryForm.getExpertPost())){
            queryWrapper.eq(Expert.EXPERT_POST,queryForm.getExpertPost());
        }
        if(StringUtils.isNotBlank(queryForm.getCompany())){
            queryWrapper.eq(Expert.COMPANY,queryForm.getCompany());
        }
        if(StringUtils.isNotBlank(queryForm.getSubject())){
            queryWrapper.eq(Expert.SUBJECT,queryForm.getSubject());
        }
        if(StringUtils.isNotBlank(queryForm.getGoodAtField())){
            queryWrapper.eq(Expert.GOOD_AT_FIELD,queryForm.getGoodAtField());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Expert.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Expert.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Expert.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Expert.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(Expert.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(Expert.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(Expert.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(Expert.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(Expert.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(Expert.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(Expert.ORG_PATH,queryForm.getOrgPath());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(Expert.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(Expert.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getCompanyId())){
            queryWrapper.eq(Expert.COMPANY_ID,queryForm.getCompanyId());
        }
        return queryWrapper;
    }
     */
}

