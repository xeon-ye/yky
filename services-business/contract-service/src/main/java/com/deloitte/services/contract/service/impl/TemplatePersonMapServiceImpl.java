package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.StandardTemplateQueryForm;
import com.deloitte.platform.api.contract.param.TemplatePersonMapQueryForm;
import com.deloitte.platform.api.contract.vo.TemplatePersonMapForm;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.contract.entity.StandardTemplate;
import com.deloitte.services.contract.entity.TemplatePersonMap;
import com.deloitte.services.contract.mapper.StandardTemplateMapper;
import com.deloitte.services.contract.mapper.TemplatePersonMapMapper;
import com.deloitte.services.contract.service.ICommonService;
import com.deloitte.services.contract.service.ITemplatePersonMapService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  TemplatePersonMap服务实现类
 * @Modified :
 */
@Service
@Transactional
public class TemplatePersonMapServiceImpl extends ServiceImpl<TemplatePersonMapMapper, TemplatePersonMap> implements ITemplatePersonMapService {


    @Autowired
    private TemplatePersonMapMapper templatePersonMapMapper;
    @Autowired
    private StandardTemplateMapper standardTemplateMapper;
    @Autowired
    public ICommonService commonService;

    @Override
    public IPage<TemplatePersonMap> selectPage(TemplatePersonMapQueryForm queryForm ) {
        QueryWrapper<TemplatePersonMap> queryWrapper = new QueryWrapper <TemplatePersonMap>();
        //getQueryWrapper(queryWrapper,queryForm);
        return templatePersonMapMapper.selectPage(new Page<TemplatePersonMap>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<TemplatePersonMap> selectList(TemplatePersonMapQueryForm queryForm) {
        QueryWrapper<TemplatePersonMap> queryWrapper = new QueryWrapper <TemplatePersonMap>();
        //getQueryWrapper(queryWrapper,queryForm);
        return templatePersonMapMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<TemplatePersonMap> getQueryWrapper(QueryWrapper<TemplatePersonMap> queryWrapper,TemplatePersonMapQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getTemplateCode())){
            queryWrapper.eq(TemplatePersonMap.TEMPLATE_CODE,queryForm.getTemplateCode());
        }
        if(StringUtils.isNotBlank(queryForm.getPersonCode())){
            queryWrapper.eq(TemplatePersonMap.PERSON_CODE,queryForm.getPersonCode());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(TemplatePersonMap.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(TemplatePersonMap.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(TemplatePersonMap.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(TemplatePersonMap.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(TemplatePersonMap.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(TemplatePersonMap.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(TemplatePersonMap.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(TemplatePersonMap.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(TemplatePersonMap.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(TemplatePersonMap.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        return queryWrapper;
    }
     */

    /**
     * 查询分页
     * @param standardTemplateQueryForm
     * @return
     */
    public List<StandardTemplate> getTemplatePersonList(StandardTemplateQueryForm standardTemplateQueryForm){
        UserVo userVo = commonService.getCurrentUser();
        standardTemplateQueryForm.setCreateBy(userVo.getId());
        int page = standardTemplateQueryForm.getCurrentPage();
        int size = standardTemplateQueryForm.getPageSize();
        int minPage = (page - 1) * size + 1;
        int maxPage = page * size;
        standardTemplateQueryForm.setMaxNum(String.valueOf(maxPage));
        standardTemplateQueryForm.setMinNum(String.valueOf(minPage));
        String orgCode = standardTemplateQueryForm.getOrgCode();
        if (orgCode != null && !orgCode.equals("")){
            String[] orgCodes = orgCode.split(",");
            List<String> orgList = new ArrayList<>();
            for (String org: orgCodes) {
                orgList.add(org);
            }
            standardTemplateQueryForm.setOrgCodeList(orgList);
        }
        String contractTypeCode = standardTemplateQueryForm.getContractTypeCode();
        if (contractTypeCode != null && !contractTypeCode.equals("")){
            String[] contractTypeCodes = contractTypeCode.split(",");
            List<String> contractTypeList = new ArrayList<>();
            for (String contractType: contractTypeCodes) {
                contractTypeList.add(contractType);
            }
            standardTemplateQueryForm.setContractTypeList(contractTypeList);
        }
        return standardTemplateMapper.getTemplatePersonList(standardTemplateQueryForm);
    }

    public BigDecimal getTemplatePersonMxaSize(StandardTemplateQueryForm standardTemplateQueryForm){
        UserVo userVo = commonService.getCurrentUser();
        standardTemplateQueryForm.setCreateBy(userVo.getId());
        String orgCode = standardTemplateQueryForm.getOrgCode();
        if (orgCode != null && !orgCode.equals("")){
            String[] orgCodes = orgCode.split(",");
            List<String> orgList = new ArrayList<>();
            for (String org: orgCodes) {
                orgList.add(org);
            }
            standardTemplateQueryForm.setOrgCodeList(orgList);
        }
        String contractTypeCode = standardTemplateQueryForm.getContractTypeCode();
        if (contractTypeCode != null && !contractTypeCode.equals("")){
            String[] contractTypeCodes = contractTypeCode.split(",");
            List<String> contractTypeList = new ArrayList<>();
            for (String contractType: contractTypeCodes) {
                contractTypeList.add(contractType);
            }
            standardTemplateQueryForm.setContractTypeList(contractTypeList);
        }
        BigDecimal num = standardTemplateMapper.getTemplatePersonMxaSize(standardTemplateQueryForm);
        return num;
    }

    public Result addMyStandard(TemplatePersonMapForm templatePersonMapForm){
        TemplatePersonMap templatePersonMap = new BeanUtils<TemplatePersonMap>().copyObj(templatePersonMapForm,TemplatePersonMap.class);
        QueryWrapper<TemplatePersonMap> queryWrapper = new QueryWrapper();
        queryWrapper.eq(TemplatePersonMap.TEMPLATE_CODE, templatePersonMap.getTemplateCode());
        queryWrapper.eq(TemplatePersonMap.PERSON_CODE, templatePersonMap.getPersonCode());
        List list = templatePersonMapMapper.selectList(queryWrapper);
        if (list != null && list.size() > 0){
            return Result.fail("请勿重复添加");
        }else{
            templatePersonMapMapper.insert(templatePersonMap);
            return Result.success();
        }
    }
}

