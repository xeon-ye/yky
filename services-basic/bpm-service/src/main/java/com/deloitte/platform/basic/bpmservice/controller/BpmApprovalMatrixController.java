package com.deloitte.platform.basic.bpmservice.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.param.BpmApprovalMatrixQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmApprovalMatrixQueryFormForApproval;
import com.deloitte.platform.api.bpmservice.vo.BpmApprovalMatrixForm;
import com.deloitte.platform.api.bpmservice.vo.BpmApprovalMatrixVo;
import com.deloitte.platform.api.bpmservice.clinet.BpmApprovalMatrixClient;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;

import com.deloitte.platform.basic.bpmservice.service.IBpmApprovalMatrixService;
import com.deloitte.platform.basic.bpmservice.entity.BpmApprovalMatrix;


/**
 * @Author : jackliu
 * @Date : Create in 2019-03-16
 * @Description :   BpmApprovalMatrix控制器实现类
 * @Modified :
 */
@Api(tags = "BpmApprovalMatrix操作接口")
@Slf4j
@RestController
public class BpmApprovalMatrixController implements BpmApprovalMatrixClient {

    @Autowired
    public IBpmApprovalMatrixService  bpmApprovalMatrixService;

    @Autowired
    public UserFeignService userFeignService;

    @Override
    @ApiOperation(value = "查询下一节点的审批人", notes = "查询下一节点的审批人")
    public Result<List<BpmApprovalMatrixVo>> findNextApprover(@Valid @RequestBody @ApiParam(name="bpmApprovalMatrixFormForApproval",value="审批人的查询对象",required=true) BpmApprovalMatrixQueryFormForApproval bpmApprovalMatrixFormForApproval) {
        log.info("[BPM]BpmApprovalMatrixController.findNextApprover req:{}", JSONObject.toJSONString(bpmApprovalMatrixFormForApproval));
        GdcPage<BpmApprovalMatrix> bpmApprovalMatrixList=bpmApprovalMatrixService.findApprover(bpmApprovalMatrixFormForApproval,true);
        List<BpmApprovalMatrixVo> bpmApprovalMatrixVoList=new BeanUtils<BpmApprovalMatrixVo>().copyObjs(bpmApprovalMatrixList.getContent(),BpmApprovalMatrixVo.class);
        return new Result<List<BpmApprovalMatrixVo>>().sucess(bpmApprovalMatrixVoList);
    }

    @Override
    @ApiOperation(value = "查询当前节点的审批人", notes = "查询当前节点的审批人")
    public Result<List<BpmApprovalMatrixVo>> findThisApprover(@Valid @RequestBody @ApiParam(name="bpmApprovalMatrixFormForApproval",value="审批人的查询对象",required=true) BpmApprovalMatrixQueryFormForApproval bpmApprovalMatrixFormForApproval) {
        log.info("[BPM]BpmApprovalMatrixController.findThisApprover req:{}", JSONObject.toJSONString(bpmApprovalMatrixFormForApproval));
        GdcPage<BpmApprovalMatrix> bpmApprovalMatrixList=bpmApprovalMatrixService.findApprover(bpmApprovalMatrixFormForApproval,false);
        List<BpmApprovalMatrixVo> bpmApprovalMatrixVoList=new BeanUtils<BpmApprovalMatrixVo>().copyObjs(bpmApprovalMatrixList.getContent(),BpmApprovalMatrixVo.class);
        return new Result<List<BpmApprovalMatrixVo>>().sucess(bpmApprovalMatrixVoList);
    }

    @Override
    @ApiOperation(value = "翻页查询下一节点的审批人", notes = "翻页查询下一节点的审批人")
    public Result<GdcPage<BpmApprovalMatrixVo>> findNextApproverPage(@Valid @RequestBody @ApiParam(name="bpmApprovalMatrixFormForApproval",value="审批人的查询对象",required=true) BpmApprovalMatrixQueryFormForApproval bpmApprovalMatrixFormForApproval) {
        log.info("[BPM]BpmApprovalMatrixController.findNextApproverPage req:{}", JSONObject.toJSONString(bpmApprovalMatrixFormForApproval));
        GdcPage<BpmApprovalMatrix> page =bpmApprovalMatrixService.findApprover(bpmApprovalMatrixFormForApproval,true);
        GdcPage<BpmApprovalMatrixVo> resultPage =new BeanUtils<GdcPage>().copyObj(page,GdcPage.class);
        return new Result<GdcPage<BpmApprovalMatrixVo>>().sucess(resultPage);
    }

    @Override
    @ApiOperation(value = "翻页查询当前节点的审批人", notes = "翻页查询当前节点的审批人")
    public Result<GdcPage<BpmApprovalMatrixVo>> findThisApproverPage(@Valid @RequestBody @ApiParam(name="bpmApprovalMatrixFormForApproval",value="审批人的查询对象",required=true) BpmApprovalMatrixQueryFormForApproval bpmApprovalMatrixFormForApproval) {
        log.info("[BPM]BpmApprovalMatrixController.findThisApproverPage req:{}", JSONObject.toJSONString(bpmApprovalMatrixFormForApproval));
        GdcPage<BpmApprovalMatrix> page=bpmApprovalMatrixService.findApprover(bpmApprovalMatrixFormForApproval,false);
        GdcPage<BpmApprovalMatrixVo> resultPage =new BeanUtils<GdcPage>().copyObj(page,GdcPage.class);
        return new Result<GdcPage<BpmApprovalMatrixVo>>().sucess(resultPage);
    }


    @Override
    @ApiOperation(value = "新增BpmApprovalMatrix", notes = "新增一个BpmApprovalMatrix")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="bpmApprovalMatrixForm",value="新增BpmApprovalMatrix的form表单",required=true)  BpmApprovalMatrixForm bpmApprovalMatrixForm) {
        log.info("form:",  bpmApprovalMatrixForm.toString());
        //通过员工编号获取员工的Id
        if(bpmApprovalMatrixForm.getRuleType()==null||"".equals(bpmApprovalMatrixForm.getRuleType())){
            bpmApprovalMatrixForm.setRuleType("PERSON");
        }
        if("PERSON".equals(bpmApprovalMatrixForm.getRuleType())){
            //员工编号不能为空
            if(bpmApprovalMatrixForm.getAccountEmpNo()==null||"".equals(bpmApprovalMatrixForm.getAccountEmpNo())){
                return Result.fail("员工编号不能为空");
            }
            Result rs = userFeignService.getByEmpNo(bpmApprovalMatrixForm.getAccountEmpNo());
            if(rs.isSuccess()){
                BpmApprovalMatrix bpmApprovalMatrix =new BeanUtils<BpmApprovalMatrix>().copyObj(bpmApprovalMatrixForm,BpmApprovalMatrix.class);
                JSONObject userVo = JSONObject.parseObject(JSONObject.toJSONString(rs.getData()));
                String userId = userVo.getString("id");
                bpmApprovalMatrix.setAccountNum(userId);
                bpmApprovalMatrix.setAccountName(userVo.getString("name"));
                return Result.success(bpmApprovalMatrixService.save(bpmApprovalMatrix));
            }else{
                return rs;
            }
        }else{
            BpmApprovalMatrix bpmApprovalMatrix =new BeanUtils<BpmApprovalMatrix>().copyObj(bpmApprovalMatrixForm,BpmApprovalMatrix.class);
            bpmApprovalMatrix.setChargeOrg("-1");
            return Result.success(bpmApprovalMatrixService.save(bpmApprovalMatrix));
        }
    }

    @Override
    @ApiOperation(value = "批量BpmApprovalMatrix", notes = "批量新增BpmApprovalMatrix")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result save(@Valid @RequestBody BpmApprovalMatrixForm[] bpmApprovalMatrixForms){
        //删除所有的配置信息
        for(BpmApprovalMatrixForm bpmApprovalMatrixForm : bpmApprovalMatrixForms) {
            if (bpmApprovalMatrixForm.getRuleType() == null || "".equals(bpmApprovalMatrixForm.getRuleType())) {
                bpmApprovalMatrixForm.setRuleType("PERSON");
            }
            if ("PERSON".equals(bpmApprovalMatrixForm.getRuleType())) {
                //员工编号不能为空
                if (bpmApprovalMatrixForm.getAccountEmpNo() == null || "".equals(bpmApprovalMatrixForm.getAccountEmpNo())) {
                    return Result.fail("员工编号不能为空");
                }
                Result rs = userFeignService.getByEmpNo(bpmApprovalMatrixForm.getAccountEmpNo());
                if (rs.isSuccess()) {
                    BpmApprovalMatrix bpmApprovalMatrix = new BeanUtils<BpmApprovalMatrix>().copyObj(bpmApprovalMatrixForm, BpmApprovalMatrix.class);
                    JSONObject userVo = JSONObject.parseObject(JSONObject.toJSONString(rs.getData()));
                    String userId = userVo.getString("id");
                    bpmApprovalMatrix.setAccountNum(userId);
                    bpmApprovalMatrix.setAccountName(userVo.getString("name"));
                    bpmApprovalMatrixService.save(bpmApprovalMatrix);
                } else {
                    return rs;
                }
            } else {
                BpmApprovalMatrix bpmApprovalMatrix = new BeanUtils<BpmApprovalMatrix>().copyObj(bpmApprovalMatrixForm, BpmApprovalMatrix.class);
                bpmApprovalMatrix.setChargeOrg("-1");
                bpmApprovalMatrixService.save(bpmApprovalMatrix);
            }
        }
        return Result.success();
    }

    @Override
    @ApiOperation(value = "删除BpmApprovalMatrix", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "BpmApprovalMatrixID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        bpmApprovalMatrixService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改BpmApprovalMatrix", notes = "修改指定BpmApprovalMatrix信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "BpmApprovalMatrix的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="bpmApprovalMatrixForm",value="修改BpmApprovalMatrix的form表单",required=true)  BpmApprovalMatrixForm bpmApprovalMatrixForm) {
        BpmApprovalMatrix bpmApprovalMatrix =new BeanUtils<BpmApprovalMatrix>().copyObj(bpmApprovalMatrixForm,BpmApprovalMatrix.class);
        bpmApprovalMatrix.setId(id);
        //通过员工编号获取员工的Id
        if(bpmApprovalMatrixForm.getRuleType()==null||"".equals(bpmApprovalMatrixForm.getRuleType())){
            bpmApprovalMatrixForm.setRuleType("PERSON");
        }
        if("PERSON".equals(bpmApprovalMatrixForm.getRuleType())){
            //员工编号不能为空
            if(bpmApprovalMatrixForm.getAccountEmpNo()==null||"".equals(bpmApprovalMatrixForm.getAccountEmpNo())){
                return Result.fail("员工编号不能为空");
            }
            Result rs = userFeignService.getByEmpNo(bpmApprovalMatrixForm.getAccountEmpNo());
            if(rs.isSuccess()){
                JSONObject userVo = JSONObject.parseObject(JSONObject.toJSONString(rs.getData()));
                String userId = userVo.getString("id");
                bpmApprovalMatrix.setAccountNum(userId);
                bpmApprovalMatrix.setAccountName(userVo.getString("name"));
                bpmApprovalMatrixService.saveOrUpdate(bpmApprovalMatrix);
                return Result.success();
            }else{
                return rs;
            }
        }else{
            bpmApprovalMatrix.setChargeOrg("-1");
            bpmApprovalMatrixService.saveOrUpdate(bpmApprovalMatrix);
            return Result.success();
        }
    }


    @Override
    @ApiOperation(value = "获取BpmApprovalMatrix", notes = "获取指定ID的BpmApprovalMatrix信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "BpmApprovalMatrix的ID", required = true, dataType = "long")
    public Result<BpmApprovalMatrixVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        BpmApprovalMatrix bpmApprovalMatrix=bpmApprovalMatrixService.getById(id);
        BpmApprovalMatrixVo bpmApprovalMatrixVo=new BeanUtils<BpmApprovalMatrixVo>().copyObj(bpmApprovalMatrix,BpmApprovalMatrixVo.class);
        return new Result<BpmApprovalMatrixVo>().sucess(bpmApprovalMatrixVo);
    }

    @Override
    @ApiOperation(value = "列表查询BpmApprovalMatrix", notes = "根据条件查询BpmApprovalMatrix列表数据")
    public Result<List<BpmApprovalMatrixVo>> list(@Valid @RequestBody @ApiParam(name="bpmApprovalMatrixQueryForm",value="BpmApprovalMatrix查询参数",required=true) BpmApprovalMatrixQueryForm bpmApprovalMatrixQueryForm) {
        log.info("search with bpmApprovalMatrixQueryForm:", bpmApprovalMatrixQueryForm.toString());
        List<BpmApprovalMatrix> bpmApprovalMatrixList=bpmApprovalMatrixService.selectList(bpmApprovalMatrixQueryForm);
        List<BpmApprovalMatrixVo> bpmApprovalMatrixVoList=new BeanUtils<BpmApprovalMatrixVo>().copyObjs(bpmApprovalMatrixList,BpmApprovalMatrixVo.class);
        return new Result<List<BpmApprovalMatrixVo>>().sucess(bpmApprovalMatrixVoList);
    }


    @Override
    @ApiOperation(value = "分页查询BpmApprovalMatrix", notes = "根据条件查询BpmApprovalMatrix分页数据")
    public Result<IPage<BpmApprovalMatrixVo>> search(@Valid @RequestBody @ApiParam(name="bpmApprovalMatrixQueryForm",value="BpmApprovalMatrix查询参数",required=true) BpmApprovalMatrixQueryForm bpmApprovalMatrixQueryForm) {
        log.info("search with bpmApprovalMatrixQueryForm:", bpmApprovalMatrixQueryForm.toString());
        IPage<BpmApprovalMatrix> bpmApprovalMatrixPage=bpmApprovalMatrixService.selectPage(bpmApprovalMatrixQueryForm);
        IPage<BpmApprovalMatrixVo> bpmApprovalMatrixVoPage=new BeanUtils<BpmApprovalMatrixVo>().copyPageObjs(bpmApprovalMatrixPage,BpmApprovalMatrixVo.class);
        return new Result<IPage<BpmApprovalMatrixVo>>().sucess(bpmApprovalMatrixVoPage);
    }

}



