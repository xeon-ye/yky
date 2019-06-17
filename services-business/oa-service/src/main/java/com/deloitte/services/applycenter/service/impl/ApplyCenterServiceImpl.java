package com.deloitte.services.applycenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.feign.ResourceFeignService;
import com.deloitte.platform.api.isump.vo.DeputyAccountVo;
import com.deloitte.platform.api.isump.vo.ResourceVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.applycenter.param.ApplyCenterQueryForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.ApplyCenterForm;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.ServiceException;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.applycenter.entity.ApplyCenter;
import com.deloitte.services.applycenter.mapper.ApplyCenterMapper;
import com.deloitte.services.applycenter.service.IApplyCenterService;
import com.deloitte.services.oa.exception.OaErrorType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-04-12
 * @Description :  ApplyCenter服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ApplyCenterServiceImpl extends ServiceImpl<ApplyCenterMapper, ApplyCenter> implements IApplyCenterService {

    @Autowired
    private ResourceFeignService resourceFeignService;

    @Autowired
    private ApplyCenterMapper applyCenterMapper;

    @Override
    public Result save(ApplyCenterForm applyCenterForm) {
        ApplyCenter applyCenter = new BeanUtils<ApplyCenter>().copyObj(applyCenterForm, ApplyCenter.class);
        ApplyCenterQueryForm applyCenterQueryForm = new ApplyCenterQueryForm();
        List<ApplyCenter> applyCenters = this.selectList(applyCenterQueryForm);
        this.save(applyCenter);
        for (ApplyCenter apply : applyCenters) {
            if (applyCenterForm.getApplySort().equals(apply.getApplySort())) {
                Integer applySort = Integer.valueOf(apply.getApplySort());
                apply.setApplySort(String.valueOf(++applySort));
                this.updateById(apply);
                applyCenterForm.setApplySort(String.valueOf(applySort));
            }
        }
        return Result.success();
    }

    @Override
    public GdcPage<ApplyCenter> selectPage(ApplyCenterQueryForm queryForm) {
        QueryWrapper<ApplyCenter> queryWrapper = new QueryWrapper<ApplyCenter>();
        queryWrapper.eq(ApplyCenter.IS_VISIABLE, "1");
        if (StringUtils.isNotBlank(queryForm.getApplyCode())) {
            queryWrapper.eq(ApplyCenter.APPLY_CODE, queryForm.getApplyCode());
        }
        queryWrapper.apply(" 1=1 ORDER BY TO_NUMBER(APPLY_SORT) ");
        List<ApplyCenter> applyCenterList = applyCenterMapper.selectList(queryWrapper);

        UserVo userVo = UserUtil.getUserVo();
        Long id;
        if (userVo != null) {
            DeputyAccountVo accountVo = userVo.getCurrentDeputyAccount();
            if (accountVo != null) {
                id = accountVo.getId();
            } else {
                throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取登录用户信息失败！");
            }
        } else {
            throw new ServiceException(OaErrorType.USERINFO_ERROR, "获取登录用户信息失败！");
        }
        List<ApplyCenter> applyCenters = new ArrayList<>();
        for (ApplyCenter applyCenter : applyCenterList) {
            Result<ResourceVo> tree = resourceFeignService.tree(id, applyCenter.getSystemCode());
            ResourceVo data = JSONObject.parseObject(JSON.toJSONString(tree.getData()),ResourceVo.class);
            if (data.getChildren() != null) {
                applyCenters.add(applyCenter);
            }
        }
        GdcPage<ApplyCenter> gdcPage = new GdcPage<>();
        gdcPage.setTotal(applyCenters.size());
        gdcPage.setPageNo(queryForm.getCurrentPage());
        gdcPage.setPageSize(queryForm.getPageSize());
        int fromIndex = (gdcPage.getPageNo() - 1) * gdcPage.getPageSize();
        int toIndex = gdcPage.getPageNo() * gdcPage.getPageSize();
        if (toIndex >= applyCenters.size()) {
            toIndex = applyCenters.size();
        }
        List<ApplyCenter> pageList = applyCenters.subList(fromIndex, toIndex);
        gdcPage.setContent(pageList);
        return gdcPage;

    }

    @Override
    public List<ApplyCenter> selectList(ApplyCenterQueryForm queryForm) {
        QueryWrapper<ApplyCenter> queryWrapper = new QueryWrapper<ApplyCenter>();
        queryWrapper.apply(" 1=1 ORDER BY TO_NUMBER(APPLY_SORT) ");
//        getQueryWrapper(queryWrapper, queryForm);
        return applyCenterMapper.selectList(queryWrapper);
    }

    /**
     * 通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<ApplyCenter> getQueryWrapper(QueryWrapper<ApplyCenter> queryWrapper, ApplyCenterQueryForm queryForm) {
        //条件拼接
        if (StringUtils.isNotBlank(queryForm.getPicUrl())) {
            queryWrapper.eq(ApplyCenter.PIC_URL, queryForm.getPicUrl());
        }
        if (StringUtils.isNotBlank(queryForm.getApplyName())) {
            queryWrapper.like(ApplyCenter.APPLY_NAME, queryForm.getApplyName());
        }
        if (StringUtils.isNotBlank(queryForm.getApplySort())) {
            queryWrapper.eq(ApplyCenter.APPLY_SORT, queryForm.getApplySort());
        }
//        if (StringUtils.isNotBlank(queryForm.getIsVisiable())) {
//            queryWrapper.eq(ApplyCenter.IS_VISIABLE, queryForm.getIsVisiable());
//        }
//        if (StringUtils.isNotBlank(String.valueOf(queryForm.getApplyDatetime()))) {
//            queryWrapper.eq(ApplyCenter.APPLY_DATETIME, queryForm.getApplyDatetime());
//        }
//        if (StringUtils.isNotBlank(String.valueOf(queryForm.getApplyUpdatetime()))) {
//            queryWrapper.eq(ApplyCenter.APPLY_UPDATETIME, queryForm.getApplyUpdatetime());
//        }
        if (StringUtils.isNotBlank(queryForm.getTypeName())) {
            queryWrapper.eq(ApplyCenter.TYPE_NAME, queryForm.getTypeName());
        }
        if (StringUtils.isNotBlank(queryForm.getDelFlag())) {
            queryWrapper.eq(ApplyCenter.DEL_FLAG, queryForm.getDelFlag());
        }
        if (StringUtils.isNotBlank(queryForm.getApplyCode())) {
            queryWrapper.eq(ApplyCenter.APPLY_CODE, queryForm.getApplyCode());
        }
        if (StringUtils.isNotBlank(queryForm.getApplyUrl())) {
            queryWrapper.eq(ApplyCenter.APPLY_URL, queryForm.getApplyUrl());
        }
        queryWrapper.eq(ApplyCenter.IS_VISIABLE, "1");
        queryWrapper.apply(" 1=1 ORDER BY TO_NUMBER(APPLY_SORT) ");
        return queryWrapper;
    }

}

