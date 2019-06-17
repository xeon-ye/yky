package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.SysContractNoQueryForm;
import com.deloitte.services.contract.common.enums.ContractErrorType;
import com.deloitte.services.contract.common.util.AssertUtils;
import com.deloitte.services.contract.entity.BasicInfo;
import com.deloitte.services.contract.entity.SysContractNo;
import com.deloitte.services.contract.mapper.BasicInfoMapper;
import com.deloitte.services.contract.mapper.SysContractNoMapper;
import com.deloitte.services.contract.service.ISysContractNoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  SysContractNo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SysContractNoServiceImpl extends ServiceImpl<SysContractNoMapper, SysContractNo> implements ISysContractNoService {


    @Autowired
    private SysContractNoMapper sysContractNoMapper;
    @Autowired
    private BasicInfoMapper basicInfoMapper;

    @Override
    public IPage<SysContractNo> selectPage(SysContractNoQueryForm queryForm ) {
        QueryWrapper<SysContractNo> queryWrapper = new QueryWrapper <SysContractNo>();
        getQueryWrapper(queryWrapper,queryForm);
        return sysContractNoMapper.selectPage(new Page<SysContractNo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<SysContractNo> selectList(SysContractNoQueryForm queryForm) {
        QueryWrapper<SysContractNo> queryWrapper = new QueryWrapper <SysContractNo>();
        //getQueryWrapper(queryWrapper,queryForm);
        if(StringUtils.isNotBlank(queryForm.getCompanyCode())){
            queryWrapper.eq(SysContractNo.COMPANY_CODE,queryForm.getCompanyCode());
        }
        if(StringUtils.isNotBlank(queryForm.getDepartmentCode())){
            queryWrapper.eq(SysContractNo.DEPARTMENT_CODE,queryForm.getDepartmentCode());
        }
        if(StringUtils.isNotBlank(queryForm.getContractYear())){
            queryWrapper.eq(SysContractNo.CONTRACT_YEAR,queryForm.getContractYear());
        }
        return sysContractNoMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     * */
    public QueryWrapper<SysContractNo> getQueryWrapper(QueryWrapper<SysContractNo> queryWrapper,SysContractNoQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCompanyCode())){
            queryWrapper.eq(SysContractNo.COMPANY_CODE,queryForm.getCompanyCode());
        }
        if(StringUtils.isNotBlank(queryForm.getCompanyName())){
            queryWrapper.like(SysContractNo.COMPANY_NAME,queryForm.getCompanyName());
        }
        if(StringUtils.isNotBlank(queryForm.getCompanyShort())){
            queryWrapper.eq(SysContractNo.COMPANY_SHORT,queryForm.getCompanyShort());
        }
        if(StringUtils.isNotBlank(queryForm.getDepartmentCode())){
            queryWrapper.eq(SysContractNo.DEPARTMENT_CODE,queryForm.getDepartmentCode());
        }
        if(StringUtils.isNotBlank(queryForm.getDepartmentName())){
            queryWrapper.like(SysContractNo.DEPARTMENT_NAME,queryForm.getDepartmentName());
        }
        if(StringUtils.isNotBlank(queryForm.getDepartmentShort())){
            queryWrapper.eq(SysContractNo.DEPARTMENT_SHORT,queryForm.getDepartmentShort());
        }
        if(StringUtils.isNotBlank(queryForm.getSerialNumber())){
            queryWrapper.eq(SysContractNo.SERIAL_NUMBER,queryForm.getSerialNumber());
        }
        if(StringUtils.isNotBlank(queryForm.getContractYear())){
            queryWrapper.eq(SysContractNo.CONTRACT_YEAR,queryForm.getContractYear());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(SysContractNo.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(SysContractNo.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(SysContractNo.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(SysContractNo.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(SysContractNo.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(SysContractNo.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        return queryWrapper;
    }

    public String getContractNo(SysContractNoQueryForm sysContractNoQueryForm){
        String contractNo = "";
        List<SysContractNo> list = selectList(sysContractNoQueryForm);
        SysContractNo sysContractNo = null;
        AssertUtils.asserts(null == list ||  list.size() == 0 , ContractErrorType.CONTRACTNO_SETTING_NULL);

        sysContractNo = list.get(0);
        int serialNo = Integer.parseInt(sysContractNo.getSerialNumber());
        int size = sysContractNo.getSerialSize().length();
        // 组合合同编号，部门简称 + [年份] + 3位的流水号
        contractNo = sysContractNo.getDepartmentShort() + "[" + sysContractNo.getContractYear() + "]" + String.format("%0"+size+"d", serialNo);
        String newNo = String.valueOf(serialNo + 1);
        sysContractNo.setSerialNumber(newNo);
        int isUpdate = sysContractNoMapper.updateById(sysContractNo);

        AssertUtils.asserts(isUpdate<= 0, ContractErrorType.CONTRACTNO_SAVE_FAIL);
        return contractNo;
    }

    public String getContractNoForChange(BasicInfo basicInfo, SysContractNoQueryForm sysContractNoQueryFormr){
        String contractNo = "";
        String relationCode = basicInfo.getRelationCode();
        if (relationCode != null && (relationCode.equals("REL2000") || relationCode.equals("REL4000") || relationCode.equals("REL5000"))){
            String oldId = basicInfo.getOldContractId();
            BasicInfo basicInfoOld = basicInfoMapper.selectById(oldId);
            String contractNoOld = basicInfoOld.getContractNo();
            String[] contractNoOlds = contractNoOld.split("-");
            if (contractNoOlds.length >= 2){
                contractNo = contractNoOlds[0] + "-" + (Integer.parseInt(contractNoOlds[1]) + 1);
            }else{
                contractNo = contractNoOlds[0] + "-1";
            }
        }else{
            contractNo = getContractNo(sysContractNoQueryFormr);
        }
        return contractNo;
    }
}

