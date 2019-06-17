package com.deloitte.services.srpmp.project.mpr.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprAcadPostTableQueryForm;
import com.deloitte.services.srpmp.project.mpr.entity.MprAcadPostTable;
import com.deloitte.services.srpmp.project.mpr.mapper.MprAcadPostTableMapper;
import com.deloitte.services.srpmp.project.mpr.service.IMprAcadPostTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :  MprAcadPostTable服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MprAcadPostTableServiceImpl extends ServiceImpl<MprAcadPostTableMapper, MprAcadPostTable> implements IMprAcadPostTableService {


    @Autowired
    private MprAcadPostTableMapper mprAcadPostTableMapper;

    @Override
    public IPage<MprAcadPostTable> selectPage(MprAcadPostTableQueryForm queryForm ) {
        QueryWrapper<MprAcadPostTable> queryWrapper = new QueryWrapper <MprAcadPostTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprAcadPostTableMapper.selectPage(new Page<MprAcadPostTable>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MprAcadPostTable> selectList(MprAcadPostTableQueryForm queryForm) {
        QueryWrapper<MprAcadPostTable> queryWrapper = new QueryWrapper <MprAcadPostTable>();
        //getQueryWrapper(queryWrapper,queryForm);
        return mprAcadPostTableMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MprAcadPostTable> getQueryWrapper(QueryWrapper<MprAcadPostTable> queryWrapper,MprAcadPostTableQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectNo())){
            queryWrapper.eq(MprAcadPostTable.PROJECT_NO,queryForm.getProjectNo());
        }
        if(StringUtils.isNotBlank(queryForm.getAcadEmpOrg())){
            queryWrapper.eq(MprAcadPostTable.ACAD_EMP_ORG,queryForm.getAcadEmpOrg());
        }
        if(StringUtils.isNotBlank(queryForm.getDomesticForeign())){
            queryWrapper.eq(MprAcadPostTable.DOMESTIC_FOREIGN,queryForm.getDomesticForeign());
        }
        if(StringUtils.isNotBlank(queryForm.getName())){
            queryWrapper.eq(MprAcadPostTable.NAME,queryForm.getName());
        }
        if(StringUtils.isNotBlank(queryForm.getPosition())){
            queryWrapper.eq(MprAcadPostTable.POSITION,queryForm.getPosition());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MprAcadPostTable.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MprAcadPostTable.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MprAcadPostTable.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MprAcadPostTable.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

