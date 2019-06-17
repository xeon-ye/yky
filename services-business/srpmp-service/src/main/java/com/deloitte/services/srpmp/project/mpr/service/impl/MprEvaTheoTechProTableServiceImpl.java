package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaTheoTechProTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaTheoTechProTable;
import com.deloitte.services.srpmp.project.mpr.mapper.MprEvaTheoTechProTableMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaTheoTechProTableService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprEvaTheoTechProTable服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprEvaTheoTechProTableServiceImpl extends ServiceImpl<MprEvaTheoTechProTableMapper, MprEvaTheoTechProTable> implements IMprEvaTheoTechProTableService {


    @Autowired
    private MprEvaTheoTechProTableMapper mprEvaTheoTechProTableMapper;

    @Override
    public IPage<MprEvaTheoTechProTable> selectPage(MprEvaTheoTechProTableQueryForm queryForm ) {
        QueryWrapper<MprEvaTheoTechProTable> queryWrapper = new QueryWrapper <MprEvaTheoTechProTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaTheoTechProTableMapper.selectPage(new Page<MprEvaTheoTechProTable>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprEvaTheoTechProTable> selectList(MprEvaTheoTechProTableQueryForm queryForm) {
        QueryWrapper<MprEvaTheoTechProTable> queryWrapper = new QueryWrapper <MprEvaTheoTechProTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprEvaTheoTechProTableMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprEvaTheoTechProTable> getQueryWrapper(QueryWrapper<MprEvaTheoTechProTable> queryWrapper,MprEvaTheoTechProTableQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprEvaTheoTechProTable.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getOutputType())){
            queryWrapper.eq(MprEvaTheoTechProTable.OUTPUT_TYPE,queryForm.getOutputType());
        }
        if(StringUtils.isNotBlank(queryForm.getOutputName())){
            queryWrapper.eq(MprEvaTheoTechProTable.OUTPUT_NAME,queryForm.getOutputName());
        }
        if(StringUtils.isNotBlank(queryForm.getBringMean())){
            queryWrapper.eq(MprEvaTheoTechProTable.BRING_MEAN,queryForm.getBringMean());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprEvaTheoTechProTable.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprEvaTheoTechProTable.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprEvaTheoTechProTable.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprEvaTheoTechProTable.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

