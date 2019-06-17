package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.BasicAttamentMapQueryForm;
import com.deloitte.platform.api.contract.vo.BasicAttamentMapForm;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.contract.entity.BasicAttamentMap;
import com.deloitte.services.contract.mapper.BasicAttamentMapMapper;
import com.deloitte.services.contract.service.IBasicAttamentMapService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-29
 * @Description :  BasicAttamentMap服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BasicAttamentMapServiceImpl extends ServiceImpl<BasicAttamentMapMapper, BasicAttamentMap> implements IBasicAttamentMapService {


    @Autowired
    private BasicAttamentMapMapper basicAttamentMapMapper;


    @Override
    public IPage<BasicAttamentMap> selectPage(BasicAttamentMapQueryForm queryForm ) {
        QueryWrapper<BasicAttamentMap> queryWrapper = new QueryWrapper <BasicAttamentMap>();
        //getQueryWrapper(queryWrapper,queryForm);
        return basicAttamentMapMapper.selectPage(new Page<BasicAttamentMap>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<BasicAttamentMap> selectList(BasicAttamentMapQueryForm queryForm) {
        QueryWrapper<BasicAttamentMap> queryWrapper = new QueryWrapper <BasicAttamentMap>();
        //getQueryWrapper(queryWrapper,queryForm);
        return basicAttamentMapMapper.selectList(queryWrapper);
    }

    @Override
    public BasicAttamentMap addBasicAttamentMap(BasicAttamentMapForm basicAttamentMapForm) {
        BasicAttamentMap basicAttamentMap=new BeanUtils<BasicAttamentMap>().copyObj(basicAttamentMapForm,BasicAttamentMap.class);
        basicAttamentMap.setFileType("FIL5000");
        save(basicAttamentMap);
        return null;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<BasicAttamentMap> getQueryWrapper(QueryWrapper<BasicAttamentMap> queryWrapper,BasicAttamentMapQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getContractId())){
            queryWrapper.eq(BasicAttamentMap.CONTRACT_ID,queryForm.getContractId());
        }
        if(StringUtils.isNotBlank(queryForm.getAttamentId())){
            queryWrapper.eq(BasicAttamentMap.ATTAMENT_ID,queryForm.getAttamentId());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(BasicAttamentMap.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(BasicAttamentMap.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(BasicAttamentMap.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(BasicAttamentMap.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(BasicAttamentMap.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(BasicAttamentMap.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(BasicAttamentMap.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(BasicAttamentMap.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(BasicAttamentMap.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(BasicAttamentMap.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        if(StringUtils.isNotBlank(queryForm.getFileName())){
            queryWrapper.eq(BasicAttamentMap.FILE_NAME,queryForm.getFileName());
        }
        if(StringUtils.isNotBlank(queryForm.getFileUrl())){
            queryWrapper.eq(BasicAttamentMap.FILE_URL,queryForm.getFileUrl());
        }
        if(StringUtils.isNotBlank(queryForm.getFileSize())){
            queryWrapper.eq(BasicAttamentMap.FILE_SIZE,queryForm.getFileSize());
        }
        if(StringUtils.isNotBlank(queryForm.getUploadTime())){
            queryWrapper.eq(BasicAttamentMap.UPLOAD_TIME,queryForm.getUploadTime());
        }
        if(StringUtils.isNotBlank(queryForm.getFileExt())){
            queryWrapper.eq(BasicAttamentMap.FILE_EXT,queryForm.getFileExt());
        }
        if(StringUtils.isNotBlank(queryForm.getFileType())){
            queryWrapper.eq(BasicAttamentMap.FILE_TYPE,queryForm.getFileType());
        }
        return queryWrapper;
    }
     */
}

