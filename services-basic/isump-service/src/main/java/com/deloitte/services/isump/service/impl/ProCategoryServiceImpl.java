package com.deloitte.services.isump.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.isump.param.ProCategoryQueryForm;
import com.deloitte.platform.api.isump.vo.ProCategoryVo;
import com.deloitte.services.isump.entity.ProCategory;
import com.deloitte.services.isump.mapper.ProCategoryMapper;
import com.deloitte.services.isump.service.IProCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jianglong
 * @Date : Create in 2019-03-22
 * @Description :  ProCategory服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ProCategoryServiceImpl extends ServiceImpl<ProCategoryMapper, ProCategory> implements IProCategoryService {


    @Autowired
    private ProCategoryMapper proCategoryMapper;

    @Override
    public IPage<ProCategory> selectPage(ProCategoryQueryForm queryForm ) {
        QueryWrapper<ProCategory> queryWrapper = new QueryWrapper <ProCategory>();
        //getQueryWrapper(queryWrapper,queryForm);
        return proCategoryMapper.selectPage(new Page<ProCategory>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

    }

    @Override
    public List<ProCategoryVo> getByDeputyAccountId(long id){
       return proCategoryMapper.getListByDeputyAccountId(id);
    }

    @Override
    public List<ProCategory> selectList(ProCategoryQueryForm queryForm) {
        QueryWrapper<ProCategory> queryWrapper = new QueryWrapper <ProCategory>();
        //getQueryWrapper(queryWrapper,queryForm);
        return proCategoryMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ProCategory> getQueryWrapper(QueryWrapper<ProCategory> queryWrapper,ProCategoryQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getCode())){
            queryWrapper.eq(ProCategory.CODE,queryForm.getCode());
        }
        if(StringUtils.isNotBlank(queryForm.getName())){
            queryWrapper.eq(ProCategory.NAME,queryForm.getName());
        }
        if(StringUtils.isNotBlank(queryForm.getParentCode())){
            queryWrapper.eq(ProCategory.PARENT_CODE,queryForm.getParentCode());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(ProCategory.REMARK,queryForm.getRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ProCategory.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ProCategory.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ProCategory.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ProCategory.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getSysType())){
            queryWrapper.eq(ProCategory.SYS_TYPE,queryForm.getSysType());
        }
        return queryWrapper;
    }
     */
}

