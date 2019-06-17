package com.deloitte.services.project.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskForm;
import com.deloitte.platform.api.project.client.ReviewClient;
import com.deloitte.platform.api.project.param.ReviewQueryForm;
import com.deloitte.platform.api.project.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.project.common.enums.VoucherTypeEnums;
import com.deloitte.services.project.entity.*;
import com.deloitte.services.project.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :   Review控制器实现类
 * @Modified :
 */
@Api(tags = "3-Review操作接口")
@Slf4j
@RestController
public class ReviewController implements ReviewClient {

    @Autowired
    public IReviewService  reviewService;

    @Autowired
    public IProjectsService projectsService;

    @Autowired
    public IApplicationService applicationService;

    @Autowired
    private IActService actService;

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
    public ICommonService commonService;
    @Autowired
    private ICarbonCopyService carbonCopyService;
    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private IEnclosureService enclosureService;

    @Override
    @ApiOperation(value = "新增Review", notes = "新增一个Review")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="reviewForm",value="新增Review的form表单",required=true)  ReviewForm reviewForm) {
        log.info("form:",  reviewForm.toString());
        Review review =new BeanUtils<Review>().copyObj(reviewForm,Review.class);
        return Result.success( reviewService.save(review));
    }


    @Override
    @ApiOperation(value = "删除Review", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ReviewID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        reviewService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Review", notes = "修改指定Review信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Review的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="reviewForm",value="修改Review的form表单",required=true)  ReviewForm reviewForm) {
        Review review =new BeanUtils<Review>().copyObj(reviewForm,Review.class);
        review.setId(id);
        reviewService.saveOrUpdate(review);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Review", notes = "获取指定ID的Review信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Review的ID", required = true, dataType = "long")
    public Result<ReviewVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Review review=reviewService.getById(id);
        ReviewVo reviewVo=new BeanUtils<ReviewVo>().copyObj(review,ReviewVo.class);
        return new Result<ReviewVo>().sucess(reviewVo);
    }

    @Override
    @ApiOperation(value = "列表查询Review", notes = "根据条件查询Review列表数据")
    public Result<List<ReviewVo>> list(@Valid @RequestBody @ApiParam(name="reviewQueryForm",value="Review查询参数",required=true) ReviewQueryForm reviewQueryForm) {
        log.info("search with reviewQueryForm:", reviewQueryForm.toString());
        List<Review> reviewList=reviewService.selectList(reviewQueryForm);
        List<ReviewVo> reviewVoList=new BeanUtils<ReviewVo>().copyObjs(reviewList,ReviewVo.class);
        return new Result<List<ReviewVo>>().sucess(reviewVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Review", notes = "根据条件查询Review分页数据")
    public Result<IPage<ReviewVo>> search(@Valid @RequestBody @ApiParam(name="reviewQueryForm",value="Review查询参数",required=true) ReviewQueryForm reviewQueryForm) {
        log.info("search with reviewQueryForm:", reviewQueryForm.toString());
        IPage<Review> reviewPage=reviewService.selectPage(reviewQueryForm);
        IPage<ReviewVo> reviewVoPage=new BeanUtils<ReviewVo>().copyPageObjs(reviewPage,ReviewVo.class);
        return new Result<IPage<ReviewVo>>().sucess(reviewVoPage);
    }

    @Override
    @ApiOperation(value = "评审跳转", notes = "评审跳转")
    @ApiImplicitParam(paramType = "path", name = "applicationId", value = "申报书的ID", required = true, dataType = "string")
    public Result<ProjectReviewVo> getOneApp(@PathVariable String applicationId) {
        ProjectReviewVo vo = new ProjectReviewVo();
        //申报书
        Application application = applicationService.getById(applicationId);
        Projects projects = projectsService.getById(application.getProjectId());
        ApplicationVo applicationVo = new BeanUtils<ApplicationVo>().copyObj(application, ApplicationVo.class);
        vo.setApplicationId(applicationId);
        vo.setApplicationVo(applicationVo);

        ProjectsVo projectsVo = new BeanUtils<ProjectsVo>().copyObj(projects, ProjectsVo.class);
        String projectId = String.valueOf(projects.getId());
        vo.setProjectId(projectId);
        vo.setProjectsVo(projectsVo);

        String reviewId = "";
        //评审表
        List<Review> reviewList = reviewService.getList(applicationId);
        if(reviewList.size() > 0) {
            reviewId = String.valueOf(reviewList.get(0).getId());
            ReviewVo reviewVo = new BeanUtils<ReviewVo>().copyObj(reviewList.get(0),ReviewVo.class);
            vo.setReviewVo(reviewVo);
            //评审专家列表
            List<Expert> experts = expertService.getList(applicationId);
            List<ExpertVo> expertVos = new BeanUtils<ExpertVo>().copyObjs(experts, ExpertVo.class);
            vo.setExpertVoList(expertVos);
        } else {
            Review review = new Review();
            review.setApplicationId(applicationId);
            reviewService.save(review);
        }

        //支出
        List<Act> actList =  actService.getActList(applicationId);
        if(actList.size() > 0) {
            List<SubactVo> subactVoList = new ArrayList<>();
            for(Act act: actList){
                List<Subact> subacts = subactService.getSubActList(act.getActId());
                for (Subact subact : subacts) {
                    SubactVo subactVo = new BeanUtils<SubactVo>().copyObj(subact,SubactVo.class);
                    subactVoList.add(subactVo);
                }
            }
            List<ActVo> actVoList = new BeanUtils<ActVo>().copyObjs(actList,ActVo.class);
            vo.setActVoList(actVoList);
            vo.setSubActVos(subactVoList);
        }

        //预算
        List list = budgetService.getAppYearCount(applicationId);
        JSONArray jsonArray = new JSONArray();
        if(list.size() > 0) {
            for(int i=0;i<list.size();i++){
                JSONObject jsonObject = new JSONObject();
                Map map = new HashMap();
                map.put("applicationId",applicationId);
                map.put("budgetYear",list.get(i));
                List<Budget> budgets = budgetService.getAppBudgetMap(map);
                jsonObject.put("year",list.get(i));
                jsonObject.put("data",budgets);
                jsonArray.add(jsonObject);
            }
            vo.setBudgetJsonArray(jsonArray);
        }

        //绩效
        List<Performance> performanceList = performanceService.getPerformanceList(applicationId);
        List<Performance> performanceIndexTypeList = performanceService.getIndexTypeListWithDistinct(applicationId);
        if (CollectionUtils.isNotEmpty(performanceList) && CollectionUtils.isNotEmpty(performanceIndexTypeList)) {
            JSONArray parentArray = new JSONArray();
            for (Performance indexType : performanceIndexTypeList) {
                JSONObject parentObject = new JSONObject();
                parentObject.put("indexType", indexType.getIndexType());
                parentObject.put("annualPerformanceTarget", indexType.getAnnualPerformanceTarget());
                JSONArray childrenProduceArray = new JSONArray();
                JSONArray childrenBenefitArray = new JSONArray();
                JSONArray childrenSatisfiedArray = new JSONArray();
                for (Performance performance : performanceList) {
                    if (performance.getIndexType().equals(indexType.getIndexType())) {
                        if ("produce".equals(performance.getIndex1st())) {
                            JSONObject childrenProduceObject = new JSONObject();
                            childrenProduceObject.put("index2NdCode", performance.getIndex2ndCode());
                            childrenProduceObject.put("index2Nd", performance.getIndex2nd());
                            childrenProduceObject.put("index3StCode", performance.getIndex3stCode());
                            childrenProduceObject.put("index3St", performance.getIndex3st());
                            childrenProduceObject.put("indexPer", performance.getIndexPer());
                            childrenProduceObject.put("per", performance.getPer());
                            childrenProduceObject.put("perCode", performance.getPerCode());
                            childrenProduceArray.add(childrenProduceObject);
                        }
                        if ("benefit".equals(performance.getIndex1st())) {
                            JSONObject childrenBenefitObject = new JSONObject();
                            childrenBenefitObject.put("index2NdCode", performance.getIndex2ndCode());
                            childrenBenefitObject.put("index2Nd", performance.getIndex2nd());
                            childrenBenefitObject.put("index3StCode", performance.getIndex3stCode());
                            childrenBenefitObject.put("index3St", performance.getIndex3st());
                            childrenBenefitObject.put("indexPer", performance.getIndexPer());
                            childrenBenefitObject.put("per", performance.getPer());
                            childrenBenefitObject.put("perCode", performance.getPerCode());
                            childrenBenefitArray.add(childrenBenefitObject);
                        }
                        if ("satisfied".equals(performance.getIndex1st())) {
                            JSONObject childrenSatisfiedObject = new JSONObject();
                            childrenSatisfiedObject.put("index2NdCode", performance.getIndex2ndCode());
                            childrenSatisfiedObject.put("index2Nd", performance.getIndex2nd());
                            childrenSatisfiedObject.put("index3StCode", performance.getIndex3stCode());
                            childrenSatisfiedObject.put("index3St", performance.getIndex3st());
                            childrenSatisfiedObject.put("indexPer", performance.getIndexPer());
                            childrenSatisfiedObject.put("per", performance.getPer());
                            childrenSatisfiedObject.put("perCode", performance.getPerCode());
                            childrenSatisfiedArray.add(childrenSatisfiedObject);
                        }
                    }
                }
                parentObject.put("produce", childrenProduceArray);
                parentObject.put("benefit", childrenBenefitArray);
                parentObject.put("satisfied", childrenSatisfiedArray);
                parentArray.add(parentObject);
            }
            vo.setPerformanceVoList(parentArray);
        }

        //评审记录表
        List<ReviewNote> reviewNoteVoList = reviewNoteService.getList(applicationId);
        if(reviewNoteVoList.size() > 0) {
            List<ReviewNoteVo> reviewNoteVos = new BeanUtils<ReviewNoteVo>().copyObjs(reviewNoteVoList, ReviewNoteVo.class);
            vo.setReviewNoteVoList(reviewNoteVos);
        }

        //附件
        List<Enclosure> enclosures = enclosureService.selectListByAppId(applicationId);
        List<EnclosureVo> enclosureVos = new BeanUtils<EnclosureVo>().copyObjs(enclosures,EnclosureVo.class);
        vo.setEnclosureVos(enclosureVos);

        JSONArray revFileJsonArr = new JSONArray();
        List<Enclosure> enclosures1 = enclosureService.selectListByRevId(reviewId);
        if(enclosures1.size() > 0) {
            for (Enclosure enclosure : enclosures1) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id",enclosure.getEnclosureId());
                jsonObject.put("fileExt",enclosure.getEnclosureType());
                jsonObject.put("fileName",enclosure.getEnclosureName());
                jsonObject.put("fileUrl",enclosure.getEnclosureUrl());
                revFileJsonArr.add(jsonObject);
            }
            vo.setRevFileJsonArr(revFileJsonArr);
        }
        return new Result<ProjectReviewVo>().sucess(vo);
    }

    @Override
    @ApiOperation(value = "提交",notes = "提交")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<ProjectReviewVo> toSubmit(@Valid @RequestBody @ApiParam(name="ProjectReviewVo",value="传入后端的值",required=true) ProjectReviewVo projectReviewVo, SendProcessTaskForm sendProcessTaskForm) {
        projectReviewVo.setReviewCode("6003");
        projectReviewVo = this.reviewService.toUpdate(projectReviewVo);
        String applicationId =   projectReviewVo.getApplicationId();
//        Result<UserVo>  result = userFeignService.get(Long.parseLong(sendProcessTaskForm.getApproverAcount()));
//        if(result.isSuccess()){
//            //给某个id 发送一条待阅
//            UserVo userVo = result.getData();
//            commonService.sendProcessTask(userVo,applicationId,VoucherTypeEnums.REVIEW_READ);
//        }
        return new Result<ProjectReviewVo>().sucess(projectReviewVo);
    }

    @Override
    @ApiOperation(value = "调整",notes = "调整")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<ProjectReviewVo> toAdjust(@Valid @RequestBody @ApiParam(name="ProjectReviewVo",value="传入后端的值",required=true) ProjectReviewVo projectReviewVo) {
        projectReviewVo.setReviewCode("6002");
        projectReviewVo = this.reviewService.toUpdate(projectReviewVo);
        return new Result<ProjectReviewVo>().sucess(projectReviewVo);
    }

    @Override
    @ApiOperation(value = "拒绝",notes = "拒绝")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<ProjectReviewVo> toRefused(@Valid @RequestBody @ApiParam(name="ProjectReviewVo",value="传入后端的值",required=true) ProjectReviewVo projectReviewVo) {
        projectReviewVo.setReviewCode("6004");
        projectReviewVo = this.reviewService.toUpdate(projectReviewVo);
        return new Result<ProjectReviewVo>().sucess(projectReviewVo);
    }

    @Override
    @ApiOperation(value = "保存",notes = "保存")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result toSave(@Valid @RequestBody @ApiParam(name="ProjectReviewVo",value="传入后端的值",required=true) ProjectReviewVo projectReviewVo) {
        projectReviewVo.setReviewCode("6001");
        projectReviewVo = this.reviewService.toUpdate(projectReviewVo);
        return new Result<ProjectReviewVo>().sucess(projectReviewVo);
    }
}



