package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.MonitorInfoQueryForm;
import com.deloitte.platform.api.contract.vo.BasicInfoVo;
import com.deloitte.platform.api.contract.vo.MonitorInfoForm;
import com.deloitte.platform.api.contract.vo.MonitorInfoVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.services.contract.common.enums.ContractErrorType;
import com.deloitte.services.contract.common.util.AssertUtils;
import com.deloitte.services.contract.entity.BasicMonitorMap;
import com.deloitte.services.contract.entity.MonitorInfo;
import com.deloitte.services.contract.mapper.BasicInfoMapper;
import com.deloitte.services.contract.mapper.BasicMonitorMapMapper;
import com.deloitte.services.contract.mapper.MonitorInfoMapper;
import com.deloitte.services.contract.service.IMonitorInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  MonitorInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class MonitorInfoServiceImpl extends ServiceImpl<MonitorInfoMapper, MonitorInfo> implements IMonitorInfoService {


    @Autowired
    private MonitorInfoMapper monitorInfoMapper;
    @Autowired
    private BasicInfoMapper basicInfoMapper;
    @Autowired
    private BasicMonitorMapMapper basicMonitorMapMapper;

    @Override
    public IPage<MonitorInfo> selectPage(MonitorInfoQueryForm queryForm ) {
        QueryWrapper<MonitorInfo> queryWrapper = new QueryWrapper <MonitorInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return monitorInfoMapper.selectPage(new Page<MonitorInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<MonitorInfo> selectList(MonitorInfoQueryForm queryForm) {
        QueryWrapper<MonitorInfo> queryWrapper = new QueryWrapper <MonitorInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return monitorInfoMapper.selectList(queryWrapper);
    }

    @Override
    public List<MonitorInfoVo> queryMonitorInfo(String contractId) {
        return monitorInfoMapper.queryMonitorInfo(contractId);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<MonitorInfo> getQueryWrapper(QueryWrapper<MonitorInfo> queryWrapper,MonitorInfoQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getMonitorName())){
            queryWrapper.eq(MonitorInfo.MONITOR_NAME,queryForm.getMonitorName());
        }
        if(StringUtils.isNotBlank(queryForm.getPlanFinishTime())){
            queryWrapper.eq(MonitorInfo.PLAN_FINISH_TIME,queryForm.getPlanFinishTime());
        }
        if(StringUtils.isNotBlank(queryForm.getPlanFinishNum())){
            queryWrapper.eq(MonitorInfo.PLAN_FINISH_NUM,queryForm.getPlanFinishNum());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(MonitorInfo.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(MonitorInfo.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(MonitorInfo.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(MonitorInfo.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(MonitorInfo.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getContractId())){
            queryWrapper.eq(MonitorInfo.CONTRACT_ID,queryForm.getContractId());
        }
        if(StringUtils.isNotBlank(queryForm.getContractName())){
            queryWrapper.eq(MonitorInfo.CONTRACT_NAME,queryForm.getContractName());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(MonitorInfo.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(MonitorInfo.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(MonitorInfo.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(MonitorInfo.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(MonitorInfo.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        return queryWrapper;
    }
     */

    /**
     * 保存合同其他监控信息
     * @param monitorInfo
     * @param userVo
     * @return
     */
    public MonitorInfo saveMonitorInfo(MonitorInfo monitorInfo, UserVo userVo){
        // 查询关联合同信息
        Long choiceId = monitorInfo.getContractId();
        List<BasicInfoVo> basicInfoVos = basicInfoMapper.queryCorrelationBasic(choiceId.toString());
        AssertUtils.asserts(basicInfoVos == null || basicInfoVos.size() <= 0, ContractErrorType.CONTRACT_IS_NULL);
        String relationCode = basicInfoVos.get(0).getRelationCode();
        if (relationCode != null && relationCode.equals("REL3000")){
            BasicInfoVo basicInfoVoRel  = basicInfoVos.get(0);
            basicInfoVos = new ArrayList<>();
            basicInfoVos.add(basicInfoVoRel);
        }

        // 保存合同其他监控信息
        monitorInfo.setCreateBy(userVo.getId());
        monitorInfoMapper.insert(monitorInfo);
        Long monitorId = monitorInfo.getId();
        for (int i = 0; i < basicInfoVos.size(); i++){
            String contractId = basicInfoVos.get(i).getId();
            BasicMonitorMap basicMonitorMap = new BasicMonitorMap();
            basicMonitorMap.setContractId(Long.parseLong(contractId));
            basicMonitorMap.setMonitorId(monitorId);
            basicMonitorMap.setIsUsed("1");
            basicMonitorMap.setCreateBy(userVo.getId());
            basicMonitorMapMapper.insert(basicMonitorMap);
        }
        return monitorInfo;
    }

    /**
     * 根据其他监控信息id删除合同其他监控信息
     * @param id
     * @return
     */
    public boolean deleteMonitorInfoById(Long id){
        monitorInfoMapper.deleteById(id);
        QueryWrapper<BasicMonitorMap> queryWrapper = new QueryWrapper();
        queryWrapper.eq(BasicMonitorMap.MONITOR_ID, id);
        basicMonitorMapMapper.delete(queryWrapper);
        return true;
    }

    public MonitorInfo updateMonitorInfo(MonitorInfo monitorInfo, UserVo userVo){
        monitorInfo.setUpdateBy(userVo.getId());
        monitorInfoMapper.updateById(monitorInfo);
        return monitorInfo;
    }
}

