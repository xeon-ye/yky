package com.deloitte.services.contract.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.SysProjectInfoQueryForm;
import com.deloitte.platform.api.contract.vo.BasicInfoVo;
import com.deloitte.platform.api.contract.vo.BasicProjectMapForm;
import com.deloitte.platform.api.contract.vo.BasicProjectMapVo;
import com.deloitte.platform.api.contract.vo.SysProjectInfoVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.feign.SrpmsProjectExtFeignService;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectQueryForm;
import com.deloitte.platform.api.srpmp.project.base.vo.SrpmsProjectVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.contract.common.enums.ContractErrorType;
import com.deloitte.services.contract.common.util.AssertUtils;
import com.deloitte.services.contract.entity.BasicProjectMap;
import com.deloitte.services.contract.entity.BasicSubjectMap;
import com.deloitte.services.contract.entity.SysProjectInfo;
import com.deloitte.services.contract.mapper.BasicInfoMapper;
import com.deloitte.services.contract.mapper.BasicProjectMapMapper;
import com.deloitte.services.contract.mapper.SysProjectInfoMapper;
import com.deloitte.services.contract.service.ISysProjectInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  SysProjectInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class SysProjectInfoServiceImpl extends ServiceImpl<SysProjectInfoMapper, SysProjectInfo> implements ISysProjectInfoService {


    @Autowired
    private SysProjectInfoMapper sysProjectInfoMapper;
    @Autowired
    private BasicProjectMapMapper basicProjectMapMapper;
    @Autowired
    private BasicInfoMapper basicInfoMapper;
    @Autowired
    private SrpmsProjectExtFeignService srpmsProjectExtFeignService;

    @Override
    public IPage<SysProjectInfo> selectPage(SysProjectInfoQueryForm queryForm ) {
        QueryWrapper<SysProjectInfo> queryWrapper = new QueryWrapper <SysProjectInfo>();
        getQueryWrapper(queryWrapper,queryForm);
        return sysProjectInfoMapper.selectPage(new Page<SysProjectInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<SysProjectInfo> selectList(SysProjectInfoQueryForm queryForm) {
        QueryWrapper<SysProjectInfo> queryWrapper = new QueryWrapper <SysProjectInfo>();
        getQueryWrapper(queryWrapper,queryForm);
        return sysProjectInfoMapper.selectList(queryWrapper);
    }

    @Override
    public List<SysProjectInfoVo> querySysProjectInfo(String contractId) {
        QueryWrapper<BasicProjectMap> queryWrapper = new QueryWrapper <>();
        queryWrapper.eq(BasicProjectMap.CONTRACT_ID,contractId);
        List<BasicProjectMap> basicSubjectMapList = basicProjectMapMapper.selectList(queryWrapper);
        String ids = "";
        for (BasicProjectMap basicProjectMap: basicSubjectMapList) {
            ids = ids + basicProjectMap.getProjectId() + ",";
        }
        if (ids != null && !ids.equals("")){
            ids = ids.substring(0, ids.lastIndexOf(","));
        }
        if (ids != null && !ids.equals("")){
            Result<List<SrpmsProjectVo>> result = srpmsProjectExtFeignService.listByProjectIds(ids);
            if(result.isSuccess()){
                List<SysProjectInfoVo> sysProjectInfoVoList = projectInfoChange(result.getData());
                return sysProjectInfoVoList;
            }
        }
        return new ArrayList<>();
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     * */
    public QueryWrapper<SysProjectInfo> getQueryWrapper(QueryWrapper<SysProjectInfo> queryWrapper,SysProjectInfoQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectCode())){
            queryWrapper.like(SysProjectInfo.PROJECT_CODE,queryForm.getProjectCode());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectName())){
            queryWrapper.like(SysProjectInfo.PROJECT_NAME,queryForm.getProjectName());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgCode())){
            queryWrapper.eq(SysProjectInfo.ORG_CODE,queryForm.getOrgCode());
        }
        if(StringUtils.isNotBlank(queryForm.getOrg())){
            queryWrapper.eq(SysProjectInfo.ORG,queryForm.getOrg());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectManagerCode())){
            queryWrapper.eq(SysProjectInfo.PROJECT_MANAGER_CODE,queryForm.getProjectManagerCode());
        }
        if(StringUtils.isNotBlank(queryForm.getProjectManager())){
            queryWrapper.eq(SysProjectInfo.PROJECT_MANAGER,queryForm.getProjectManager());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(SysProjectInfo.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(SysProjectInfo.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(SysProjectInfo.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(SysProjectInfo.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(SysProjectInfo.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(SysProjectInfo.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        return queryWrapper;
    }

    public BasicProjectMap saveSysProject(BasicProjectMap basicProjectMap, UserVo userVo){
        // 查询合同关联信息
        Long choiceId = basicProjectMap.getContractId();
        List<BasicInfoVo> basicInfoVos = basicInfoMapper.queryCorrelationBasic(choiceId.toString());
        AssertUtils.asserts(basicInfoVos == null || basicInfoVos.size() <= 0, ContractErrorType.CONTRACT_IS_NULL);
        // 合同关联状态
        String relationCode = basicInfoVos.get(0).getRelationCode();
        if (relationCode != null && relationCode.equals("REL3000")){
            BasicInfoVo basicInfoVoRel  = basicInfoVos.get(0);
            basicInfoVos = new ArrayList<>();
            basicInfoVos.add(basicInfoVoRel);
        }

        for (int i = 0; i < basicInfoVos.size(); i++){
            Long contractId = Long.parseLong(basicInfoVos.get(i).getId());
            BasicProjectMap basicProject = new BasicProjectMap();
            basicProject.setContractId(contractId);
            basicProject.setProjectId(basicProjectMap.getProjectId());
            basicProject.setIsUsed(basicProjectMap.getIsUsed());
            basicProject.setCreateBy(userVo.getId());
            basicProjectMapMapper.insert(basicProject);
        }
        return basicProjectMap;
    }

    public int deleteBasicProjectMapById(Long id){
        QueryWrapper<BasicProjectMap> queryWrapper = new QueryWrapper();
        queryWrapper.eq(BasicProjectMap.PROJECT_ID, id);
        return basicProjectMapMapper.delete(queryWrapper);
    }

    public boolean deleteBasicProjectMap(BasicProjectMap basicProjectMap){
        Long choiceId = basicProjectMap.getContractId();
        Long projectId = basicProjectMap.getProjectId();
        // 查询合同关联信息
        List<BasicInfoVo> basicInfoVos = basicInfoMapper.queryCorrelationBasic(choiceId.toString());
        AssertUtils.asserts(basicInfoVos == null || basicInfoVos.size() <= 0, ContractErrorType.CONTRACT_IS_NULL);
        String relationCode = basicInfoVos.get(0).getRelationCode();
        if (relationCode != null && relationCode.equals("REL3000")){
            BasicInfoVo basicInfoVoRel  = basicInfoVos.get(0);
            basicInfoVos = new ArrayList<>();
            basicInfoVos.add(basicInfoVoRel);
        }

        for (int i = 0; i < basicInfoVos.size(); i++){
            Long contractId = Long.parseLong(basicInfoVos.get(i).getId());
            QueryWrapper<BasicProjectMap> queryWrapper = new QueryWrapper();
            queryWrapper.eq(BasicProjectMap.CONTRACT_ID, contractId);
            queryWrapper.eq(BasicProjectMap.PROJECT_ID, projectId);
            basicProjectMapMapper.delete(queryWrapper);
        }
        return true;
    }

    /**
     * 查询项目信息（调用科研接口）
     * @param sysProjectInfoQueryForm
     * @return
     */
    public IPage<SysProjectInfoVo> searchNoUser(SysProjectInfoQueryForm sysProjectInfoQueryForm){
        SrpmsProjectQueryForm srpmsProjectQueryForm = new SrpmsProjectQueryForm();
        srpmsProjectQueryForm.setPageSize(sysProjectInfoQueryForm.getPageSize());
        srpmsProjectQueryForm.setCurrentPage(sysProjectInfoQueryForm.getCurrentPage());
        srpmsProjectQueryForm.setProjectNum(sysProjectInfoQueryForm.getProjectCode());
        srpmsProjectQueryForm.setProjectName(sysProjectInfoQueryForm.getProjectName());
        GdcPage<SrpmsProjectVo> srpmsProjectVoPage = null;
        Result<GdcPage<SrpmsProjectVo>> result = srpmsProjectExtFeignService.searchNoUser(srpmsProjectQueryForm);
        if (result.isSuccess()){
            srpmsProjectVoPage = result.getData();
            // page: long current, long size, long total, boolean isSearchCount
            IPage<SysProjectInfoVo> sysProjectInfoVoPsge = new Page<>(srpmsProjectVoPage.getPageNo(), srpmsProjectVoPage.getPageSize(), srpmsProjectVoPage.getTotal(), true);
            List<SrpmsProjectVo> srpmsProjectVoList = srpmsProjectVoPage.getContent();
            List<SysProjectInfoVo> sysProjectInfoVoList = projectInfoChange(srpmsProjectVoList);
            sysProjectInfoVoPsge.setRecords(sysProjectInfoVoList);
            return sysProjectInfoVoPsge;
        }
        return null;
    }

    /**
     * 转换接口vo
     * @param srpmsProjectVoList
     * @return
     */
    private List<SysProjectInfoVo> projectInfoChange(List<SrpmsProjectVo> srpmsProjectVoList){
        List<SysProjectInfoVo> sysProjectInfoVoList = new ArrayList<>();
        if(srpmsProjectVoList != null && srpmsProjectVoList.size() > 0){
            for (SrpmsProjectVo srpmsProjectVo: srpmsProjectVoList) {
                SysProjectInfoVo sysProjectInfoVo = new SysProjectInfoVo();
                sysProjectInfoVo.setId(String.valueOf(srpmsProjectVo.getId()));
                sysProjectInfoVo.setProjectCode(srpmsProjectVo.getProjectNum());
                sysProjectInfoVo.setProjectName(srpmsProjectVo.getProjectName());
                sysProjectInfoVo.setOrgCode(String.valueOf(srpmsProjectVo.getLeadDeptId()));
                JSONObject leadDept = srpmsProjectVo.getLeadDept();
                if (leadDept != null){
                    String org = leadDept.get("deptName") != null ? (String)leadDept.get("deptName") : "";
                    sysProjectInfoVo.setOrg(org);
                }
                sysProjectInfoVo.setProjectManagerCode(String.valueOf(srpmsProjectVo.getLeadPersonId()));
                JSONObject leadPerson = srpmsProjectVo.getLeadPerson();
                if (leadPerson != null){
                    String projectManager = leadPerson.get("name") != null ? (String)leadPerson.get("name") : "";
                    sysProjectInfoVo.setProjectManager(projectManager);
                }
                sysProjectInfoVoList.add(sysProjectInfoVo);
            }
        }
        return sysProjectInfoVoList;
    }
}

