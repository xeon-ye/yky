package com.deloitte.services.contract.controller;

import com.deloitte.platform.api.contract.param.BasicInfoQueryForm;
import com.deloitte.platform.api.contract.vo.BasicInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.contract.service.IBasicInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Api(tags = "7-合同报表操作接口")
@Slf4j
@RestController
@RequestMapping("/ContractReport")
public class ContractReportController {

    @Autowired
    private IBasicInfoService iBasicInfoService;

    @ApiOperation(value = "7-1-1合同台账查询", notes = "7-1-1合同台账查询")
    @PostMapping("/selectContractLedger")
    public Result<BasicInfoVo> selectContractLedger(@Valid @RequestBody BasicInfoQueryForm basicInfoQueryForm){
        BasicInfoVo basicInfoVo = new BasicInfoVo();
        List<BasicInfoVo> list = iBasicInfoService.selectContractLedger(basicInfoQueryForm);
        basicInfoVo.setListBasicInfoVo(list);
        String total = iBasicInfoService.selectContractLedgerCount(basicInfoQueryForm).toString();
        basicInfoVo.setTotal(total);
        return new Result<BasicInfoVo>().sucess(basicInfoVo);
    }

    @ApiOperation(value = "7-1-2合同台账导出", notes = "7-1-2合同台账导出")
    @PostMapping("/exportContractLedger")
    public Result exportContractLedger(@Valid @RequestBody BasicInfoQueryForm basicInfoQueryForm){
        return iBasicInfoService.exportContractLedger(basicInfoQueryForm);
    }

    @ApiOperation(value = "7-4-1 倒签合同统计", notes = "7-4-1 倒签合同统计")
    @PostMapping("/selectBackDating")
    public Result<BasicInfoVo> selectBackDating(@Valid @RequestBody BasicInfoQueryForm basicInfoQueryForm){

        return new Result<BasicInfoVo>().sucess(iBasicInfoService.selectNoBackDating(basicInfoQueryForm));
    }

    @ApiOperation(value = "7-3-1 审签意见统计", notes = "7-3-1 审签意见统计")
    @PostMapping("/selectApprovalOpinion")
    public Result<BasicInfoVo> selectApprovalOpinion(@Valid @RequestBody BasicInfoQueryForm basicInfoQueryForm){

        return new Result<BasicInfoVo>().sucess(iBasicInfoService.selectApprovalOpinion(basicInfoQueryForm));
    }

    @ApiOperation(value = "7-2-1履行状况报表", notes = "7-2-1履行状况报表")
    @PostMapping("/selectExecuteStatue")
    public Result<BasicInfoVo> selectExecuteStatue(@Valid @RequestBody BasicInfoQueryForm basicInfoQueryForm){
        return new Result<BasicInfoVo>().sucess(iBasicInfoService.selectExecuteStatue(basicInfoQueryForm));
    }
}
