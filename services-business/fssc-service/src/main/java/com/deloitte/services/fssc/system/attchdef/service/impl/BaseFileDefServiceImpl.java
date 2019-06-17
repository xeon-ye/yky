package com.deloitte.services.fssc.system.attchdef.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.attchdef.param.BaseFileDefQueryForm;
import com.deloitte.services.fssc.system.attchdef.entity.BaseFileDef;
import com.deloitte.services.fssc.system.attchdef.mapper.BaseFileDefMapper;
import com.deloitte.services.fssc.system.attchdef.service.IBaseFileDefService;
import com.deloitte.services.fssc.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-04-09
 * @Description :  BaseFileDef服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BaseFileDefServiceImpl extends ServiceImpl<BaseFileDefMapper, BaseFileDef> implements IBaseFileDefService {


    @Autowired
    private BaseFileDefMapper baseFileDefMapper;

    @Override
    public IPage<BaseFileDef> selectPage(BaseFileDefQueryForm queryForm ) {
        QueryWrapper<BaseFileDef> queryWrapper = new QueryWrapper <BaseFileDef>();
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getDocumentName()),BaseFileDef.DOCUMENT_NAME,queryForm.getDocumentName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getMainTypeName()),BaseFileDef.MAIN_TYPE_NAME,queryForm.getMainTypeName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getInComeMainTypeName()),BaseFileDef.IN_COME_MAIN_TYPE_NAME,queryForm.getInComeMainTypeName());
        queryWrapper.like(StringUtil.isNotEmpty(queryForm.getIsValid()),BaseFileDef.IS_VALID,queryForm.getIsValid());
        queryWrapper.orderByDesc(BaseFileDef.UPDATE_TIME);
        return baseFileDefMapper.selectPage(new Page<BaseFileDef>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<BaseFileDef> selectList(BaseFileDefQueryForm queryForm) {
        QueryWrapper<BaseFileDef> queryWrapper = new QueryWrapper <BaseFileDef>();
        //getQueryWrapper(queryWrapper,queryForm);
        return baseFileDefMapper.selectList(queryWrapper);
    }


}

