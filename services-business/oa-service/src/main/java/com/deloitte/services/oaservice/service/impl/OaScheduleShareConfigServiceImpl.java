package com.deloitte.services.oaservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.oaservice.param.OaScheduleShareConfigQueryForm;
import com.deloitte.services.oaservice.entity.OaScheduleShareConfig;
import com.deloitte.services.oaservice.mapper.OaScheduleShareConfigMapper;
import com.deloitte.services.oaservice.service.IOaScheduleShareConfigService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-23
 * @Description :  OaScheduleShareConfig服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OaScheduleShareConfigServiceImpl extends ServiceImpl<OaScheduleShareConfigMapper, OaScheduleShareConfig> implements IOaScheduleShareConfigService {


    @Autowired
    private OaScheduleShareConfigMapper oaScheduleShareConfigMapper;

    @Override
    public IPage<OaScheduleShareConfig> selectPage(OaScheduleShareConfigQueryForm queryForm ) {
        QueryWrapper<OaScheduleShareConfig> queryWrapper = new QueryWrapper <OaScheduleShareConfig>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaScheduleShareConfigMapper.selectPage(new Page<OaScheduleShareConfig>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<OaScheduleShareConfig> selectList(OaScheduleShareConfigQueryForm queryForm) {
        QueryWrapper<OaScheduleShareConfig> queryWrapper = new QueryWrapper <OaScheduleShareConfig>();
        getQueryWrapper(queryWrapper,queryForm);
        return oaScheduleShareConfigMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     * */
    public QueryWrapper<OaScheduleShareConfig> getQueryWrapper(QueryWrapper<OaScheduleShareConfig> queryWrapper,OaScheduleShareConfigQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getUserId())){
            queryWrapper.eq(OaScheduleShareConfig.USER_ID,queryForm.getUserId());
        }
        if(StringUtils.isNotBlank(queryForm.getUserName())){
            queryWrapper.eq(OaScheduleShareConfig.USER_NAME,queryForm.getUserName());
        }
        if(StringUtils.isNotBlank(queryForm.getShareToId())){
            queryWrapper.eq(OaScheduleShareConfig.SHARE_TO_ID,queryForm.getShareToId());
        }
        if(StringUtils.isNotBlank(queryForm.getShareToName())){
            queryWrapper.eq(OaScheduleShareConfig.SHARE_TO_NAME,queryForm.getShareToName());
        }
        if(StringUtils.isNotBlank(queryForm.getShareToDeptId())){
            queryWrapper.eq(OaScheduleShareConfig.SHARE_TO_DEPT_ID,queryForm.getShareToDeptId());
        }
        if(StringUtils.isNotBlank(queryForm.getShareToDeptName())){
            queryWrapper.eq(OaScheduleShareConfig.SHARE_TO_DEPT_NAME,queryForm.getShareToDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getPermissions())){
            queryWrapper.eq(OaScheduleShareConfig.PERMISSIONS,queryForm.getPermissions());
        }
        if(StringUtils.isNotBlank(queryForm.getEnableFlag())){
            queryWrapper.eq(OaScheduleShareConfig.ENABLE_FLAG,queryForm.getEnableFlag());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(OaScheduleShareConfig.CREATE_BY,queryForm.getCreateBy());
        }

        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(OaScheduleShareConfig.UPDATE_BY,queryForm.getUpdateBy());
        }

        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(OaScheduleShareConfig.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(OaScheduleShareConfig.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(OaScheduleShareConfig.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(OaScheduleShareConfig.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(OaScheduleShareConfig.EXT5,queryForm.getExt5());
        }
        return queryWrapper;
    }

}

