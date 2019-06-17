package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.project.param.MaintenanceQueryForm;
import com.deloitte.services.project.entity.Maintenance;
import com.deloitte.services.project.mapper.MaintenanceMapper;
import com.deloitte.services.project.service.IMaintenanceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :  Maintenance服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MaintenanceServiceImpl extends ServiceImpl<MaintenanceMapper, Maintenance> implements IMaintenanceService {


    @Autowired
    private MaintenanceMapper maintenanceMapper;

    @Override
    public IPage<Maintenance> selectPage(MaintenanceQueryForm queryForm ) {
        QueryWrapper<Maintenance> queryWrapper = new QueryWrapper <Maintenance>();
        //getQueryWrapper(queryWrapper,queryForm);
        return maintenanceMapper.selectPage(new Page<Maintenance>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Maintenance> selectList(MaintenanceQueryForm queryForm) {
        QueryWrapper<Maintenance> queryWrapper = new QueryWrapper <Maintenance>();
        //getQueryWrapper(queryWrapper,queryForm);
        return maintenanceMapper.selectList(queryWrapper);
    }

    @Override
    public List<Maintenance> getOneList(String projectId) {
        QueryWrapper<Maintenance> queryWrapper = new QueryWrapper <Maintenance>();
        queryWrapper.eq(Maintenance.PROJECT_ID,projectId);
        return maintenanceMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<Maintenance> getQueryWrapper(QueryWrapper<Maintenance> queryWrapper,MaintenanceQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getMaintenanceId())){
            queryWrapper.eq(Maintenance.MAINTENANCE_ID,queryForm.getMaintenanceId());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(Maintenance.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(Maintenance.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getMainNote())){
            queryWrapper.eq(Maintenance.MAIN_NOTE,queryForm.getMainNote());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(Maintenance.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(Maintenance.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(Maintenance.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(Maintenance.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(Maintenance.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(Maintenance.EXT2,queryForm.getExt2());
        }
        return queryWrapper;
    }
     */
}

