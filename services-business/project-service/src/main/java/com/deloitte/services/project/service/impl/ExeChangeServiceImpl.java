package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.ExeChangeQueryForm;
import com.deloitte.services.project.entity.ExeChange;
import com.deloitte.services.project.mapper.ExeChangeMapper;
import com.deloitte.services.project.service.IExeChangeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-17
 * @Description :  ExeChange服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ExeChangeServiceImpl extends ServiceImpl<ExeChangeMapper, ExeChange> implements IExeChangeService {


    @Autowired
    private ExeChangeMapper exeChangeMapper;

    @Override
    public IPage<ExeChange> selectPage(ExeChangeQueryForm queryForm ) {
        QueryWrapper<ExeChange> queryWrapper = new QueryWrapper <ExeChange>();
        //getQueryWrapper(queryWrapper,queryForm);
        return exeChangeMapper.selectPage(new Page<ExeChange>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ExeChange> selectList(ExeChangeQueryForm queryForm) {
        QueryWrapper<ExeChange> queryWrapper = new QueryWrapper <ExeChange>();
        //getQueryWrapper(queryWrapper,queryForm);
        return exeChangeMapper.selectList(queryWrapper);
    }

    @Override
    public List<ExeChange> getExeChangeList(long executionId) {
        QueryWrapper<ExeChange> queryWrapper = new QueryWrapper <ExeChange>();
        queryWrapper.eq(ExeChange.EXECUTION_ID, executionId);
        return exeChangeMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ExeChange> getQueryWrapper(QueryWrapper<ExeChange> queryWrapper,ExeChangeQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getChangeId())){
            queryWrapper.eq(ExeChange.CHANGE_ID,queryForm.getChangeId());
        }
        if(StringUtils.isNotBlank(queryForm.getExecutionId())){
            queryWrapper.eq(ExeChange.EXECUTION_ID,queryForm.getExecutionId());
        }
        if(StringUtils.isNotBlank(queryForm.getChangeAdv())){
            queryWrapper.eq(ExeChange.CHANGE_ADV,queryForm.getChangeAdv());
        }
        if(StringUtils.isNotBlank(queryForm.getChangeStatusCode())){
            queryWrapper.eq(ExeChange.CHANGE_STATUS_CODE,queryForm.getChangeStatusCode());
        }
        if(StringUtils.isNotBlank(queryForm.getChangeStatusName())){
            queryWrapper.eq(ExeChange.CHANGE_STATUS_NAME,queryForm.getChangeStatusName());
        }
        if(StringUtils.isNotBlank(queryForm.getHisTime())){
            queryWrapper.eq(ExeChange.HIS_TIME,queryForm.getHisTime());
        }
        if(StringUtils.isNotBlank(queryForm.getHisBy())){
            queryWrapper.eq(ExeChange.HIS_BY,queryForm.getHisBy());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ExeChange.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ExeChange.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ExeChange.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ExeChange.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(ExeChange.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(ExeChange.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(ExeChange.EXT3,queryForm.getExt3());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgId())){
            queryWrapper.eq(ExeChange.ORG_ID,queryForm.getOrgId());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgPath())){
            queryWrapper.eq(ExeChange.ORG_PATH,queryForm.getOrgPath());
        }
        return queryWrapper;
    }
     */
}

