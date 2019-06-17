package com.deloitte.services.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.project.param.ServiceNumQueryForm;
import com.deloitte.services.project.common.enums.ProjectErrorType;
import com.deloitte.services.project.common.util.AssertUtils;
import com.deloitte.services.project.common.util.ConstantUtils;
import com.deloitte.services.project.common.util.DateUtil;
import com.deloitte.services.project.common.util.GeneralBuisnessNoUtils;
import com.deloitte.services.project.entity.Application;
import com.deloitte.services.project.entity.Projects;
import com.deloitte.services.project.entity.Reply;
import com.deloitte.services.project.entity.ServiceNum;
import com.deloitte.services.project.mapper.ServiceNumMapper;
import com.deloitte.services.project.service.IApplicationService;
import com.deloitte.services.project.service.IProjectsService;
import com.deloitte.services.project.service.IReplyService;
import com.deloitte.services.project.service.IServiceNumService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-17
 * @Description :  ServiceNum服务实现类
 * @Modified :
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ServiceNumServiceImpl extends ServiceImpl<ServiceNumMapper, ServiceNum> implements IServiceNumService {


    @Autowired
    private ServiceNumMapper serviceNumMapper;

    @Autowired
    private IProjectsService projectsService;

    @Autowired
    private IApplicationService applicationService;

    @Autowired
    private IReplyService replyService;


    @Override
    public IPage<ServiceNum> selectPage(ServiceNumQueryForm queryForm ) {
        QueryWrapper<ServiceNum> queryWrapper = new QueryWrapper <ServiceNum>();
        //getQueryWrapper(queryWrapper,queryForm);
        return serviceNumMapper.selectPage(new Page<ServiceNum>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public int getMaxNum(String serviceOnly) {
        return serviceNumMapper.getMaxNum(serviceOnly);
    }

    @Override
    public List<ServiceNum> selectList(ServiceNumQueryForm queryForm) {
        QueryWrapper<ServiceNum> queryWrapper = new QueryWrapper <ServiceNum>();
        //getQueryWrapper(queryWrapper,queryForm);
        return serviceNumMapper.selectList(queryWrapper);
    }

    @Override
    public String generalBusinessNO(String businessCode) {
        AssertUtils.asserts(Objects.isNull(businessCode), ProjectErrorType.DATA_IS_NULL);
        // 生成规则： 类型+单位编码+年份+流水
        String busNO = null;
        switch (businessCode) {
            case ConstantUtils.PREFIX_APL:
                busNO = getBusNo(ConstantUtils.PREFIX_APL);
                break;
            case ConstantUtils.PREFIX_BUP:
                busNO = getBusNo(ConstantUtils.PREFIX_BUP);
                break;
            case ConstantUtils.PREFIX_CAN:
                busNO = getBusNo(ConstantUtils.PREFIX_CAN);
                break;
            case ConstantUtils.PREFIX_CHR:
                busNO = getBusNo(ConstantUtils.PREFIX_CHR);
                break;
            case ConstantUtils.PREFIX_REP:
                busNO = getBusNo(ConstantUtils.PREFIX_REP);
                break;
            case ConstantUtils.PREFIX_REV:
                busNO = getBusNo(ConstantUtils.PREFIX_REV);
                break;
            default:
                break;
        }
        return busNO;
    }

    private String getBusNo(String businessCode) {
        String busNO = GeneralBuisnessNoUtils.generalBusNO(businessCode);
        return busNO;
    }

    @Override
    public String toSaveNum(String projectId, String applicationId) {
        Projects projects = projectsService.getById(projectId);
        String organizationId = projects.getOrganizationId();
        String serviceOnly = "APL" + "" + organizationId + "" + DateUtil.getCurrentYear();

        QueryWrapper<ServiceNum> queryWrapper = new QueryWrapper <ServiceNum>();
        queryWrapper.eq(ServiceNum.SERVICE_ONLY,serviceOnly);
        ServiceNum serviceNum = serviceNumMapper.selectOne(queryWrapper);

        Application application = applicationService.getById(applicationId);

        if(Objects.isNull(serviceNum)) {
            ServiceNum serviceNum1 = new ServiceNum();
            serviceNum1.setApplicationId(applicationId);
            serviceNum1.setProjectId(projectId);
            serviceNum1.setServiceOnly(serviceOnly);
            serviceNum1.setNum(1);
            serviceNum1.setCurYear(NumberUtils.toLong(String.valueOf(DateUtil.getCurrentYear())));
            serviceOnly += "001";
            serviceNum1.setApplyNum(serviceOnly);
            serviceNumMapper.insert(serviceNum1);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("serviceOnly",serviceOnly);
            ServiceNum serviceNum1 = serviceNumMapper.selectMaxNum(map);

            ServiceNum serviceNum2 = new ServiceNum();
            int num = serviceNum1.getNum();
            num += 1;
            serviceOnly += String.format("%03d",num);
            serviceNum2.setApplyNum(serviceOnly);
            serviceNum2.setNum(num);
            serviceNum2.setApplicationId(serviceNum1.getApplicationId());
            serviceNumMapper.insert(serviceNum2);

        }
        application.setServiceNum(serviceOnly);
        applicationService.updateById(application);
        return serviceOnly;
    }

    @Override
    public String replySerNum(String projectId) {
        Projects projects = projectsService.getById(projectId);
        String organizationId = projects.getOrganizationId();
        String serviceOnly = "REP" + "" + organizationId + "" + DateUtil.getCurrentYear();
        QueryWrapper<ServiceNum> queryWrapper = new QueryWrapper <ServiceNum>();
        queryWrapper.eq(ServiceNum.SERVICE_ONLY,serviceOnly);
        ServiceNum serviceNum = serviceNumMapper.selectOne(queryWrapper);

        Reply reply = replyService.getAllListByRep(projectId).get(0);

        if(Objects.isNull(serviceNum)) {
            ServiceNum serviceNum1 = new ServiceNum();
            serviceNum1.setProjectId(projectId);
            serviceNum1.setServiceOnly(serviceOnly);
            serviceNum1.setNum(1);
            serviceNum1.setCurYear(NumberUtils.toLong(String.valueOf(DateUtil.getCurrentYear())));
            serviceOnly += "001";
            serviceNum1.setReplyNum(serviceOnly);
            serviceNumMapper.insert(serviceNum1);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("serviceOnly",serviceOnly);
            ServiceNum serviceNum1 = serviceNumMapper.selectMaxNum(map);

            ServiceNum serviceNum2 = new ServiceNum();
            int num = serviceNum1.getNum();
            num += 1;
            serviceOnly += String.format("%03d",num);
            serviceNum2.setReplyNum(serviceOnly);
            serviceNum2.setNum(num);
            serviceNum2.setApplicationId(serviceNum1.getApplicationId());
            serviceNumMapper.insert(serviceNum2);
        }

        reply.setServiceNum(serviceOnly);
        replyService.updateById(reply);
        return serviceOnly;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ServiceNum> getQueryWrapper(QueryWrapper<ServiceNum> queryWrapper,ServiceNumQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getProjectId())){
            queryWrapper.eq(ServiceNum.PROJECT_ID,queryForm.getProjectId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplicationId())){
            queryWrapper.eq(ServiceNum.APPLICATION_ID,queryForm.getApplicationId());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyNum())){
            queryWrapper.eq(ServiceNum.APPLY_NUM,queryForm.getApplyNum());
        }
        if(StringUtils.isNotBlank(queryForm.getApplyCancelNum())){
            queryWrapper.eq(ServiceNum.APPLY_CANCEL_NUM,queryForm.getApplyCancelNum());
        }
        if(StringUtils.isNotBlank(queryForm.getReviewNum())){
            queryWrapper.eq(ServiceNum.REVIEW_NUM,queryForm.getReviewNum());
        }
        if(StringUtils.isNotBlank(queryForm.getReplyNum())){
            queryWrapper.eq(ServiceNum.REPLY_NUM,queryForm.getReplyNum());
        }
        if(StringUtils.isNotBlank(queryForm.getChangeNum())){
            queryWrapper.eq(ServiceNum.CHANGE_NUM,queryForm.getChangeNum());
        }
        if(StringUtils.isNotBlank(queryForm.getBussNum())){
            queryWrapper.eq(ServiceNum.BUSS_NUM,queryForm.getBussNum());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(ServiceNum.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(ServiceNum.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(ServiceNum.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(ServiceNum.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getExt1())){
            queryWrapper.eq(ServiceNum.EXT1,queryForm.getExt1());
        }
        if(StringUtils.isNotBlank(queryForm.getExt2())){
            queryWrapper.eq(ServiceNum.EXT2,queryForm.getExt2());
        }
        if(StringUtils.isNotBlank(queryForm.getExt3())){
            queryWrapper.eq(ServiceNum.EXT3,queryForm.getExt3());
        }
        return queryWrapper;
    }
     */
}

