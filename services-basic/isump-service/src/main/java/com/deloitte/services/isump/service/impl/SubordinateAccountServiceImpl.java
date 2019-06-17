package com.deloitte.services.isump.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.isump.param.SubordinateAccountQueryForm;
import com.deloitte.platform.api.isump.param.SubordinateAccountQueryParam;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.services.isump.entity.SubordinateAccount;
import com.deloitte.services.isump.mapper.SubordinateAccountMapper;
import com.deloitte.services.isump.service.ISubordinateAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  SubordinateAccount服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SubordinateAccountServiceImpl extends ServiceImpl<SubordinateAccountMapper, SubordinateAccount> implements ISubordinateAccountService {


    @Autowired
    private SubordinateAccountMapper subordinateAccountMapper;

    @Override
    public IPage<SubordinateAccount> selectPage(SubordinateAccountQueryForm queryForm ) {
        QueryWrapper<SubordinateAccount> queryWrapper = new QueryWrapper <SubordinateAccount>();
        getQueryWrapper(queryWrapper,queryForm);
        return subordinateAccountMapper.selectPage(new Page<SubordinateAccount>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<SubordinateAccount> selectList(SubordinateAccountQueryForm queryForm) {
        QueryWrapper<SubordinateAccount> queryWrapper = new QueryWrapper <SubordinateAccount>();
        getQueryWrapper(queryWrapper,queryForm);
        return subordinateAccountMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<SubordinateAccount> getQueryWrapper(QueryWrapper<SubordinateAccount> queryWrapper, BaseQueryForm<SubordinateAccountQueryParam> queryForm){
        SubordinateAccountQueryParam subordinateAccount = queryForm.toParam(SubordinateAccountQueryParam.class);
        //条件拼接
        if(subordinateAccount.getUserId() != null){
            queryWrapper.like(SubordinateAccount.USER_ID,subordinateAccount.getUserId());
        }
        if(StringUtils.isNotBlank(subordinateAccount.getAccount())){
            queryWrapper.like(SubordinateAccount.ACCOUNT,subordinateAccount.getAccount());
        }
        if(StringUtils.isNotBlank(subordinateAccount.getSysCode())){
            queryWrapper.eq(SubordinateAccount.SYS_CODE,subordinateAccount.getSysCode());
        }
        if(StringUtils.isNotBlank(subordinateAccount.getRemark())){
            queryWrapper.like(SubordinateAccount.REMARK,subordinateAccount.getRemark());
        }
        if(StringUtils.isNotBlank(subordinateAccount.getReserve())){
            queryWrapper.like(SubordinateAccount.RESERVE,subordinateAccount.getReserve());
        }
        if(StringUtils.isNotBlank(subordinateAccount.getVersion())){
            queryWrapper.like(SubordinateAccount.VERSION,subordinateAccount.getVersion());
        }
        if(subordinateAccount.getCreateBy() != null){
            queryWrapper.eq(SubordinateAccount.CREATE_BY,subordinateAccount.getCreateBy());
        }
        if(subordinateAccount.getUpdateBy() != null){
            queryWrapper.eq(SubordinateAccount.UPDATE_BY,subordinateAccount.getUpdateBy());
        }
        return queryWrapper;
    }

}

