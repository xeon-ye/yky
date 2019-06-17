package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.SysSignSubjectInfoQueryForm;
import com.deloitte.platform.api.contract.vo.SysSignSubjectInfoForm;
import com.deloitte.platform.api.contract.vo.SysSignSubjectInfoVo;
import com.deloitte.platform.api.isump.DeptClient;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.param.DeptQueryForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.contract.entity.SysSignSubjectInfo;
import com.deloitte.services.contract.mapper.SysSignSubjectInfoMapper;
import com.deloitte.services.contract.service.ISysSignSubjectInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  SysSignSubjectInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SysSignSubjectInfoServiceImpl extends ServiceImpl<SysSignSubjectInfoMapper, SysSignSubjectInfo> implements ISysSignSubjectInfoService {


    @Autowired
    private SysSignSubjectInfoMapper sysSignSubjectInfoMapper;
    @Autowired
    private DeptFeignService deptClient;

    @Override
    public IPage<SysSignSubjectInfo> selectPage(SysSignSubjectInfoQueryForm queryForm ) {
        QueryWrapper<SysSignSubjectInfo> queryWrapper = new QueryWrapper <SysSignSubjectInfo>();
        queryWrapper.like(SysSignSubjectInfo.STATUE, "1");
        if(StringUtils.isNotBlank(queryForm.getSubjectCode())){
            queryWrapper.eq(SysSignSubjectInfo.SUBJECT_CODE,queryForm.getSubjectCode());
        }
        if(StringUtils.isNotBlank(queryForm.getSubjectName())){
            queryWrapper.like(SysSignSubjectInfo.SUBJECT_NAME,queryForm.getSubjectName());
        }
        if(StringUtils.isNotBlank(queryForm.getTaxNum())){
            queryWrapper.like(SysSignSubjectInfo.TAX_NUM,queryForm.getTaxNum());
        }
        return sysSignSubjectInfoMapper.selectPage(new Page<SysSignSubjectInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);
    }

    @Override
    public List<SysSignSubjectInfo> selectList(SysSignSubjectInfoQueryForm queryForm) {
        QueryWrapper<SysSignSubjectInfo> queryWrapper = new QueryWrapper <SysSignSubjectInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return sysSignSubjectInfoMapper.selectList(queryWrapper);
    }
    public List<SysSignSubjectInfoForm> queryCopyInfo(String contractId)
    {
        return sysSignSubjectInfoMapper.queryCopyInfo(contractId);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<SysSignSubjectInfo> getQueryWrapper(QueryWrapper<SysSignSubjectInfo> queryWrapper,SysSignSubjectInfoQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getSubjectCode())){
            queryWrapper.eq(SysSignSubjectInfo.SUBJECT_CODE,queryForm.getSubjectCode());
        }
        if(StringUtils.isNotBlank(queryForm.getSubjectName())){
            queryWrapper.eq(SysSignSubjectInfo.SUBJECT_NAME,queryForm.getSubjectName());
        }
        if(StringUtils.isNotBlank(queryForm.getTaxNum())){
            queryWrapper.eq(SysSignSubjectInfo.TAX_NUM,queryForm.getTaxNum());
        }
        if(StringUtils.isNotBlank(queryForm.getTaxPayType())){
            queryWrapper.eq(SysSignSubjectInfo.TAX_PAY_TYPE,queryForm.getTaxPayType());
        }
        if(StringUtils.isNotBlank(queryForm.getSubjectAddress())){
            queryWrapper.eq(SysSignSubjectInfo.SUBJECT_ADDRESS,queryForm.getSubjectAddress());
        }
        if(StringUtils.isNotBlank(queryForm.getStatue())){
            queryWrapper.eq(SysSignSubjectInfo.STATUE,queryForm.getStatue());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(SysSignSubjectInfo.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(SysSignSubjectInfo.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(SysSignSubjectInfo.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(SysSignSubjectInfo.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(SysSignSubjectInfo.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(SysSignSubjectInfo.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(SysSignSubjectInfo.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(SysSignSubjectInfo.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(SysSignSubjectInfo.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(SysSignSubjectInfo.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        return queryWrapper;
    }
     */

    public SysSignSubjectInfo selectObjectById(Long subjectId){
        return sysSignSubjectInfoMapper.selectById(subjectId);
    }

    public IPage<SysSignSubjectInfoVo> selectByClient(SysSignSubjectInfoQueryForm sysSignSubjectInfoQueryForm){
        List<SysSignSubjectInfoVo> sysSignSubjectInfoVos = new ArrayList<>();
        //设置查询信息
        DeptQueryForm deptQueryForm = new DeptQueryForm();
        deptQueryForm.setCurrentPage(sysSignSubjectInfoQueryForm.getCurrentPage());
        deptQueryForm.setPageSize(sysSignSubjectInfoQueryForm.getPageSize());
        deptQueryForm.setDeptCode(sysSignSubjectInfoQueryForm.getSubjectCode());
        deptQueryForm.setDeptName(sysSignSubjectInfoQueryForm.getSubjectName());
        deptQueryForm.setTaxpayerNumber(sysSignSubjectInfoQueryForm.getTaxNum());
        deptQueryForm.setState("1");
        //获取我方/对方标记
        Integer groupType = sysSignSubjectInfoQueryForm.getSpareField1() != null && !sysSignSubjectInfoQueryForm.getSpareField1().equals("") ? Integer.parseInt(sysSignSubjectInfoQueryForm.getSpareField1()) : null;
        deptQueryForm.setGroupType(groupType);
        //获取接口查询出的page对象
        Result<GdcPage<DeptVo>> deptVoResult = deptClient.search2(deptQueryForm);
        if (deptVoResult.isSuccess()){
            GdcPage<DeptVo> deptVoPage = deptVoResult.getData();
            //获取page里的集合信息
            List<DeptVo> deptVos = deptVoPage.getContent();
            for (DeptVo deptVo: deptVos) {
                SysSignSubjectInfoVo sysSignSubjectInfoVo = new SysSignSubjectInfoVo();
                sysSignSubjectInfoVo.setId(deptVo.getDeptCode());
                sysSignSubjectInfoVo.setSubjectCode(deptVo.getDeptCode());
                sysSignSubjectInfoVo.setSubjectName(deptVo.getDeptName());
                sysSignSubjectInfoVo.setTaxNum(deptVo.getTaxpayerNumber());
                sysSignSubjectInfoVo.setIsUsed(deptVo.getState());
                sysSignSubjectInfoVo.setAccountBank(deptVo.getBankAccount());
                sysSignSubjectInfoVo.setAccountName(deptVo.getBankName());
                sysSignSubjectInfoVo.setBankName(deptVo.getBankName());
                sysSignSubjectInfoVo.setDeptLegalPersonName(deptVo.getDeptLegalPersonName());
                sysSignSubjectInfoVos.add(sysSignSubjectInfoVo);
            }
//        GdcPage<SysSignSubjectInfoVo> sysSignSubjectInfoVoPage = new GdcPage<>(sysSignSubjectInfoVos,deptVoPage.getTotal(),deptVoPage.getPageNo(),deptVoPage.getPageSize());
            // long current, long size, long total, boolean isSearchCount
            IPage<SysSignSubjectInfoVo> sysSignSubjectInfoVoPage = new Page<>(deptVoPage.getPageNo(), deptVoPage.getPageSize(), deptVoPage.getTotal(), true);
            sysSignSubjectInfoVoPage.setRecords(sysSignSubjectInfoVos);
            return sysSignSubjectInfoVoPage;
        }
        return null;
    }
}

