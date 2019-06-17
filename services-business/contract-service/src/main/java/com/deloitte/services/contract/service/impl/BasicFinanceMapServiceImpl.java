package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.BasicFinanceMapQueryForm;
import com.deloitte.services.contract.entity.BasicFinanceMap;
import com.deloitte.services.contract.mapper.BasicFinanceMapMapper;
import com.deloitte.services.contract.service.IBasicFinanceMapService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  BasicFinanceMap服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BasicFinanceMapServiceImpl extends ServiceImpl<BasicFinanceMapMapper, BasicFinanceMap> implements IBasicFinanceMapService {


    @Autowired
    private BasicFinanceMapMapper basicFinanceMapMapper;

    @Override
    public IPage<BasicFinanceMap> selectPage(BasicFinanceMapQueryForm queryForm ) {
        QueryWrapper<BasicFinanceMap> queryWrapper = new QueryWrapper <BasicFinanceMap>();
        //getQueryWrapper(queryWrapper,queryForm);
        return basicFinanceMapMapper.selectPage(new Page<BasicFinanceMap>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<BasicFinanceMap> selectList(BasicFinanceMapQueryForm queryForm) {
        QueryWrapper<BasicFinanceMap> queryWrapper = new QueryWrapper <BasicFinanceMap>();
        //getQueryWrapper(queryWrapper,queryForm);
        return basicFinanceMapMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<BasicFinanceMap> getQueryWrapper(QueryWrapper<BasicFinanceMap> queryWrapper,BasicFinanceMapQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getContractId())){
            queryWrapper.eq(BasicFinanceMap.CONTRACT_ID,queryForm.getContractId());
        }
        if(StringUtils.isNotBlank(queryForm.getFinanceId())){
            queryWrapper.eq(BasicFinanceMap.FINANCE_ID,queryForm.getFinanceId());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(BasicFinanceMap.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(BasicFinanceMap.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(BasicFinanceMap.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(BasicFinanceMap.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(BasicFinanceMap.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(BasicFinanceMap.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(BasicFinanceMap.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(BasicFinanceMap.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(BasicFinanceMap.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(BasicFinanceMap.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        return queryWrapper;
    }
     */
}

