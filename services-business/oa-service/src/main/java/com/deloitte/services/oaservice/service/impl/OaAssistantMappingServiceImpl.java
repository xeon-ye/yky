package com.deloitte.services.oaservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.oaservice.param.OaAssistantMappingQueryForm;
import com.deloitte.services.oaservice.entity.OaAssistantMapping;
import com.deloitte.services.oaservice.mapper.OaAssistantMappingMapper;
import com.deloitte.services.oaservice.service.IOaAssistantMappingService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-03
 * @Description :  OaAssistantMapping服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaAssistantMappingServiceImpl extends ServiceImpl<OaAssistantMappingMapper, OaAssistantMapping> implements IOaAssistantMappingService {


    @Autowired
    private OaAssistantMappingMapper oaAssistantMappingMapper;

    @Override
    public IPage<OaAssistantMapping> selectPage(OaAssistantMappingQueryForm queryForm ) {
        QueryWrapper<OaAssistantMapping> queryWrapper = new QueryWrapper <OaAssistantMapping>();
        //getQueryWrapper(queryWrapper,queryForm);
        return oaAssistantMappingMapper.selectPage(new Page<OaAssistantMapping>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<OaAssistantMapping> selectList(OaAssistantMappingQueryForm queryForm) {
        QueryWrapper<OaAssistantMapping> queryWrapper = new QueryWrapper <OaAssistantMapping>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaAssistantMappingMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     * */
    public QueryWrapper<OaAssistantMapping> getQueryWrapper(QueryWrapper<OaAssistantMapping> queryWrapper,OaAssistantMappingQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getUserId())){
            queryWrapper.eq(OaAssistantMapping.USER_ID,queryForm.getUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getUserName())){
            queryWrapper.eq(OaAssistantMapping.USER_NAME,queryForm.getUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getLeaderId())){
            queryWrapper.eq(OaAssistantMapping.LEADER_ID,queryForm.getLeaderId());
        }
        if(StringUtils.isNotBlank(queryForm.getLeaderName())){
            queryWrapper.eq(OaAssistantMapping.LEADER_NAME,queryForm.getLeaderName());
        }
        if(StringUtils.isNotBlank(queryForm.getLeaderDeptId())){
            queryWrapper.eq(OaAssistantMapping.LEADER_DEPT_ID,queryForm.getLeaderDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getLeaderDeptName())){
            queryWrapper.eq(OaAssistantMapping.LEADER_DEPT_NAME,queryForm.getLeaderDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getEnableFlag())){
            queryWrapper.eq(OaAssistantMapping.ENABLE_FLAG,queryForm.getEnableFlag());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(OaAssistantMapping.CREATE_BY,queryForm.getCreateBy());
        }
        if(queryForm.getCreateTime()!=null){
            queryWrapper.eq(OaAssistantMapping.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(OaAssistantMapping.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(queryForm.getUpdateTime()!=null){
            queryWrapper.eq(OaAssistantMapping.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(OaAssistantMapping.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(OaAssistantMapping.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(OaAssistantMapping.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(OaAssistantMapping.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(OaAssistantMapping.EXT5,queryForm.getExt5());
        }
        return queryWrapper;
    }

}

