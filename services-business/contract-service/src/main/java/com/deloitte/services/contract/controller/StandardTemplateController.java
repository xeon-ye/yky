package com.deloitte.services.contract.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.contract.param.StandardTemplateQueryForm;
import com.deloitte.platform.api.contract.vo.AttamentInfoForm;
import com.deloitte.platform.api.contract.vo.StandardTemplateForm;
import com.deloitte.platform.api.contract.vo.StandardTemplateVo;
import com.deloitte.platform.api.contract.client.StandardTemplateClient;
import com.deloitte.platform.api.contract.vo.TemplatePersonMapForm;
import com.deloitte.platform.api.isump.UserClient;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.contract.common.enums.ContractErrorType;
import com.deloitte.services.contract.common.enums.VoucherStatusEnums;
import com.deloitte.services.contract.common.enums.VoucherTypeEnums;
import com.deloitte.services.contract.common.util.AssertUtils;
import com.deloitte.services.contract.entity.*;
import com.deloitte.services.contract.entity.Process;
import com.deloitte.services.contract.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :   StandardTemplate控制器实现类
 * @Modified :
 */
@Api(tags = "6-标准文本操作接口")
@Slf4j
@RestController
@RequestMapping("/standardTemplate")
public class StandardTemplateController implements StandardTemplateClient {

    @Autowired
    public IStandardTemplateService  standardTemplateService;
    @Autowired
    public IAttamentInfoService iAttamentInfoService;
    @Autowired
    public ICommonService commonService;
    @Autowired
    public ITemplatePersonMapService iTemplatePersonMapService;
    @Autowired
    private IApprovalVoucherService approvalVoucherService;
    @Autowired
    public IBPMService bpmService;
    @Autowired
    public UserClient userClient;
    @Autowired
    public IProcessService processService;

    @Override
    @ApiOperation(value = "新增合同标准文本", notes = "新增合同标准文本")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="standardTemplateForm",value="新增StandardTemplate的form表单",required=true)  StandardTemplateForm standardTemplateForm) {
        log.info("form:",  standardTemplateForm.toString());
        UserVo userVo = commonService.getCurrentUser();
        StandardTemplate standardTemplate =new BeanUtils<StandardTemplate>().copyObj(standardTemplateForm,StandardTemplate.class);
        standardTemplate.setStatue("TEMP1000");
        standardTemplate.setCreateBy(userVo.getId());
        standardTemplate.setCreateByName(userVo.getName());
        return Result.success( standardTemplateService.save(standardTemplate));
    }


    @Override
    @ApiOperation(value = "删除合同标准文本", notes = "删除合同标准文本")
    @ApiImplicitParam(paramType = "path", name = "id", value = "StandardTemplateID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        standardTemplateService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改StandardTemplate", notes = "修改指定StandardTemplate信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "StandardTemplate的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="standardTemplateForm",value="修改StandardTemplate的form表单",required=true)  StandardTemplateForm standardTemplateForm) {
        StandardTemplate standardTemplate =new BeanUtils<StandardTemplate>().copyObj(standardTemplateForm,StandardTemplate.class);
        standardTemplate.setId(id);
        standardTemplateService.saveOrUpdate(standardTemplate);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取StandardTemplate", notes = "获取指定ID的StandardTemplate信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "StandardTemplate的ID", required = true, dataType = "long")
    public Result<StandardTemplateVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        StandardTemplate standardTemplate=standardTemplateService.getById(id);
        StandardTemplateVo standardTemplateVo=new BeanUtils<StandardTemplateVo>().copyObj(standardTemplate,StandardTemplateVo.class);
        return new Result<StandardTemplateVo>().sucess(standardTemplateVo);
    }

    @Override
    @ApiOperation(value = "列表查询StandardTemplate", notes = "根据条件查询StandardTemplate列表数据")
    @PostMapping("/list")
    public Result<List<StandardTemplateVo>> list(@Valid @RequestBody @ApiParam(name="standardTemplateQueryForm",value="StandardTemplate查询参数",required=true) StandardTemplateQueryForm standardTemplateQueryForm) {
        log.info("search with standardTemplateQueryForm:", standardTemplateQueryForm.toString());
        List<StandardTemplate> standardTemplateList=standardTemplateService.selectList(standardTemplateQueryForm);
        List<StandardTemplateVo> standardTemplateVoList=new BeanUtils<StandardTemplateVo>().copyObjs(standardTemplateList,StandardTemplateVo.class);
        return new Result<List<StandardTemplateVo>>().sucess(standardTemplateVoList);
    }


    @Override
    @ApiOperation(value = "分页查询合同标准文本", notes = "分页查询合同标准文本")
    public Result<IPage<StandardTemplateVo>> search(@Valid @RequestBody @ApiParam(name="standardTemplateQueryForm",value="StandardTemplate查询参数",required=true) StandardTemplateQueryForm standardTemplateQueryForm) {
        log.info("search with standardTemplateQueryForm:", standardTemplateQueryForm.toString());
        IPage<StandardTemplate> standardTemplatePage=standardTemplateService.selectPage(standardTemplateQueryForm);
        IPage<StandardTemplateVo> standardTemplateVoPage=new BeanUtils<StandardTemplateVo>().copyPageObjs(standardTemplatePage,StandardTemplateVo.class);
        return new Result<IPage<StandardTemplateVo>>().sucess(standardTemplateVoPage);
    }

    @ApiOperation(value = "新增合同标准文本（自己写的，会返回保存的实体信息）", notes = "新增合同标准文本（自己写的，会返回保存的实体信息）")
    @PostMapping("/addStandard")
    public Result<StandardTemplateVo> addStandard(@Valid @RequestBody StandardTemplateForm standardTemplateForm) {
        UserVo userVo = commonService.getCurrentUser();
        StandardTemplate standardTemplate =new BeanUtils<StandardTemplate>().copyObj(standardTemplateForm,StandardTemplate.class);
        standardTemplate.setStatue("TEMP1000");
        standardTemplate.setCreateBy(userVo.getId());
        standardTemplate.setCreateByName(userVo.getName());
        standardTemplateService.save(standardTemplate);
        StandardTemplateVo standardTemplateVo = new BeanUtils<StandardTemplateVo>().copyObj(standardTemplate, StandardTemplateVo.class);
        return new Result<StandardTemplateVo>().sucess(standardTemplateVo);
    }

    @ApiOperation(value = "上传标准文本正文",notes = "上传标准文本正文")
    @PostMapping("/uploadContract")
    public Result uploadContract (@Valid @RequestBody AttamentInfoForm attamentInfoForm){
        AttamentInfo attamentInfo = new BeanUtils<AttamentInfo>().copyObj(attamentInfoForm,AttamentInfo.class);
        iAttamentInfoService.save(attamentInfo);
        return Result.success();
    }

    @ApiOperation(value = "修改标准文本正文",notes = "修改标准文本正文")
    @PostMapping("/updateStandard")
    public Result updateStandard (@Valid @RequestBody StandardTemplateForm standardTemplateForm){
        StandardTemplate standardTemplate = new BeanUtils<StandardTemplate>().copyObj(standardTemplateForm,StandardTemplate.class);
        standardTemplate.setId(Long.parseLong(standardTemplateForm.getId()));
        standardTemplate.setStatue("TEMP1000");
        standardTemplateService.updateById(standardTemplate);
        return Result.success();
    }

    @ApiOperation(value = "添加我的常用标准文本",notes = "添加我的常用标准文本")
    @PostMapping("/addMyStandard")
    public Result addMyStandard(@Valid @RequestBody TemplatePersonMapForm templatePersonMap){
        UserVo userVo = commonService.getCurrentUser();
        templatePersonMap.setPersonCode(userVo.getId());
        templatePersonMap.setIsUsed("1");
        Result result = iTemplatePersonMapService.addMyStandard(templatePersonMap);
        return result;
    }

    @ApiOperation(value = "移除我的常用标准文本",notes = "移除我的常用标准文本")
    @PostMapping("/deleteMyStandard")
    public Result deleteMyStandard(@Valid @RequestBody TemplatePersonMapForm templatePersonMap){
        iTemplatePersonMapService.removeById(Long.parseLong(templatePersonMap.getId()));
        return Result.success();
    }

    @ApiOperation(value = "根据id查询常用标准文本",notes = "根据id查询常用标准文本")
    @PostMapping("/getStandardById")
    public Result<StandardTemplateVo> getStandardById(@Valid @RequestBody StandardTemplateForm standardTemplateForm){
        StandardTemplate standardTemplate = standardTemplateService.getById(standardTemplateForm.getId());
        StandardTemplateVo standardTemplateVo = new BeanUtils<StandardTemplateVo>().copyObj(standardTemplate,StandardTemplateVo.class);
        return new Result<StandardTemplateVo>().sucess(standardTemplateVo);
    }

    @ApiOperation(value = "查询我的常用标准文本list",notes = "查询我的常用标准文本list")
    @PostMapping("/getTemplatePersonList")
    public Result<StandardTemplateVo> getTemplatePersonList(@Valid @RequestBody StandardTemplateQueryForm standardTemplateQueryForm){
        List<StandardTemplate> StandardTemplates = iTemplatePersonMapService.getTemplatePersonList(standardTemplateQueryForm);
        List<StandardTemplateVo> standardTemplateVos = new BeanUtils<StandardTemplateVo>().copyObjs(StandardTemplates,StandardTemplateVo.class);
        String total = iTemplatePersonMapService.getTemplatePersonMxaSize(standardTemplateQueryForm).toString();
        StandardTemplateVo standardTemplateVo = new StandardTemplateVo();
        standardTemplateVo.setStandardTemplateVoList(standardTemplateVos);
        standardTemplateVo.setTotal(total);
        return new Result<StandardTemplateVo>().sucess(standardTemplateVo);
    }

    @ApiOperation(value = "提交标准文本审批流程",notes = "提交标准文本审批流程")
    @PostMapping("/submitStandardWork")
    public Result submitStandardWork(@Valid @RequestBody StandardTemplateForm standardTemplateForm){
        AssertUtils.asserts(null == standardTemplateForm || null == standardTemplateForm.getId()  ,ContractErrorType.STANDARD_BOOK_PARAMETER_ERROR);

        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        //判断合同是否已经提交过
        QueryWrapper<ApprovalVoucher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ApprovalVoucher.BUSINESS_ID,standardTemplateForm.getId());
        queryWrapper.eq(ApprovalVoucher.VOUCHER_STATUS,VoucherStatusEnums.AUDITING.getCode());
        queryWrapper.eq(ApprovalVoucher.VOUCHER_TYPE, VoucherTypeEnums.STANDARD_BOOK.getCode());
        ApprovalVoucher approvalVoucher = approvalVoucherService.getOne(queryWrapper);
        AssertUtils.asserts(null != approvalVoucher , ContractErrorType.STANDARD_VOUCHER_IS_ALREADLY_SUBMIT);
        //新建一个单据
        ApprovalVoucher voucher = approvalVoucherService.generateNewVoucher(Long.parseLong(standardTemplateForm.getId()), VoucherTypeEnums.STANDARD_BOOK);
        //流程提交参数
        Map<String,String> processVariables = new HashMap<>();
//        AssertUtils.asserts( null == standardTemplateForm.getProcessDefineKey() || "".equals(standardTemplateForm.getProcessDefineKey()), ContractErrorType.PROCESS_KEY_IS_NULL);
        //获取流程信息
        QueryWrapper<Process> processQueryWrapper = new QueryWrapper<>();
        processQueryWrapper.eq(Process.PROCESS_TYPE,VoucherTypeEnums.STANDARD_BOOK.getCode());
        Process process = processService.getOne(processQueryWrapper);
        AssertUtils.asserts(null == process , ContractErrorType.PROCESS_IS_NULL);

        Result result = bpmService.startAuditProcess(voucher,userVo,organizationVo,process.getProcessDefineKey(),processVariables );
        if(result.isSuccess()){//流程发起成功
            //更新审批状态
            StandardTemplate standardTemplate = standardTemplateService.getById(standardTemplateForm.getId());
            standardTemplate.setStatue("TEMP2000");
            standardTemplateService.updateById(standardTemplate);
            //更新单据状态
            voucher.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
            approvalVoucherService.updateById(voucher);
        }
        return result;
    }


    @ApiOperation(value = "无效标准文本审批流程",notes = "无效标准文本审批流程")
    @PostMapping("/disableStandardWork")
    public Result disableStandardWork(@Valid @RequestBody StandardTemplateForm standardTemplateForm){
        AssertUtils.asserts(null == standardTemplateForm || null == standardTemplateForm.getId()  ,ContractErrorType.STANDARD_UNABLE_PARAMETER_ERROR);

        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        //判断合同是否已经提交过
        QueryWrapper<ApprovalVoucher> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(ApprovalVoucher.BUSINESS_ID,standardTemplateForm.getId());
        queryWrapper.eq(ApprovalVoucher.VOUCHER_STATUS,VoucherStatusEnums.AUDITING.getCode());
        queryWrapper.eq(ApprovalVoucher.VOUCHER_TYPE, VoucherTypeEnums.STANDARD_UNABLE.getCode());
        ApprovalVoucher approvalVoucher = approvalVoucherService.getOne(queryWrapper);
        AssertUtils.asserts(null != approvalVoucher , ContractErrorType.STANDARD_VOUCHER_IS_ALREADLY_SUBMIT);
        //新建一个单据
        ApprovalVoucher voucher = approvalVoucherService.generateNewVoucher(Long.parseLong(standardTemplateForm.getId()), VoucherTypeEnums.STANDARD_UNABLE);
        //流程提交参数
        Map<String,String> processVariables = new HashMap<>();
//        AssertUtils.asserts( null == standardTemplateForm.getProcessDefineKey() || "".equals(standardTemplateForm.getProcessDefineKey()), ContractErrorType.PROCESS_KEY_IS_NULL);
        //获取流程信息
        QueryWrapper<Process> processQueryWrapper = new QueryWrapper<>();
        processQueryWrapper.eq(Process.PROCESS_TYPE,VoucherTypeEnums.STANDARD_UNABLE.getCode());
        Process process = processService.getOne(processQueryWrapper);
        AssertUtils.asserts(null == process , ContractErrorType.PROCESS_IS_NULL);

        Result result = bpmService.startAuditProcess(voucher,userVo,organizationVo,process.getProcessDefineKey(),processVariables );
        if(result.isSuccess()){//流程发起成功
            //更新审批状态
            StandardTemplate standardTemplate = standardTemplateService.getById(standardTemplateForm.getId());
            standardTemplate.setStatue("TEMP4000");//修改为失效中状态
            standardTemplateService.updateById(standardTemplate);
            //更新单据状态
            voucher.setVoucherStatus(VoucherStatusEnums.AUDITING.getCode());
            approvalVoucherService.updateById(voucher);
        }
        return result;
    }

    @ApiOperation(value = "分页查询StandardTemplate", notes = "根据条件查询StandardTemplate分页数据")
    @PostMapping("/getStandardTemplateList")
    public Result<Map<String, Object>> getStandardTemplateList(@Valid @RequestBody @ApiParam(name="standardTemplateQueryForm",value="StandardTemplate查询参数",required=true) Map<String, Object> params) {
        log.info("search with standardTemplateQueryForm:", params.toString());
        // 每页多少条
        int page = Integer.parseInt((String) params.get("page"));
        // 第几页
        int currentPage = Integer.parseInt((String) params.get("currentPage"));
        //标准文本名称
        String templateName = params.get("templateName") != null ? (String) params.get("templateName") : "";
        // 标准文本编号
        String templateCode = params.get("templateCode") != null  ? (String) params.get("templateCode") : "";
        // 标准文本属性
        String attributeCode = params.get("attributeCode") != null  ? (String) params.get("attributeCode") : "";
        // 适用组织范围
        String orgCode = params.get("orgCode") != null  ? (String) params.get("orgCode") : "";
        // 适用合同类型
        String contractTypeCode = params.get("contractTypeCode") != null  ? (String) params.get("contractTypeCode") : "";
        int minNum = (currentPage - 1) * page + 1;
        int maxNum = currentPage * page;
        Map<String, String> map = new HashMap<>();
        map.put("minNum", String.valueOf(minNum));
        map.put("maxNum", String.valueOf(maxNum));
        map.put("templateName", templateName);
        map.put("templateCode", templateCode);
        map.put("attributeCode", attributeCode);
        map.put("orgCode", orgCode);
        map.put("contractTypeCode", contractTypeCode);
        List<StandardTemplate> standardTemplatePage = standardTemplateService.getStandardTemplateList(map);
        int maxSize = standardTemplateService.getStandardTemplateMxaSize(map);
        int max = maxSize / page + 1;
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("standardTemplatePage", standardTemplatePage);
        resultMap.put("matSize", max);
        return new Result<Map<String, Object>>().sucess(resultMap);
    }

    @ApiOperation(value = "根据标准文本属性查询StandardTemplate", notes = "根据标准文本属性查询StandardTemplate数据")
    @PostMapping("/getStandardTemplateAllList")
    public Result<List<StandardTemplate>> getStandardTemplateAllList(@Valid @RequestBody @ApiParam(name="standardTemplateQueryForm",value="StandardTemplate查询参数",required=true) Map<String, Object> params) {
        // 标准文本属性
        String attributeCode = (String) params.get("attributeCode");
        Map<String, String> map = new HashMap<>();
        map.put("attributeCode", attributeCode);
        List<StandardTemplate> standardTemplatePage = standardTemplateService.getStandardTemplateAllList(map);
        return new Result<List<StandardTemplate>>().sucess(standardTemplatePage);
    }

    @ApiOperation(value = "根据id获取用户信息", notes = "根据id获取用户信息")
    @PostMapping("/getUserByid")
    public Result<UserVo> getUserByid(String id){
        return userClient.get(Long.parseLong(id));
    }
}



