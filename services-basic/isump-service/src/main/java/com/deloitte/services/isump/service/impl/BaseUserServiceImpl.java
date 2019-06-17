package com.deloitte.services.isump.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.isump.param.BaseUserQueryForm;
import com.deloitte.platform.api.isump.param.BaseUserQueryParam;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.services.isump.entity.BaseUser;
import com.deloitte.services.isump.mapper.BaseUserMapper;
import com.deloitte.services.isump.service.IBaseUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  BaseUser服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BaseUserServiceImpl extends ServiceImpl<BaseUserMapper, BaseUser> implements IBaseUserService {


    @Autowired
    private BaseUserMapper baseUserMapper;

    @Override
    public IPage<BaseUser> selectPage(BaseUserQueryForm queryForm ) {
        QueryWrapper<BaseUser> queryWrapper = new QueryWrapper <BaseUser>();
        getQueryWrapper(queryWrapper,queryForm);
        return baseUserMapper.selectPage(new Page<BaseUser>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<BaseUser> selectList(BaseUserQueryForm queryForm) {
        QueryWrapper<BaseUser> queryWrapper = new QueryWrapper <BaseUser>();
        getQueryWrapper(queryWrapper,queryForm);
        return baseUserMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<BaseUser> getQueryWrapper(QueryWrapper<BaseUser> queryWrapper, BaseQueryForm<BaseUserQueryParam> queryForm){
        BaseUserQueryParam baseUser = queryForm.toParam(BaseUserQueryParam.class);
        //条件拼接
        if(baseUser.getUserId() != null){
            queryWrapper.eq(BaseUser.USER_ID,baseUser.getUserId());
        }
        if(StringUtils.isNotBlank(baseUser.getIdentityCard())){
            queryWrapper.like(BaseUser.IDENTITY_CARD,baseUser.getIdentityCard());
        }
        if(StringUtils.isNotBlank(baseUser.getAddress())){
            queryWrapper.like(BaseUser.ADDRESS,baseUser.getAddress());
        }
        if(baseUser.getBirthday() != null){
            queryWrapper.like(BaseUser.BIRTHDAY,baseUser.getBirthday());
        }
        if(StringUtils.isNotBlank(baseUser.getPassportNo())){
            queryWrapper.like(BaseUser.PASSPORT_NO,baseUser.getPassportNo());
        }
        if(StringUtils.isNotBlank(baseUser.getCompany())){
            queryWrapper.like(BaseUser.COMPANY,baseUser.getCompany());
        }
        if(StringUtils.isNotBlank(baseUser.getPosition())){
            queryWrapper.like(BaseUser.POSITION,baseUser.getPosition());
        }
        if(StringUtils.isNotBlank(baseUser.getEducation())){
            queryWrapper.like(BaseUser.EDUCATION,baseUser.getEducation());
        }
        if(StringUtils.isNotBlank(baseUser.getGender())){
            queryWrapper.eq(BaseUser.GENDER,baseUser.getGender());
        }
        if(StringUtils.isNotBlank(baseUser.getRemark())){
            queryWrapper.like(BaseUser.REMARK,baseUser.getRemark());
        }
        if(StringUtils.isNotBlank(baseUser.getReserve())){
            queryWrapper.eq(BaseUser.RESERVE,baseUser.getReserve());
        }
        if(StringUtils.isNotBlank(baseUser.getVersion())){
            queryWrapper.eq(BaseUser.VERSION,baseUser.getVersion());
        }
        if(baseUser.getCreateBy() != null){
            queryWrapper.eq(BaseUser.CREATE_BY,baseUser.getCreateBy());
        }
        if(baseUser.getUpdateBy() != null){
            queryWrapper.eq(BaseUser.UPDATE_BY,baseUser.getUpdateBy());
        }
        return queryWrapper;
    }
}

