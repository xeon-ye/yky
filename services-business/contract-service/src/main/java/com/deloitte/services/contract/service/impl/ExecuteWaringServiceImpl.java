package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.ExecuteWaringQueryForm;
import com.deloitte.services.contract.entity.ExecuteWaring;
import com.deloitte.services.contract.mapper.ExecuteWaringMapper;
import com.deloitte.services.contract.service.IExecuteWaringService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : yangyq
 * @Date : Create in 2019-04-28
 * @Description :  ExecuteWaring服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ExecuteWaringServiceImpl extends ServiceImpl<ExecuteWaringMapper, ExecuteWaring> implements IExecuteWaringService {


    @Autowired
    private ExecuteWaringMapper executeWaringMapper;

    @Override
    public IPage<ExecuteWaring> selectPage(ExecuteWaringQueryForm queryForm ) {
        QueryWrapper<ExecuteWaring> queryWrapper = new QueryWrapper <ExecuteWaring>();
        //getQueryWrapper(queryWrapper,queryForm);
        return executeWaringMapper.selectPage(new Page<ExecuteWaring>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ExecuteWaring> selectList(ExecuteWaringQueryForm queryForm) {
        QueryWrapper<ExecuteWaring> queryWrapper = new QueryWrapper <ExecuteWaring>();
        //getQueryWrapper(queryWrapper,queryForm);
        return executeWaringMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ExecuteWaring> getQueryWrapper(QueryWrapper<ExecuteWaring> queryWrapper,ExecuteWaringQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getId())){
            queryWrapper.eq(ExecuteWaring.ID,queryForm.getId());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptCode())){
            queryWrapper.eq(ExecuteWaring.DEPT_CODE,queryForm.getDeptCode());
        }
        if(StringUtils.isNotBlank(queryForm.getDeptName())){
            queryWrapper.eq(ExecuteWaring.DEPT_NAME,queryForm.getDeptName());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgCode())){
            queryWrapper.eq(ExecuteWaring.ORG_CODE,queryForm.getOrgCode());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgName())){
            queryWrapper.eq(ExecuteWaring.ORG_NAME,queryForm.getOrgName());
        }
        if(StringUtils.isNotBlank(queryForm.getWaringTime())){
            queryWrapper.eq(ExecuteWaring.WARING_TIME,queryForm.getWaringTime());
        }
        if(StringUtils.isNotBlank(queryForm.getWaringFrequency())){
            queryWrapper.eq(ExecuteWaring.WARING_FREQUENCY,queryForm.getWaringFrequency());
        }
        if(StringUtils.isNotBlank(queryForm.getRemark())){
            queryWrapper.eq(ExecuteWaring.REMARK,queryForm.getRemark());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ExecuteWaring.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ExecuteWaring.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ExecuteWaring.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ExecuteWaring.UPDATE_BY,queryForm.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

