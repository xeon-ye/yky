package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.AttamentInfoQueryForm;
import com.deloitte.services.contract.entity.AttamentInfo;
import com.deloitte.services.contract.mapper.AttamentInfoMapper;
import com.deloitte.services.contract.service.IAttamentInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  AttamentInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class AttamentInfoServiceImpl extends ServiceImpl<AttamentInfoMapper, AttamentInfo> implements IAttamentInfoService {


    @Autowired
    private AttamentInfoMapper attamentInfoMapper;

    @Override
    public IPage<AttamentInfo> selectPage(AttamentInfoQueryForm queryForm ) {
        QueryWrapper<AttamentInfo> queryWrapper = new QueryWrapper <AttamentInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return attamentInfoMapper.selectPage(new Page<AttamentInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<AttamentInfo> selectList(AttamentInfoQueryForm queryForm) {
        QueryWrapper<AttamentInfo> queryWrapper = new QueryWrapper <AttamentInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return attamentInfoMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<AttamentInfo> getQueryWrapper(QueryWrapper<AttamentInfo> queryWrapper,AttamentInfoQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getAttamentTypeCode())){
            queryWrapper.eq(AttamentInfo.ATTAMENT_TYPE_CODE,queryForm.getAttamentTypeCode());
        }
        if(StringUtils.isNotBlank(queryForm.getAttamentType())){
            queryWrapper.eq(AttamentInfo.ATTAMENT_TYPE,queryForm.getAttamentType());
        }
        if(StringUtils.isNotBlank(queryForm.getAttamentRenanem())){
            queryWrapper.eq(AttamentInfo.ATTAMENT_RENANEM,queryForm.getAttamentRenanem());
        }
        if(StringUtils.isNotBlank(queryForm.getAttamentFullName())){
            queryWrapper.eq(AttamentInfo.ATTAMENT_FULL_NAME,queryForm.getAttamentFullName());
        }
        if(StringUtils.isNotBlank(queryForm.getPath())){
            queryWrapper.eq(AttamentInfo.PATH,queryForm.getPath());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(AttamentInfo.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(AttamentInfo.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(AttamentInfo.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(AttamentInfo.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(AttamentInfo.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(AttamentInfo.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(AttamentInfo.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(AttamentInfo.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        return queryWrapper;
    }
     */
}

