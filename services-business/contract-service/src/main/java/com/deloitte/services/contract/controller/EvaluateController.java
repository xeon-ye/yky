package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.EvaluateQueryForm;
import com.deloitte.platform.api.contract.vo.EvaluateForm;
import com.deloitte.platform.api.contract.vo.EvaluateVo;
import com.deloitte.platform.api.contract.client.EvaluateClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.contract.entity.BasicInfo;
import com.deloitte.services.contract.service.IBasicInfoService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.List;

import com.deloitte.services.contract.service.IEvaluateService;
import com.deloitte.services.contract.entity.Evaluate;


/**
 * @Author : hemingzheng
 * @Date : Create in 2019-04-25
 * @Description :   Evaluate控制器实现类
 * @Modified :
 */
@Api(tags = "合同评价操作接口")
@Slf4j
@RestController
@RequestMapping("/evaluate")
public class EvaluateController implements EvaluateClient {

    @Autowired
    public IEvaluateService  evaluateService;
    @Autowired
    public IBasicInfoService basicInfoService;


    @Override
    @ApiOperation(value = "新增合同评价", notes = "新增合同评价")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    @PostMapping("/add")
    public Result add(@Valid @RequestBody @ApiParam(name="evaluateForm",value="新增Evaluate的form表单",required=true)  EvaluateForm evaluateForm) {
        log.info("form:",  evaluateForm.toString());
        Evaluate evaluate =new BeanUtils<Evaluate>().copyObj(evaluateForm,Evaluate.class);
        //查询是否已经保存过
        QueryWrapper<Evaluate> queryEvaluate = new QueryWrapper<>();
        queryEvaluate.eq(Evaluate.CONTRACT_ID,evaluateForm.getContractId());
        Evaluate evaluateInfo = evaluateService.getOne(queryEvaluate);
        if(null !=evaluateInfo){
            evaluateInfo.setSignStrategyImplementation(evaluateForm.getSignStrategyImplementation());
            evaluateInfo.setSignBiddingRiskanalysis(evaluateForm.getSignBiddingRiskanalysis());
            evaluateInfo.setSignQuotationsLessons(evaluateForm.getSignQuotationsLessons());
            evaluateInfo.setSignLessonsCareful(evaluateForm.getSignLessonsCareful());
            evaluateInfo.setSignCoordinationEvaluation(evaluateForm.getSignCoordinationEvaluation());
            evaluateInfo.setPerformIsNormal(evaluateForm.getPerformIsNormal());
            evaluateInfo.setPerformSpecialCase(evaluateForm.getPerformSpecialCase());
            evaluateInfo.setPerformProsCons(evaluateForm.getPerformProsCons());
            evaluateInfo.setPerformCoordinatProblem(evaluateForm.getPerformCoordinatProblem());
            evaluateInfo.setPerformOther(evaluateForm.getPerformOther());
            evaluateInfo.setTermsProsCons(evaluateForm.getTermsProsCons());
            evaluateInfo.setTermsSpecialpromResult(evaluateForm.getTermsSpecialpromResult());
            evaluateInfo.setTermsSpecialpromResult(evaluateForm.getTermsSpecialpromResult());
            evaluateInfo.setTermsOther(evaluateForm.getTermsOther());
            evaluateService.updateById(evaluateInfo);
        }else {
            evaluateService.save(evaluate);
            //修改评价状态为已评价
            BasicInfo basicInfo = basicInfoService.getById(evaluateForm.getContractId());
            basicInfo.setEvaluateStatue("EVA1000");
            basicInfoService.updateById(basicInfo);
        }
        return Result.success();
    }


    @Override
    @ApiOperation(value = "删除Evaluate", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "EvaluateID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        evaluateService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改Evaluate", notes = "修改指定Evaluate信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "Evaluate的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="evaluateForm",value="修改Evaluate的form表单",required=true)  EvaluateForm evaluateForm) {
        Evaluate evaluate =new BeanUtils<Evaluate>().copyObj(evaluateForm,Evaluate.class);
        evaluate.setId(id);
        evaluateService.saveOrUpdate(evaluate);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取Evaluate", notes = "获取指定ID的Evaluate信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Evaluate的ID", required = true, dataType = "long")
    public Result<EvaluateVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Evaluate evaluate=evaluateService.getById(id);
        EvaluateVo evaluateVo=new BeanUtils<EvaluateVo>().copyObj(evaluate,EvaluateVo.class);
        return new Result<EvaluateVo>().sucess(evaluateVo);
    }

    @Override
    @ApiOperation(value = "列表查询Evaluate", notes = "根据条件查询Evaluate列表数据")
    public Result<List<EvaluateVo>> list(@Valid @RequestBody @ApiParam(name="evaluateQueryForm",value="Evaluate查询参数",required=true) EvaluateQueryForm evaluateQueryForm) {
        log.info("search with evaluateQueryForm:", evaluateQueryForm.toString());
        List<Evaluate> evaluateList=evaluateService.selectList(evaluateQueryForm);
        List<EvaluateVo> evaluateVoList=new BeanUtils<EvaluateVo>().copyObjs(evaluateList,EvaluateVo.class);
        return new Result<List<EvaluateVo>>().sucess(evaluateVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Evaluate", notes = "根据条件查询Evaluate分页数据")
    public Result<IPage<EvaluateVo>> search(@Valid @RequestBody @ApiParam(name="evaluateQueryForm",value="Evaluate查询参数",required=true) EvaluateQueryForm evaluateQueryForm) {
        log.info("search with evaluateQueryForm:", evaluateQueryForm.toString());
        IPage<Evaluate> evaluatePage=evaluateService.selectPage(evaluateQueryForm);
        IPage<EvaluateVo> evaluateVoPage=new BeanUtils<EvaluateVo>().copyPageObjs(evaluatePage,EvaluateVo.class);
        return new Result<IPage<EvaluateVo>>().sucess(evaluateVoPage);
    }


}



