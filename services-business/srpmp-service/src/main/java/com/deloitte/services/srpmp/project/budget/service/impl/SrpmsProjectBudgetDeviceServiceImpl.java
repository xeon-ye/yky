package com.deloitte.services.srpmp.project.budget.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.srpmp.project.budget.param.SrpmsProjectBudgetDeviceQueryForm;
import com.deloitte.platform.api.srpmp.project.budget.vo.SrpmsProjectBudgetDeviceVo;
import com.deloitte.services.srpmp.common.enums.BudgetCategoryEnums;
import com.deloitte.services.srpmp.project.budget.entity.SrpmsProjectBudgetDevice;
import com.deloitte.services.srpmp.project.budget.mapper.SrpmsProjectBudgetDeviceMapper;
import com.deloitte.services.srpmp.project.budget.service.ISrpmsProjectBudgetDeviceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;


import java.util.ArrayList;
import java.util.List;
/**
 * @Author : lixin
 * @Date : Create in 2019-02-27
 * @Description :  SrpmsProjectBudgetDevice服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SrpmsProjectBudgetDeviceServiceImpl extends ServiceImpl<SrpmsProjectBudgetDeviceMapper, SrpmsProjectBudgetDevice> implements ISrpmsProjectBudgetDeviceService {


    @Autowired
    private SrpmsProjectBudgetDeviceMapper srpmsProjectBudgetDeviceMapper;

    @Override
    public IPage<SrpmsProjectBudgetDevice> selectPage(SrpmsProjectBudgetDeviceQueryForm queryForm ) {
        QueryWrapper<SrpmsProjectBudgetDevice> queryWrapper = new QueryWrapper <SrpmsProjectBudgetDevice>();
        queryWrapper.orderByAsc(SrpmsProjectBudgetDevice.ID);
        //getQueryWrapper(queryWrapper,queryForm);
        return srpmsProjectBudgetDeviceMapper.selectPage(new Page<SrpmsProjectBudgetDevice>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<SrpmsProjectBudgetDevice> selectList(SrpmsProjectBudgetDeviceQueryForm queryForm) {
        QueryWrapper<SrpmsProjectBudgetDevice> queryWrapper = new QueryWrapper <SrpmsProjectBudgetDevice>();
        //getQueryWrapper(queryWrapper,queryForm);
        queryWrapper.orderByAsc(SrpmsProjectBudgetDevice.ID);
        return srpmsProjectBudgetDeviceMapper.selectList(queryWrapper);
    }

    @Override
    public void cleanAndSaveBudgetDevice(List<SrpmsProjectBudgetDeviceVo> deviceVoList, BudgetCategoryEnums joinWayEnum, Long projectId) {
        UpdateWrapper<SrpmsProjectBudgetDevice> personJoinUpdateWrapper = new UpdateWrapper<>();
        personJoinUpdateWrapper.eq(SrpmsProjectBudgetDevice.JOIN_WAY, joinWayEnum.getCode());
        personJoinUpdateWrapper.eq(SrpmsProjectBudgetDevice.PROJECT_ID, projectId);
        this.remove(personJoinUpdateWrapper);

        saveBudgetDevice(deviceVoList, joinWayEnum, projectId);
    }

    @Override
    public void saveBudgetDevice(List<SrpmsProjectBudgetDeviceVo> deviceVoList, BudgetCategoryEnums joinWayEnum, Long projectId) {
        if (deviceVoList == null) {
            return;
        }
        List<SrpmsProjectBudgetDevice> deviceList = new ArrayList<>();
        for (SrpmsProjectBudgetDeviceVo personJoinVo : deviceVoList) {
            SrpmsProjectBudgetDevice deviceEntity = JSONObject.parseObject(JSON.toJSONString(personJoinVo), SrpmsProjectBudgetDevice.class);
            deviceEntity.setJoinWay(joinWayEnum.getCode());
            deviceEntity.setProjectId(projectId);
            deviceEntity.setId(null);
            deviceList.add(deviceEntity);
        }
        this.saveBatch(deviceList);
    }

    @Override
    public List<SrpmsProjectBudgetDeviceVo> queryBudgetDeviceList(BudgetCategoryEnums joinWay, Long projectId) {
        QueryWrapper<SrpmsProjectBudgetDevice> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SrpmsProjectBudgetDevice.JOIN_WAY, joinWay.getCode());
        queryWrapper.eq(SrpmsProjectBudgetDevice.PROJECT_ID, projectId);
        List<SrpmsProjectBudgetDevice> entityList = this.list(queryWrapper);
        if (entityList != null) {
            List<SrpmsProjectBudgetDeviceVo> voList = new ArrayList<>();
            for (SrpmsProjectBudgetDevice deviceEntity : entityList) {
                SrpmsProjectBudgetDeviceVo deviceVo = JSONObject.parseObject(JSON.toJSONString(deviceEntity), SrpmsProjectBudgetDeviceVo.class);
                voList.add(deviceVo);
            }
            return voList;
        }
        return null;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<SrpmsProjectBudgetDevice> getQueryWrapper(QueryWrapper<SrpmsProjectBudgetDevice> queryWrapper,BaseQueryForm<SrpmsProjectBudgetDeviceQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getProjectId())){
            queryWrapper.like(SrpmsProjectBudgetDevice.PROJECT_ID,srpmsProjectBudgetDevice.getProjectId());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getSerial())){
            queryWrapper.like(SrpmsProjectBudgetDevice.SERIAL,srpmsProjectBudgetDevice.getSerial());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getJoinWay())){
            queryWrapper.like(SrpmsProjectBudgetDevice.JOIN_WAY,srpmsProjectBudgetDevice.getJoinWay());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getDeviceName())){
            queryWrapper.like(SrpmsProjectBudgetDevice.DEVICE_NAME,srpmsProjectBudgetDevice.getDeviceName());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getDeviceCat())){
            queryWrapper.like(SrpmsProjectBudgetDevice.DEVICE_CAT,srpmsProjectBudgetDevice.getDeviceCat());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getDeviceType())){
            queryWrapper.like(SrpmsProjectBudgetDevice.DEVICE_TYPE,srpmsProjectBudgetDevice.getDeviceType());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getDeviceNo())){
            queryWrapper.like(SrpmsProjectBudgetDevice.DEVICE_NO,srpmsProjectBudgetDevice.getDeviceNo());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getDeviceNum())){
            queryWrapper.like(SrpmsProjectBudgetDevice.DEVICE_NUM,srpmsProjectBudgetDevice.getDeviceNum());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getProducer())){
            queryWrapper.like(SrpmsProjectBudgetDevice.PRODUCER,srpmsProjectBudgetDevice.getProducer());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getTechQuota())){
            queryWrapper.like(SrpmsProjectBudgetDevice.TECH_QUOTA,srpmsProjectBudgetDevice.getTechQuota());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getUseage())){
            queryWrapper.like(SrpmsProjectBudgetDevice.USEAGE,srpmsProjectBudgetDevice.getUseage());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getContent())){
            queryWrapper.like(SrpmsProjectBudgetDevice.CONTENT,srpmsProjectBudgetDevice.getContent());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getDeptName())){
            queryWrapper.like(SrpmsProjectBudgetDevice.DEPT_NAME,srpmsProjectBudgetDevice.getDeptName());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getMeasurementUnit())){
            queryWrapper.like(SrpmsProjectBudgetDevice.MEASUREMENT_UNIT,srpmsProjectBudgetDevice.getMeasurementUnit());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getUnitPrice())){
            queryWrapper.like(SrpmsProjectBudgetDevice.UNIT_PRICE,srpmsProjectBudgetDevice.getUnitPrice());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getDeviceCount())){
            queryWrapper.like(SrpmsProjectBudgetDevice.DEVICE_COUNT,srpmsProjectBudgetDevice.getDeviceCount());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getAmount())){
            queryWrapper.like(SrpmsProjectBudgetDevice.AMOUNT,srpmsProjectBudgetDevice.getAmount());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getCreateTime())){
            queryWrapper.like(SrpmsProjectBudgetDevice.CREATE_TIME,srpmsProjectBudgetDevice.getCreateTime());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getCreateBy())){
            queryWrapper.like(SrpmsProjectBudgetDevice.CREATE_BY,srpmsProjectBudgetDevice.getCreateBy());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getUpdateTime())){
            queryWrapper.like(SrpmsProjectBudgetDevice.UPDATE_TIME,srpmsProjectBudgetDevice.getUpdateTime());
        }
        if(StringUtils.isNotBlank(srpmsProjectBudgetDevice.getUpdateBy())){
            queryWrapper.like(SrpmsProjectBudgetDevice.UPDATE_BY,srpmsProjectBudgetDevice.getUpdateBy());
        }
        return queryWrapper;
    }
     */
}

