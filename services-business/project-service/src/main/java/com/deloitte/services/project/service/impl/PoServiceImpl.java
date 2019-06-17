package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.PoQueryForm;
import com.deloitte.services.project.entity.Po;
import com.deloitte.services.project.mapper.PoMapper;
import com.deloitte.services.project.service.IPoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  Po服务实现类
 * @Modified :
 */
@Service
@Transactional
public class PoServiceImpl extends ServiceImpl<PoMapper, Po> implements IPoService {


    @Autowired
    private PoMapper poMapper;

    @Override
    public IPage<Po> selectPage(PoQueryForm queryForm ) {
        QueryWrapper<Po> queryWrapper = new QueryWrapper <Po>();
        //getQueryWrapper(queryWrapper,queryForm);
        return poMapper.selectPage(new Page<Po>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Po> selectList(PoQueryForm queryForm) {
        QueryWrapper<Po> queryWrapper = new QueryWrapper <Po>();
        //getQueryWrapper(queryWrapper,queryForm);
        return poMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Po> getQueryWrapper(QueryWrapper<Po> queryWrapper,PoQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getPoId())){
            queryWrapper.eq(Po.PO_ID,queryForm.getPoId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(Po.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getMtlName())){
            queryWrapper.eq(Po.MTL_NAME,queryForm.getMtlName());
        }
        if(StringUtils.isNotBlank(queryForm.getMtlQuantity())){
            queryWrapper.eq(Po.MTL_QUANTITY,queryForm.getMtlQuantity());
        }
        if(StringUtils.isNotBlank(queryForm.getMtlUnitPrice())){
            queryWrapper.eq(Po.MTL_UNIT_PRICE,queryForm.getMtlUnitPrice());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Po.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Po.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Po.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Po.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(Po.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(Po.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(Po.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getExt4())){
            queryWrapper.eq(Po.EXT4,queryForm.getExt4());
        }
        if(StringUtils.isNotBlank(queryForm.getExt5())){
            queryWrapper.eq(Po.EXT5,queryForm.getExt5());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(Po.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(Po.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

