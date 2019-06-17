package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.BasicSubjectMapQueryForm;
import com.deloitte.services.contract.entity.BasicSubjectMap;
import com.deloitte.services.contract.mapper.BasicSubjectMapMapper;
import com.deloitte.services.contract.service.IBasicSubjectMapService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  BasicSubjectMap服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BasicSubjectMapServiceImpl extends ServiceImpl<BasicSubjectMapMapper, BasicSubjectMap> implements IBasicSubjectMapService {


    @Autowired
    private BasicSubjectMapMapper basicSubjectMapMapper;

    @Override
    public IPage<BasicSubjectMap> selectPage(BasicSubjectMapQueryForm queryForm ) {
        QueryWrapper<BasicSubjectMap> queryWrapper = new QueryWrapper <BasicSubjectMap>();
        //getQueryWrapper(queryWrapper,queryForm);
        return basicSubjectMapMapper.selectPage(new Page<BasicSubjectMap>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<BasicSubjectMap> selectList(BasicSubjectMapQueryForm queryForm) {
        QueryWrapper<BasicSubjectMap> queryWrapper = new QueryWrapper <BasicSubjectMap>();
        //getQueryWrapper(queryWrapper,queryForm);
        return basicSubjectMapMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<BasicSubjectMap> getQueryWrapper(QueryWrapper<BasicSubjectMap> queryWrapper,BasicSubjectMapQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getContractId())){
            queryWrapper.eq(BasicSubjectMap.CONTRACT_ID,queryForm.getContractId());
        }
        if(StringUtils.isNotBlank(queryForm.getSubjectId())){
            queryWrapper.eq(BasicSubjectMap.SUBJECT_ID,queryForm.getSubjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getType())){
            queryWrapper.eq(BasicSubjectMap.TYPE,queryForm.getType());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(BasicSubjectMap.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(BasicSubjectMap.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(BasicSubjectMap.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(BasicSubjectMap.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(BasicSubjectMap.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(BasicSubjectMap.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(BasicSubjectMap.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(BasicSubjectMap.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(BasicSubjectMap.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(BasicSubjectMap.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        return queryWrapper;
    }
     */
}

