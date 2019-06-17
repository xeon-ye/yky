package com.deloitte.services.fssc.common.controller;

import com.alibaba.fastjson.JSONArray;
import com.deloitte.platform.api.fssc.budget.param.BudgetProjectQueryForm;
import com.deloitte.platform.api.fssc.dic.vo.DicValueVo;
import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.feign.FsscUserFeignService;
import com.deloitte.platform.api.isump.feign.OrganizationFeignService;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.param.DeptQueryForm;
import com.deloitte.platform.api.isump.param.UserQueryForm;
import com.deloitte.platform.api.isump.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.fssc.base.entity.BasePersonIncomeType;
import com.deloitte.services.fssc.base.service.IBasePersonIncomeTypeService;
import com.deloitte.services.fssc.budget.entity.BudgetProject;
import com.deloitte.services.fssc.budget.service.IBudgetProjectService;
import com.deloitte.services.fssc.business.pe.service.IPerSelfAssessmentService;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.common.vo.AllDocumentTypeVo;
import com.deloitte.services.fssc.common.vo.FsscCurrentUserInfo;
import com.deloitte.services.fssc.common.vo.Select2DataSource;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.eums.FsscTableNameEums;
import com.deloitte.services.fssc.util.AssertUtils;
import com.deloitte.services.fssc.util.QrCodeUtils;
import com.deloitte.services.fssc.util.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = "公共接口")
@RestController
@RequestMapping(value = "fssc/common")
@Slf4j
public class FsscCommonController {

    @Autowired
    private DeptFeignService deptFeignService;

    @Autowired
    private OrganizationFeignService organizationFeignService;

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private FsscUserFeignService fsscUserFeignService;

    @Autowired
    private FsscCommonServices commonServices;

    @Autowired
    private IBasePersonIncomeTypeService personIncomeTypeService;

    @Autowired
    private IBudgetProjectService budgetProjectService;

    @Autowired
    private IPerSelfAssessmentService selfAssessmentService;

    @ApiOperation(value = "查询当前单位", notes = "查询当前单位")
    @GetMapping(value = "/findDepts")
    @ResponseBody
    public Result<List<DeptVo>> findDepts() {
        List<DeptVo> deptVos = new ArrayList<>();
        DeptVo currentDept = commonServices.getCurrentDept();
        deptVos.add(currentDept);
        return Result.success(deptVos);
    }


    @ApiOperation(value = "查询所有单位", notes = "查询所有单位")
    @GetMapping(value = "/findAllDepts")
    @ResponseBody
    public Result<List<DeptVo>> findAllDepts(DeptQueryForm deptQueryForm) {
        deptQueryForm.setPageSize(999999);
        Result<GdcPage<DeptVo>> pageResult = deptFeignService.search2(deptQueryForm);
        log.info("查询所有单位,返回编码{}，信息{}", pageResult.getCode(), pageResult.getMesg());
        AssertUtils.asserts(pageResult.isSuccess(), FsscErrorType.GET_ALL_ORG_NOT_FAIL);
        return Result.success(pageResult.getData().getContent());
    }


    @ApiOperation(value = "查询当前用户单位下所有部门", notes = "查询当前用户单位下所有部门")
    @GetMapping(value = "/findOrgs")
    @ResponseBody
    public Result<List<OrganizationVo>> findOrgs() {
        DeptVo currentDept = commonServices.getCurrentDept();
        return organizationFeignService.getOrgDeptByCode(currentDept.getDeptCode());
    }

    @ApiOperation(value = "查询院校", notes = "查询院校")
    @GetMapping(value = "/findThreeSchool")
    @ResponseBody
    public Result<List<OrganizationVo>> findThreeSchool() {
        List<OrganizationVo> list = new ArrayList<>();
        Result<OrganizationVo> code = organizationFeignService.getByCode("1001039");
        if (code.isSuccess()) {
            list.add(code.getData());
        }
        Result<OrganizationVo> code1 = organizationFeignService.getByCode("1001040");
        if (code1.isSuccess()) {
            list.add(code1.getData());
        }
        Result<OrganizationVo> code2 = organizationFeignService.getByCode("1001041");
        if (code2.isSuccess()) {
            list.add(code2.getData());
        }
        return Result.success(list);
    }

    @ApiOperation(value = "查询当前用户所属部门", notes = "查询当前用户所属部门")
    @GetMapping(value = "/findCurrentUserOrgs")
    @ResponseBody
    public Result<List<OrganizationVo>> findCurrentUserOrgs() {
        List<OrganizationVo> list = new ArrayList<>();
        UserVo currentUser = commonServices.getCurrentUser();
        List<DeputyAccountVo> deputyAccount = currentUser.getDeputyAccount();
        if (CollectionUtils.isNotEmpty(deputyAccount)) {
            for (DeputyAccountVo vo : deputyAccount) {
                OrganizationVo organizationVo = new OrganizationVo();
                organizationVo.setCode(vo.getOrgCode());
                organizationVo.setName(vo.getOrgName());
                organizationVo.setId(StringUtil.objectToString(vo.getOrgId()));
                list.add(organizationVo);
            }

        }
        return Result.success(list);
    }

    @ApiOperation(value = "查询所有人员", notes = "查询所有人员")
    @GetMapping(value = "/findUsers")
    @ResponseBody
    public Result<GdcPage<UserVo>> findUsers(UserQueryForm form) {
        Result<GdcPage<UserVo>> result = userFeignService.search2(form);
        if (result.isSuccess() && result.getData() != null && CollectionUtils.isNotEmpty(result.getData().getContent())){
            for (UserVo userVo : result.getData().getContent()){
                userVo.setPositionTitle(userVo.getPositionLevel());
            }
        }
        return result;
    }

    @ApiOperation(value = "查询项目负责人", notes = "查询项目负责人")
    @GetMapping(value = "/findProjectDuty")
    @ResponseBody
    public Result<List<Select2DataSource>> findProjectDuty(UserQueryForm form) {
        List<BudgetProject> projectList = budgetProjectService.selectList(new BudgetProjectQueryForm());
        if (CollectionUtils.isEmpty(projectList)){
            return Result.success();
        }
        List<String> dutyIdList = projectList.stream().map(BudgetProject :: getProjectDutyId).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(dutyIdList)){
            return Result.success();
        }
        List<Long> dutyIdListLong = new ArrayList<>(dutyIdList.size());
        for (String dutyId : dutyIdList){
            if (StringUtils.isNotBlank(dutyId)) {
                dutyIdListLong.add(Long.valueOf(dutyId));
            };
        }
        form.setIdList(dutyIdListLong);
        Result<GdcPage<UserVo>> result = userFeignService.search2(form);
        List<Select2DataSource> dataSourceList = new ArrayList<>();
        if (result.isSuccess() && result.getData() != null && CollectionUtils.isNotEmpty(result.getData().getContent())){
            for (UserVo userVo : result.getData().getContent()){
                Select2DataSource dataSource = new Select2DataSource(userVo.getId(),userVo.getEmpNo(), userVo.getName());
                dataSourceList.add(dataSource);
            }
        }
        return Result.success(dataSourceList);
    }

    @ApiOperation(value = "查询部门下所有人员", notes = "查询部门下所有人员")
    @GetMapping(value = "/findUserByOrgCode")
    @ResponseBody
    public Result<List<UserVo>> findUserByOrgCode(@RequestParam(value = "orgCode") String orgCode) {
        Result<List<UserVo>> byOrgCode = userFeignService.getByOrgCode(orgCode);
        return byOrgCode;
    }

    @ApiOperation(value = "查询所有人员银行信息", notes = "查询所有人员银行信息")
    @GetMapping(value = "/findUserBankDetail")
    @ResponseBody
    public Result<GdcPage<FsscUserVo>> findUserBankDetail(@RequestParam(value = "certNo", required = false) String certNo,
                                                          @RequestParam(value = "empNo", required = false) String empNo,
                                                          @RequestParam(value = "userName", required = false) String userName,
                                                          @RequestParam(value = "personalResident", required = false) String personalResident,
                                                          @RequestParam(value = "currentPage", defaultValue = "0") int currentPage,
                                                          @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
                                                          @RequestParam(value = "functionModule",required = false) String functionModule) {

        DeptVo currentDept = commonServices.getCurrentDept();
        List<String> personIncomeTypeCodeList = null;
        if (StringUtils.isNotBlank(functionModule)){
            List<BasePersonIncomeType> personIncomeTypeList  = personIncomeTypeService.selectByFunctionModule(functionModule,currentDept.getDeptCode());
            personIncomeTypeCodeList = personIncomeTypeList.stream().map(BasePersonIncomeType :: getTypeCode).collect(Collectors.toList());
        }
        //TODO,4A改接口
        Result<GdcPage<FsscUserVo>> gdcPageResult =
                fsscUserFeignService.queryFsscUserInfoPage(empNo, userName, personalResident,certNo ,personIncomeTypeCodeList,
                        currentPage, pageSize);
        return Result.success(gdcPageResult);
    }

    @ApiOperation(value = "当前用户信息", notes = "当前用户信息")
    @GetMapping(value = "/currentUserInfo")
    @ResponseBody
    public Result<FsscCurrentUserInfo> currentUserInfo() {
        FsscCurrentUserInfo info = new FsscCurrentUserInfo();

        DeptVo currentDept = commonServices.getCurrentDept();

        UserVo currentUser = commonServices.getCurrentUser();

        JSONArray menu = commonServices.getCurrentMenu();

        info.setUserId(StringUtil.castTolong(currentUser.getId()));
        info.setUserName(currentUser.getName());

        info.setDeptId(StringUtil.castTolong(currentDept.getDeptId()));
        info.setDeptCode(currentDept.getDeptCode());
        info.setDeptName(currentDept.getDeptName());
        DeputyAccountVo currentDeputyAccount = currentUser.getCurrentDeputyAccount();
        if (currentDeputyAccount != null) {
            info.setOrgCode(currentDeputyAccount.getOrgCode());
            info.setOrgName(currentDeputyAccount.getOrgName());
            info.setOrgId(currentDeputyAccount.getOrgId());
        }
        info.setMenu(menu);
        List<RoleVo> roleVoList = commonServices.getCurrentRoles();
        List<String> roleCodeList = roleVoList.stream().map(RoleVo :: getCode).distinct().collect(Collectors.toList());
        info.setRoleCodeList(roleCodeList);

        RoleVo currentRole = commonServices.getCurrentRole();
        info.setRoleCode(currentRole.getCode());
        info.setRoleName(currentRole.getName());
        return Result.success(info);
    }

    @ApiOperation(value = "获取功能模块", notes = "获取功能模块")
    @GetMapping(value = "/getFunctionModule")
    public Result<List<DicValueVo>> getFunctionModule() {
        List<DicValueVo> list = commonServices.findDicValueList(FsscEums.DOCUMENT_FUNCTION_MODULE.getValue());
        return Result.success(list);
    }

    @ApiOperation(value = "获取绩效指标判断条件", notes = "获取绩效指标判断条件")
    @GetMapping(value = "/getJudgeCondition")
    public Result<List<DicValueVo>> getJudgeCondition() {
        List<DicValueVo> list = commonServices.findDicValueList(FsscEums.PERFORMANCE_INDEX_JUDGE_CONDITION.getValue());
        return Result.success(list);
    }


    @ApiOperation(value = "获取所有单据类型", notes = "获取所有单据类型")
    @GetMapping(value = "/findAllDocumentType")
    public Result<List<AllDocumentTypeVo>> findAllDocumentType() {
        List<AllDocumentTypeVo> list = new ArrayList<>();
        list.add(new AllDocumentTypeVo("借款单", FsscTableNameEums.BM_BORROW_MONEY_INFO.getValue()));
        list.add(new AllDocumentTypeVo("合同预付款单", FsscTableNameEums.ADP_ADVANCE_PAYMENT_INFO.getValue()));
        list.add(new AllDocumentTypeVo("通用报账单", FsscTableNameEums.GE_GENERAL_EXPENSE.getValue()));
        list.add(new AllDocumentTypeVo("差旅申请单", FsscTableNameEums.TAS_TRAVLE_APPLY_INFO.getValue()));
        list.add(new AllDocumentTypeVo("差旅报账单", FsscTableNameEums.TAS_TRAVEL_REIMBURSE.getValue()));
        list.add(new AllDocumentTypeVo("劳务费/咨询费报账单", FsscTableNameEums.LC_LABOR_COST.getValue()));
        list.add(new AllDocumentTypeVo("款项确认单", FsscTableNameEums.PPC_PROJECT_CONFIRMATION.getValue()));
        list.add(new AllDocumentTypeVo("合同报账单", FsscTableNameEums.CRB_CONTRACT_REIMBURSE_BILL.getValue()));
        list.add(new AllDocumentTypeVo("收款单", FsscTableNameEums.REP_RECIEVE_PAYMENT.getValue()));
        list.add(new AllDocumentTypeVo("收入上缴", FsscTableNameEums.ITO_INCOME_TURNED_OVER.getValue()));
        list.add(new AllDocumentTypeVo("付款单", FsscTableNameEums.PY_PAMENT_ORDER_INFO.getValue()));
        return Result.success(list);
    }


    @ApiOperation(value = "生成二维码")
    @GetMapping(value = "createQrCode")
    public void createQrCode(String documentNum) {
        AssertUtils.asserts(StringUtil.isNotEmpty(documentNum), FsscErrorType.DOCUMENT_DATA_IS_EMPTY);
        QrCodeUtils.creatRrCode(documentNum, 200, 200);
    }
}
