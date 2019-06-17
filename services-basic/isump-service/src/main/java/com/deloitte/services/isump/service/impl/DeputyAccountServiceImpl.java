package com.deloitte.services.isump.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.isump.param.DeputyAccountQueryForm;
import com.deloitte.platform.api.isump.param.DeputyAccountQueryParam;
import com.deloitte.platform.common.core.entity.form.BaseQueryForm;
import com.deloitte.services.isump.entity.DeputyAccount;
import com.deloitte.services.isump.mapper.DeputyAccountMapper;
import com.deloitte.services.isump.service.IDeputyAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  DeputyAccount服务实现类
 * @Modified :
 */
@Service
@Transactional
public class DeputyAccountServiceImpl extends ServiceImpl<DeputyAccountMapper, DeputyAccount> implements IDeputyAccountService {


    @Autowired
    private DeputyAccountMapper deputyAccountMapper;

    @Override
    public IPage<DeputyAccount> selectPage(DeputyAccountQueryForm queryForm ) {
        QueryWrapper<DeputyAccount> queryWrapper = new QueryWrapper <DeputyAccount>();
        getQueryWrapper(queryWrapper,queryForm);
        return deputyAccountMapper.selectPage(new Page<DeputyAccount>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<DeputyAccount> selectList(DeputyAccountQueryForm queryForm) {
        QueryWrapper<DeputyAccount> queryWrapper = new QueryWrapper <DeputyAccount>();
        getQueryWrapper(queryWrapper,queryForm);
        return deputyAccountMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<DeputyAccount> getQueryWrapper(QueryWrapper<DeputyAccount> queryWrapper, BaseQueryForm<DeputyAccountQueryParam> queryForm){
        //条件拼接
        DeputyAccountQueryParam deputyAccount = queryForm.toParam(DeputyAccountQueryParam.class);
        if(deputyAccount.getUserId() != null){
            queryWrapper.eq(DeputyAccount.USER_ID,deputyAccount.getUserId());
        }
        if(deputyAccount.getOrgId() != null){
            queryWrapper.eq(DeputyAccount.ORG_ID,deputyAccount.getOrgId());
        }
        if(StringUtils.isNotBlank(deputyAccount.getName())){
            queryWrapper.like(DeputyAccount.NAME,deputyAccount.getName());
        }
//        if(StringUtils.isNotBlank(deputyAccount.getSort())){
//            queryWrapper.like(DeputyAccount.SORT,deputyAccount.getSort());
//        }
//        if(StringUtils.isNotBlank(deputyAccount.getOrgSort())){
//            queryWrapper.like(DeputyAccount.ORG_SORT,deputyAccount.getOrgSort());
//        }
        if(StringUtils.isNotBlank(deputyAccount.getState())){
            queryWrapper.eq(DeputyAccount.STATE,deputyAccount.getState());
        }
        if(deputyAccount.getOpenTime() != null){
            queryWrapper.ge(DeputyAccount.OPEN_TIME,deputyAccount.getOpenTime());
        }
        if(deputyAccount.getCloseTime() != null){
            queryWrapper.le(DeputyAccount.CLOSE_TIME,deputyAccount.getCloseTime());
        }
        if(StringUtils.isNotBlank(deputyAccount.getRemark())){
            queryWrapper.like(DeputyAccount.REMARK,deputyAccount.getRemark());
        }
        if(StringUtils.isNotBlank(deputyAccount.getReserve())){
            queryWrapper.eq(DeputyAccount.RESERVE,deputyAccount.getReserve());
        }
        if(StringUtils.isNotBlank(deputyAccount.getVersion())){
            queryWrapper.eq(DeputyAccount.VERSION,deputyAccount.getVersion());
        }
        if(deputyAccount.getCreateBy() != null){
            queryWrapper.eq(DeputyAccount.CREATE_BY,deputyAccount.getCreateBy());
        }
        if(deputyAccount.getUpdateBy() != null){
            queryWrapper.eq(DeputyAccount.UPDATE_BY,deputyAccount.getUpdateBy());
        }
        queryWrapper.orderByAsc(DeputyAccount.ORG_SORT,DeputyAccount.SORT);
        return queryWrapper;
    }
}

