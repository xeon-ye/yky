package com.deloitte.services.isump.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.isump.param.ProDeputyAccountQueryForm;
import com.deloitte.services.isump.entity.ProDeputyAccount;
import com.deloitte.services.isump.mapper.ProDeputyAccountMapper;
import com.deloitte.services.isump.service.IProDeputyAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jianglong
 * @Date : Create in 2019-03-22
 * @Description :  ProDeputyAccount服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ProDeputyAccountServiceImpl extends ServiceImpl<ProDeputyAccountMapper, ProDeputyAccount> implements IProDeputyAccountService {


    @Autowired
    private ProDeputyAccountMapper proDeputyAccountMapper;

    @Override
    public IPage<ProDeputyAccount> selectPage(ProDeputyAccountQueryForm queryForm ) {
        QueryWrapper<ProDeputyAccount> queryWrapper = new QueryWrapper <ProDeputyAccount>();
        //getQueryWrapper(queryWrapper,queryForm);
        return proDeputyAccountMapper.selectPage(new Page<ProDeputyAccount>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ProDeputyAccount> selectList(ProDeputyAccountQueryForm queryForm) {
        QueryWrapper<ProDeputyAccount> queryWrapper = new QueryWrapper <ProDeputyAccount>();
        //getQueryWrapper(queryWrapper,queryForm);
        return proDeputyAccountMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ProDeputyAccount> getQueryWrapper(QueryWrapper<ProDeputyAccount> queryWrapper,ProDeputyAccountQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getDeputyAccountId())){
            queryWrapper.eq(ProDeputyAccount.DEPUTY_ACCOUNT_ID,queryForm.getDeputyAccountId());
        }
        if(StringUtils.isNotBlank(queryForm.getProCategoryId())){
            queryWrapper.eq(ProDeputyAccount.PRO_CATEGORY_ID,queryForm.getProCategoryId());
        }
        return queryWrapper;
    }
     */
}

