package com.deloitte.services.project.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.project.param.ReviewQueryForm;
import com.deloitte.platform.api.project.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.common.enums.ValueEnums;
import com.deloitte.services.project.common.enums.VoucherTypeEnums;
import com.deloitte.services.project.entity.*;
import com.deloitte.services.project.mapper.ReviewMapper;
import com.deloitte.services.project.service.*;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  Review服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements IReviewService {


    @Autowired
    private ReviewMapper reviewMapper;

    @Autowired
    public IProjectsService projectsService;

    @Autowired
    public IApplicationService applicationService;

    @Autowired
    private ISubactService subactService;

    @Autowired
    private IBudgetService budgetService;

    @Autowired
    private IPerformanceService performanceService;

    @Autowired
    private IReviewNoteService reviewNoteService;

    @Autowired
    private IExpertService expertService;

    @Autowired
    private IEnclosureService enclosureService;

    @Autowired
    public ICommonService commonService;

    @Override
    public IPage<Review> selectPage(ReviewQueryForm queryForm ) {
        QueryWrapper<Review> queryWrapper = new QueryWrapper <Review>();
        //getQueryWrapper(queryWrapper,queryForm);
        return reviewMapper.selectPage(new Page<Review>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<Review> selectList(ReviewQueryForm queryForm) {
        QueryWrapper<Review> queryWrapper = new QueryWrapper <Review>();
        //getQueryWrapper(queryWrapper,queryForm);
        return reviewMapper.selectList(queryWrapper);
    }

    @Override
    public List<Review> getList(String applicationId) {
        QueryWrapper<Review> queryWrapper = new QueryWrapper <Review>();
        queryWrapper.eq(Review.APPLICATION_ID, applicationId);
        return reviewMapper.selectList(queryWrapper);
    }

    @Override
    public ProjectReviewVo toUpdate(ProjectReviewVo projectReviewVo) {
        ProjectReviewVo vo = new ProjectReviewVo();
        String applicationId =  projectReviewVo.getApplicationId();
        String projectId =  projectReviewVo.getProjectId();
        Projects projects = projectsService.getById(projectId);
        //评审事件
        String keyCode = projectReviewVo.getReviewCode();
        //申报状态
        String applicationState = null;
        String applicationName = "";
        //项目状态
        String projectState = null;
        String projectName = "";
        //评审状态
        String reviewCode = null;
        String reviewName = "";

        //调整-->通过-->拒绝
        if("6002".equals(keyCode)){
            reviewCode = ValueEnums.REVIEW_ADJUESTED.getCode();
            reviewName = ValueEnums.REVIEW_ADJUESTED.getValue();
            //待调整
            applicationState = ValueEnums.APPLICATION_ADJUESTED.getCode();
            applicationName = ValueEnums.APPLICATION_ADJUESTED.getValue();
            //项目状态
            projectState = ValueEnums.PROJECT_DECLAARED.getCode();
            projectName = ValueEnums.PROJECT_DECLAARED.getValue();
        }  else if("6003".equals(keyCode)){
            reviewCode = ValueEnums.REVIEW_HAVE_PASSED.getCode();
            reviewName = ValueEnums.REVIEW_HAVE_PASSED.getValue();
            //待批复-->已通过
            applicationState = ValueEnums.APPLICATION_HAVE_PASSED.getCode();
            applicationName = ValueEnums.APPLICATION_HAVE_PASSED.getValue();
            //项目状态
            projectState = ValueEnums.PROJECT_REPLIED.getCode();
            projectName = ValueEnums.PROJECT_REPLIED.getValue();
        } else if("6004".equals(keyCode)){
            reviewCode = ValueEnums.REVIEW_REJECTED.getCode();
            reviewName = ValueEnums.REVIEW_REJECTED.getValue();
            //已拒绝
            applicationState = ValueEnums.APPLICATION_REJECTED.getCode();
            applicationName = ValueEnums.APPLICATION_REJECTED.getValue();
            //项目状态
            projectState = ValueEnums.PROJECT_CANCELLED.getCode();
            projectName = ValueEnums.PROJECT_CANCELLED.getValue();
        }

        //项目
        if(!"6001".equals(keyCode)) {
            //只有已申报的项目状态才改变，其他不做处理
            if(projects.getProjectStatusCode().equals("7002")) {
                projects.setProjectStatusCode(projectState);
                projects.setProjectStatusName(projectName);
                projectsService.updateById(projects);
            }
            ProjectsVo projectsVo = new BeanUtils<ProjectsVo>().copyObj(projects,ProjectsVo.class);
            vo.setProjectsVo(projectsVo);
        }

        //申报书
        Application application = applicationService.getById(applicationId);
        if(ObjectUtils.allNotNull(application)){
            if(!"6001".equals(keyCode)) {
                application.setAppStateCode(applicationState);
                application.setAppStateName(applicationName);
                applicationService.updateById(application);
            }
        }

        //保存更新支出评审金额
        List<SubactVo> subactVoList = projectReviewVo.getSubActVos();
        for (SubactVo subactVo : subactVoList) {
            Subact subact=new BeanUtils<Subact>().copyObj(subactVo,Subact.class);
            subact.setId(NumberUtils.toLong(subactVo.getId()));
            subactService.updateById(subact);
        }

        //预算
        List<Budget> budgets = budgetService.getAppBudgetList(applicationId);
        if(budgets.size() >0) {
            budgetService.deleteByApp(applicationId);
        }

        JSONArray jsonArray =  projectReviewVo.getBudgetJsonArray();
        for(int i = 0; i<jsonArray.size(); i++) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            JSONArray jsonArray1 = JSONObject.parseArray(JSON.toJSONString(jsonObject.get("data")));
            for(int j=0;j<jsonArray1.size();j++){
                Budget budget = JSONObject.parseObject(JSON.toJSONString(jsonArray1.getJSONObject(j)), Budget.class);
                budgetService.save(budget);
            }
        }

        //评审表
        ReviewVo reviewvo = projectReviewVo.getReviewVo();
        List<Review> reviews = this.getList(applicationId);
        String reviewId = "";
        for (Review review : reviews) {
            reviewId = String.valueOf(review.getId());
            if(!"6001".equals(keyCode)) {
                review.setReviewStatusCode(reviewCode);
                review.setReviewStatusName(reviewName);
            }
            review.setSchoolPriority(reviewvo.getSchoolPriority());
            review.setFirstLevelProject(reviewvo.getFirstLevelProject());
            review.setReviewAdvice(reviewvo.getReviewAdvice());
            if(StringUtils.isEmpty(review.getServiceNum())){
                String serNum = application.getServiceNum().replaceAll("APL","REV");
                review.setServiceNum(serNum);
            }
            this.updateById(review);

            ReviewVo reviewVo = new BeanUtils<ReviewVo>().copyObj(review,ReviewVo.class);
            vo.setReviewVo(reviewVo);
        }

        //附件
        JSONArray jsonArr = projectReviewVo.getRevFileJsonArr();
        if(jsonArr.size() > 0) {
            List<Enclosure> enclosures = enclosureService.selectListByRevId(reviewId);
            if(enclosures.size() > 0) {
                enclosureService.delListByRev(reviewId);
            }
            for(int i=0;i<jsonArr.size();i++) {
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(jsonArr.get(i)));
                Enclosure enclosure = new Enclosure();
                enclosure.setEnclosureName(jsonObject.getString("fileName"));
                enclosure.setEnclosureType(jsonObject.getString("fileExt"));
                enclosure.setEnclosureId(jsonObject.getString("id"));
                enclosure.setEnclosureUrl(jsonObject.getString("fileUrl"));
                enclosure.setReviewId(reviewId);
                enclosureService.save(enclosure);
            }
        }

        //评审专家列表
        List<Expert> experts = expertService.getList(applicationId);
        if(experts.size() > 0) {
            expertService.deleteList(applicationId);
        }
        List<ExpertVo> expertVos =  projectReviewVo.getExpertVoList();
        for (ExpertVo expertVo : expertVos) {
            Expert expert = new BeanUtils<Expert>().copyObj(expertVo, Expert.class);

            expert.setApplicationId(applicationId);
            expert.setProjectId(projectId);
            expertService.save(expert);
        }

        //审批人意见 测试保存时就生成
        if(!"6001".equals(keyCode)) {
            ReviewNoteVo reviewNoteVo = new ReviewNoteVo();
            reviewNoteVo.setApplicationId(applicationId);
            reviewNoteVo.setReviewMan("张三");
            reviewNoteVo.setReviewOpi(reviewvo.getReviewAdvice());
            reviewNoteVo.setReviewDate(LocalDateTime.now());
            ReviewNote reviewNote = new BeanUtils<ReviewNote>().copyObj(reviewNoteVo,ReviewNote.class);
            reviewNoteService.save(reviewNote);
        }
//        //发起流程
//        if("6002".equals(keyCode)) {
//            Map processVariables = new HashMap();
//            projects.getProjectTypeCode();
//            UserVo userVo = commonService.getCurrentUser();
//            userVo.setName(projects.getProjectHeaderName());
//            userVo.setId(projects.getProjectHeaderId());
//            commonService.startAuditProcessByAccept(reviewId,processVariables,VoucherTypeEnums.PROJECT_ADJUST_PROCESS,userVo);
//        }
        return vo;
    }


}

