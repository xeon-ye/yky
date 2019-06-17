package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.BasicMonitorMapQueryForm;
import com.deloitte.services.contract.entity.BasicMonitorMap;
import com.deloitte.services.contract.mapper.BasicMonitorMapMapper;
import com.deloitte.services.contract.service.IBasicMonitorMapService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  BasicMonitorMap服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BasicMonitorMapServiceImpl extends ServiceImpl<BasicMonitorMapMapper, BasicMonitorMap> implements IBasicMonitorMapService {


    @Autowired
    private BasicMonitorMapMapper basicMonitorMapMapper;

    @Override
    public IPage<BasicMonitorMap> selectPage(BasicMonitorMapQueryForm queryForm ) {
        QueryWrapper<BasicMonitorMap> queryWrapper = new QueryWrapper <BasicMonitorMap>();
        //getQueryWrapper(queryWrapper,queryForm);
        return basicMonitorMapMapper.selectPage(new Page<BasicMonitorMap>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<BasicMonitorMap> selectList(BasicMonitorMapQueryForm queryForm) {
        QueryWrapper<BasicMonitorMap> queryWrapper = new QueryWrapper <BasicMonitorMap>();
        //getQueryWrapper(queryWrapper,queryForm);
        return basicMonitorMapMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<BasicMonitorMap> getQueryWrapper(QueryWrapper<BasicMonitorMap> queryWrapper,BasicMonitorMapQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getContractId())){
            queryWrapper.eq(BasicMonitorMap.CONTRACT_ID,queryForm.getContractId());
        }
        if(StringUtils.isNotBlank(queryForm.getMonitorId())){
            queryWrapper.eq(BasicMonitorMap.MONITOR_ID,queryForm.getMonitorId());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(BasicMonitorMap.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(BasicMonitorMap.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(BasicMonitorMap.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(BasicMonitorMap.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(BasicMonitorMap.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(BasicMonitorMap.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(BasicMonitorMap.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(BasicMonitorMap.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(BasicMonitorMap.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(BasicMonitorMap.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        return queryWrapper;
    }
     */
}

