package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.SetupInfoQueryForm;
import com.deloitte.services.contract.entity.SetupInfo;
import com.deloitte.services.contract.mapper.SetupInfoMapper;
import com.deloitte.services.contract.service.ISetupInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : hemingzheng
 * @Date : Create in 2019-04-13
 * @Description :  SetupInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SetupInfoServiceImpl extends ServiceImpl<SetupInfoMapper, SetupInfo> implements ISetupInfoService {


    @Autowired
    private SetupInfoMapper setupInfoMapper;

    @Override
    public IPage<SetupInfo> selectPage(SetupInfoQueryForm queryForm ) {
        QueryWrapper<SetupInfo> queryWrapper = new QueryWrapper <SetupInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return setupInfoMapper.selectPage(new Page<SetupInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<SetupInfo> selectList(SetupInfoQueryForm queryForm) {
        QueryWrapper<SetupInfo> queryWrapper = new QueryWrapper <SetupInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return setupInfoMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<SetupInfo> getQueryWrapper(QueryWrapper<SetupInfo> queryWrapper,SetupInfoQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getContractId())){
            queryWrapper.eq(SetupInfo.CONTRACT_ID,queryForm.getContractId());
        }
        if(StringUtils.isNotBlank(queryForm.getContractName())){
            queryWrapper.eq(SetupInfo.CONTRACT_NAME,queryForm.getContractName());
        }
        if(StringUtils.isNotBlank(queryForm.getSetupResult())){
            queryWrapper.eq(SetupInfo.SETUP_RESULT,queryForm.getSetupResult());
        }
        if(StringUtils.isNotBlank(queryForm.getSetupTime())){
            queryWrapper.eq(SetupInfo.SETUP_TIME,queryForm.getSetupTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(SetupInfo.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(SetupInfo.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(SetupInfo.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(SetupInfo.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(SetupInfo.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(SetupInfo.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(SetupInfo.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(SetupInfo.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(SetupInfo.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        return queryWrapper;
    }
     */
}

