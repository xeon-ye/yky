package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.StandardTemplateQueryForm;
import com.deloitte.services.contract.entity.StandardTemplate;
import com.deloitte.services.contract.entity.SysSignSubjectInfo;
import com.deloitte.services.contract.mapper.StandardTemplateMapper;
import com.deloitte.services.contract.service.IStandardTemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  StandardTemplate服务实现类
 * @Modified :
 */
@Service
@Transactional
public class StandardTemplateServiceImpl extends ServiceImpl<StandardTemplateMapper, StandardTemplate> implements IStandardTemplateService {


    @Autowired
    private StandardTemplateMapper standardTemplateMapper;

    @Override
    public IPage<StandardTemplate> selectPage(StandardTemplateQueryForm queryForm ) {
        QueryWrapper<StandardTemplate> queryWrapper = new QueryWrapper <StandardTemplate>();
        getQueryWrapper(queryWrapper,queryForm);
//        if(StringUtils.isNotBlank(queryForm.getAttributeCode())){
//            queryWrapper.eq(StandardTemplate.ATTRIBUTE_CODE,queryForm.getAttributeCode());
//        }
        return standardTemplateMapper.selectPage(new Page<StandardTemplate>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);
    }

    @Override
    public List<StandardTemplate> selectList(StandardTemplateQueryForm queryForm) {
        QueryWrapper<StandardTemplate> queryWrapper = new QueryWrapper <StandardTemplate>();
        getQueryWrapper(queryWrapper,queryForm);
        return standardTemplateMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<StandardTemplate> getQueryWrapper(QueryWrapper<StandardTemplate> queryWrapper,StandardTemplateQueryForm queryForm){
        //条件拼接
       if(StringUtils.isNotBlank(queryForm.getTemplateCode())){
            queryWrapper.like(StandardTemplate.TEMPLATE_CODE,queryForm.getTemplateCode());
        }
        if(StringUtils.isNotBlank(queryForm.getTemplateName())){
            queryWrapper.like(StandardTemplate.TEMPLATE_NAME,queryForm.getTemplateName());
        }
        if(StringUtils.isNotBlank(queryForm.getAttributeCode())){
            queryWrapper.eq(StandardTemplate.ATTRIBUTE_CODE,queryForm.getAttributeCode());
        }
       /* if(StringUtils.isNotBlank(queryForm.getAttribute())){
            queryWrapper.eq(StandardTemplate.ATTRIBUTE,queryForm.getAttribute());
        }*/
        if(StringUtils.isNotBlank(queryForm.getOrgCode())){
            String orgCode = queryForm.getOrgCode();
            String[] orgCodes = orgCode.split(",");
            for (int i = 0; i < orgCodes.length; i++){
                queryWrapper.like(StandardTemplate.ORG_CODE, orgCodes[i]);
            }
        }
        /* if(StringUtils.isNotBlank(queryForm.getOrg())){
            queryWrapper.eq(StandardTemplate.ORG,queryForm.getOrg());
        }*/
        if(StringUtils.isNotBlank(queryForm.getContractTypeCode())){
            String contractTypeCode = queryForm.getContractTypeCode();
            String[] contractTypeCodes = contractTypeCode.split(",");
            for (int i = 0; i < contractTypeCodes.length; i++){
                queryWrapper.like(StandardTemplate.CONTRACT_TYPE_CODE, contractTypeCodes[i]);
            }
        }
        if(StringUtils.isNotBlank(queryForm.getStatue())){
            queryWrapper.eq(StandardTemplate.STATUE, queryForm.getStatue());
        }
       /* if(StringUtils.isNotBlank(queryForm.getContractType())){
            queryWrapper.eq(StandardTemplate.CONTRACT_TYPE,queryForm.getContractType());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(StandardTemplate.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(StandardTemplate.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(StandardTemplate.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(StandardTemplate.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(StandardTemplate.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(StandardTemplate.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(StandardTemplate.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(StandardTemplate.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(StandardTemplate.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(StandardTemplate.SPARE_FIELD_5,queryForm.getSpareField5());
        }*/
        return queryWrapper;
    }


    /**
     * 查询分页
     * @param map
     * @return
     */
    public List<StandardTemplate> getStandardTemplateList(Map<String, String> map){
        return standardTemplateMapper.getStandardTemplateList(map);
    }

    public List<StandardTemplate> getStandardTemplateAllList(Map<String, String> map){
        return standardTemplateMapper.getStandardTemplateaAllList(map);
    }

    public int getStandardTemplateMxaSize(Map<String, String> map){
        List<Map<String, Object>> list = standardTemplateMapper.getStandardTemplateMxaSize(map);
        int i = (Integer) list.get(0).get("LIST_NUMB");
        return i;
    }
}

