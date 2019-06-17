package com.deloitte.services.srpmp.project.base.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.param.SrpmsProjectQueryForm;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.common.enums.EnumMprProcessStatus;
import com.deloitte.services.srpmp.common.enums.ReportTypeEnum;
import com.deloitte.services.srpmp.common.enums.SrpmsProjectStatusEnums;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.util.CheckUtils;
import com.deloitte.services.srpmp.common.util.JSONConvert;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.project.base.entity.SrpmsProject;
import com.deloitte.services.srpmp.project.base.mapper.SrpmsProjectMapper;
import com.deloitte.services.srpmp.project.base.service.ISrpmsProjectAndJoinService;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaBaseInfo;
import com.deloitte.services.srpmp.project.mpr.service.IMprEvaBaseInfoService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : lixin
 * @Date : Create in 2019-02-19
 * @Description :  SrpmsProject服务实现类
 * @Modified :
 */
@Service
@Transactional
@Slf4j
public class SrpmsProjectAndJoinServiceImpl extends ServiceImpl<SrpmsProjectMapper, SrpmsProject> implements ISrpmsProjectAndJoinService {

    @Autowired
    private SrpmsProjectMapper srpmsProjectMapper;

    @Autowired
    private IMprEvaBaseInfoService mprEvaBaseInfoService;

    @Autowired
    private ISysDictService sysDictService;

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public JSONObject selectPage(SrpmsProjectQueryForm queryForm, UserVo user, DeptVo dept) {

        String roleCode = user.getHonor();
        String deptCode = dept.getDeptCode();
        String proType = user.getRemark();
        int tableFlg = queryForm.getTableFlag();

        String status = queryForm.getStatus();
        if (StringUtils.isNotBlank(status)) {
            if (SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode().equals(status)
                    || SrpmsProjectStatusEnums.PEROJECT_HAS_SUBMIT.getCode().equals(status)) {
                status = SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode().concat(",").concat(SrpmsProjectStatusEnums.PEROJECT_HAS_SUBMIT.getCode());
                queryForm.setStatusArr(status.split(","));
            }
            queryForm.setStatus(status);
        } else {
            status = SrpmsProjectStatusEnums.PEROJECT_NOT_SUBMIT.getCode().concat(",").concat(SrpmsProjectStatusEnums.PEROJECT_HAS_SUBMIT.getCode());
            queryForm.setStatusArr(status.split(","));
        }

        IPage<SrpmsProject> page;
        if (tableFlg == SrpmsConstant.TABLE_FLAG_1) {
            queryForm.setLeadPersonId(CommonUtil.getLongValue(user.getId()));
            page = new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize());
            page.setRecords(srpmsProjectMapper.selectMyPage(page, queryForm));
        } else if (tableFlg == SrpmsConstant.TABLE_FLAG_2) {
            if (StringUtils.isBlank(proType)) {
                return CommonUtil.getEmptyRelust();
            }

            if (!SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode)) {
                queryForm.setLeadDeptId(CommonUtil.getLongValue(dept.getDeptId()));
            }
            if (queryForm.getSelfDataFlag() == 1) {// 此标识判断管理员是否查询自己的数据
                queryForm.setLeadPersonId(CommonUtil.getLongValue(user.getId()));
            }

            if (StringUtils.isBlank(queryForm.getProjectType())) {
                queryForm.setProjectType(proType);
            } else if (proType.contains(queryForm.getProjectType())) {
                queryForm.setProjectType(queryForm.getProjectType());
            } else {
                return CommonUtil.getEmptyRelust();
            }

            QueryWrapper<SrpmsProject> queryWrapper = new QueryWrapper<>();
            getQueryWrapper(queryWrapper, queryForm);
            log.info("加入数据权限之后的查询条件为" + JSONObject.toJSONString(queryForm));
            page = srpmsProjectMapper.selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);
        } else {// 初始化权限显示table
            if (SrpmsConstant.SRPMS_ADMIN.equals(roleCode) || SrpmsConstant.SRPMS_LEADER.equals(roleCode)) {
                if (StringUtils.isBlank(proType)) {
                    return CommonUtil.getEmptyRelust();
                }
                tableFlg = SrpmsConstant.TABLE_FLAG_2;
                if (!SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode)) {
                    queryForm.setLeadDeptId(CommonUtil.getLongValue(dept.getDeptId()));
                }
                if (queryForm.getSelfDataFlag() == 1) {// 此标识判断管理员是否查询自己的数据
                    queryForm.setLeadPersonId(CommonUtil.getLongValue(user.getId()));
                }
                if (StringUtils.isBlank(queryForm.getProjectType())) {
                    queryForm.setProjectType(proType);
                } else if (proType.contains(queryForm.getProjectType())) {
                    queryForm.setProjectType(queryForm.getProjectType());
                } else {
                    return CommonUtil.getEmptyRelust();
                }
                QueryWrapper<SrpmsProject> queryWrapper = new QueryWrapper<>();
                getQueryWrapper(queryWrapper, queryForm);
                log.info("加入数据权限之后的查询条件为" + JSONObject.toJSONString(queryForm));
                page = srpmsProjectMapper.selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);
            } else {
                tableFlg = SrpmsConstant.TABLE_FLAG_1;
                queryForm.setLeadPersonId(CommonUtil.getLongValue(user.getId()));
                page = new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize());
                page.setRecords(srpmsProjectMapper.selectMyPage(page, queryForm));
            }
        }


        queryForm.setTableFlag(tableFlg);

        JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(page));
        JSONArray jsonArray = json.getJSONArray("records");

        JSONArray jsonOptions = (JSONArray) redisTemplate.opsForValue().get("sysDict_" + SrpmsConstant.SUBJECT_OPTIONS);
        Map<String, String> optionMap = new HashMap<>();
        if (jsonOptions != null && jsonOptions.size() > 0) {
            JSONObject jsonObject;
            JSONArray tempArr;
            for (int i = 0; i < jsonOptions.size(); i++) {
                jsonObject = jsonOptions.getJSONObject(i);
                optionMap.put(jsonObject.getString("id"), jsonObject.getString("label"));
                tempArr = JSONArray.parseArray(jsonObject.getString("children"));
                if (tempArr != null && tempArr.size() > 0) {
                    for (int j = 0; j < tempArr.size(); j++) {
                        jsonObject = tempArr.getJSONObject(j);
                        optionMap.put(jsonObject.getString("id"), jsonObject.getString("label"));
                    }
                }
            }
        }

        Map<String, String> dictMap = sysDictService.getSysDictTranLong(SrpmsConstant.PRO_CATEGORY);

        List<Long> ids = Lists.newArrayList();
        JSONArray optionsArr;
        for (int i = 0; i < jsonArray.size(); i++) {

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONConvert.convertJson(jsonObject);
            if (jsonObject.get("leadPerson") != null && JSONObject.isValid(jsonObject.getString("leadPerson"))) {
                jsonObject.put("applyPerson", jsonObject.getJSONObject("leadPerson").getString("name"));
            }
            if (jsonObject.get("leadDept") != null && JSONObject.isValid(jsonObject.getString("leadDept"))) {
                jsonObject.put("applyOrg", jsonObject.getJSONObject("leadDept").getString("deptName"));
            }
            if (dictMap != null) {
                if (StringUtils.isNotBlank(jsonObject.getString("projectType"))) {
                    jsonObject.put("projectTypeName", dictMap.get(jsonObject.getString("projectType")));
                }
            }
            if (SrpmsConstant.PROJECT_TYPE_1.equals(jsonObject.getString("projectFlag"))) {
                jsonObject.put("projectFlag", jsonObject.getString("projectFlag"));
            } else {
                jsonObject.put("projectFlag", "");
                if (optionMap != null && optionMap.size() > 0 && StringUtils.isNotBlank(jsonObject.getString("subjectCategory"))) {
                    optionsArr = JSONArray.parseArray(jsonObject.getString("subjectCategory"));
                    if (optionsArr != null && optionsArr.size() > 0) {
                        if (optionsArr.size() == 1) {
                            jsonObject.put("subjectCategory", optionMap.get(optionsArr.get(0)));
                        } else {
                            jsonObject.put("subjectCategory", String.join("/", optionMap.get(optionsArr.get(0)), optionMap.get(optionsArr.get(1))));
                        }
                    }
                }
            }
            ids.add(jsonObject.getLong("id"));
        }

        //补充中期绩效报告状态信息
        if (CollectionUtils.isNotEmpty(ids)) {
            List<MprEvaBaseInfo> baseInfoList = (List<MprEvaBaseInfo>) mprEvaBaseInfoService.listByIds(ids);
            Map<String, String> statusMap = Maps.newHashMap();
            for (MprEvaBaseInfo info : baseInfoList) {
                statusMap.put(info.getId() + "-" + info.getReportType(), info.getProcessStatus());
            }
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONConvert.convertJson(jsonObject);
                if (StringUtils.isNotBlank(MapUtils.getString(statusMap, jsonObject.getLong("id") + "-" + ReportTypeEnum.MPR_REPORT.getCode()))) {
                    jsonObject.put("mprProcessStatus", MapUtils.getString(statusMap, jsonObject.getLong("id") + "-" + ReportTypeEnum.MPR_REPORT.getCode()));
                } else {
                    jsonObject.put("mprProcessStatus", EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode());
                }
                if (StringUtils.isNotBlank(MapUtils.getString(statusMap, jsonObject.getLong("id") + "-" + ReportTypeEnum.YEAR_REPORT.getCode()))) {
                    jsonObject.put("yearProcessStatus", MapUtils.getString(statusMap, jsonObject.getLong("id") + "-" + ReportTypeEnum.YEAR_REPORT.getCode()));
                } else {
                    jsonObject.put("yearProcessStatus", EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode());
                }
                if (StringUtils.isNotBlank(MapUtils.getString(statusMap, jsonObject.getLong("id") + "-" + ReportTypeEnum.MID_REPORT.getCode()))) {
                    jsonObject.put("midProcessStatus", MapUtils.getString(statusMap, jsonObject.getLong("id") + "-" + ReportTypeEnum.MID_REPORT.getCode()));
                } else {
                    jsonObject.put("midProcessStatus", EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode());
                }
                if (StringUtils.isNotBlank(MapUtils.getString(statusMap, jsonObject.getLong("id") + "-" + ReportTypeEnum.OTHER.getCode()))) {
                    jsonObject.put("otherProcessStatus", MapUtils.getString(statusMap, jsonObject.getLong("id") + "-" + ReportTypeEnum.OTHER.getCode()));
                } else {
                    jsonObject.put("otherProcessStatus", EnumMprProcessStatus.MPR_NOT_SUBMIT.getCode());
                }

            }
        }
        json.put("tableFlag", queryForm.getTableFlag());
        return json;
    }


    public QueryWrapper<SrpmsProject> getQueryWrapper(QueryWrapper<SrpmsProject> queryWrapper, SrpmsProjectQueryForm srpmsProject) {
        //条件拼接
        if (CheckUtils.notEmpty(srpmsProject.getProjectNum())) {
            queryWrapper.like(SrpmsProject.PROJECT_NUM, srpmsProject.getProjectNum());
        }
        if (CheckUtils.notEmpty(srpmsProject.getProjectName())) {
            queryWrapper.like(SrpmsProject.PROJECT_NAME, srpmsProject.getProjectName());
        }
        if (CheckUtils.notEmpty(srpmsProject.getApplyYear())) {
            queryWrapper.like(SrpmsProject.APPLY_YEAR, srpmsProject.getApplyYear());
        }
        if (CheckUtils.notEmpty(srpmsProject.getSubjectCategory())) {
            queryWrapper.like(SrpmsProject.SUBJECT_CATEGORY, srpmsProject.getSubjectCategory());
        }
        if (CheckUtils.notEmpty(srpmsProject.getLeadDeptId())) {
            queryWrapper.eq(SrpmsProject.LEAD_DEPT_ID, srpmsProject.getLeadDeptId());
        }
        if (CheckUtils.notEmpty(srpmsProject.getProjectRole())) {
            queryWrapper.like(SrpmsProject.PROJECT_ROLE, srpmsProject.getProjectRole());
        }
        if (CheckUtils.notEmpty(srpmsProject.getProjectCategory())) {
            queryWrapper.eq(SrpmsProject.PROJECT_TYPE, srpmsProject.getProjectCategory());
        }
        if (CheckUtils.notEmpty(srpmsProject.getProjectType())) {
            if (!srpmsProject.getProjectType().contains(",")) {
                queryWrapper.eq(SrpmsProject.PROJECT_TYPE, srpmsProject.getProjectType());
            } else {
                String[] arr = srpmsProject.getProjectType().split(",");
                queryWrapper.in(SrpmsProject.PROJECT_TYPE, arr);
            }
        }
        if (CheckUtils.notEmpty(srpmsProject.getAplNum())) {
            queryWrapper.eq(SrpmsProject.APL_NUM, srpmsProject.getAplNum());
        }
        if (CheckUtils.notEmpty(srpmsProject.getApdNum())) {
            queryWrapper.eq(SrpmsProject.APD_NUM, srpmsProject.getApdNum());
        }
        if (CheckUtils.notEmpty(srpmsProject.getPudNum())) {
            queryWrapper.eq(SrpmsProject.PUD_NUM, srpmsProject.getPudNum());
        }

        if (CheckUtils.notEmpty(srpmsProject.getStatus())) {
            if (!srpmsProject.getStatus().contains(",")) {
                queryWrapper.eq(SrpmsProject.STATUS, srpmsProject.getStatus());
            } else {
                String[] arr = srpmsProject.getStatus().split(",");
                queryWrapper.in(SrpmsProject.STATUS, arr);
            }
        }

        if (srpmsProject.getStatusArr() != null && srpmsProject.getStatusArr().length > 0) {
            queryWrapper.notIn(SrpmsProject.STATUS, srpmsProject.getStatusArr());
        }

        if (CheckUtils.notEmpty(srpmsProject.getLeadPersonId())) {
            queryWrapper.eq(SrpmsProject.LEAD_PERSON_ID, srpmsProject.getLeadPersonId());
        }
        if (SrpmsProjectStatusEnums.PEROJECT_HAS_PUBLICITY.getCode().equals(srpmsProject.getStatus())) {
            queryWrapper.orderByDesc(SrpmsProject.PUBLIC_TIME);
        } else if (SrpmsProjectStatusEnums.PEROJECT_SET_UP.getCode().equals(srpmsProject.getStatus())) {
            queryWrapper.orderByDesc(SrpmsProject.APPROVE_TIME);
        } else {
            queryWrapper.orderByDesc(SrpmsProject.UPDATE_TIME);
        }
        if (CheckUtils.notEmpty(srpmsProject.getProjectFlag())) {// 项目标识条件
            queryWrapper.eq(SrpmsProject.PROJECT_FLAG, srpmsProject.getProjectFlag());
        }
        queryWrapper.ne(SrpmsProject.DEL_FLG, '1');
        return queryWrapper;
    }
}

