package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.BasicOrderMapQueryForm;
import com.deloitte.services.contract.entity.BasicOrderMap;
import com.deloitte.services.contract.mapper.BasicOrderMapMapper;
import com.deloitte.services.contract.service.IBasicOrderMapService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  BasicOrderMap服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BasicOrderMapServiceImpl extends ServiceImpl<BasicOrderMapMapper, BasicOrderMap> implements IBasicOrderMapService {


    @Autowired
    private BasicOrderMapMapper basicOrderMapMapper;

    @Override
    public IPage<BasicOrderMap> selectPage(BasicOrderMapQueryForm queryForm ) {
        QueryWrapper<BasicOrderMap> queryWrapper = new QueryWrapper <BasicOrderMap>();
        //getQueryWrapper(queryWrapper,queryForm);
        return basicOrderMapMapper.selectPage(new Page<BasicOrderMap>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<BasicOrderMap> selectList(BasicOrderMapQueryForm queryForm) {
        QueryWrapper<BasicOrderMap> queryWrapper = new QueryWrapper <BasicOrderMap>();
        //getQueryWrapper(queryWrapper,queryForm);
        return basicOrderMapMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<BasicOrderMap> getQueryWrapper(QueryWrapper<BasicOrderMap> queryWrapper,BasicOrderMapQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getContractId())){
            queryWrapper.eq(BasicOrderMap.CONTRACT_ID,queryForm.getContractId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrderId())){
            queryWrapper.eq(BasicOrderMap.ORDER_ID,queryForm.getOrderId());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(BasicOrderMap.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(BasicOrderMap.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(BasicOrderMap.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(BasicOrderMap.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(BasicOrderMap.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(BasicOrderMap.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(BasicOrderMap.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(BasicOrderMap.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(BasicOrderMap.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(BasicOrderMap.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        return queryWrapper;
    }
     */
}

