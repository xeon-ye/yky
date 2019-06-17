package com.deloitte.platform.api.fssc.basecontract.client;

import com.deloitte.platform.api.contract.vo.BasicInfoVoToFssc;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-17
 * @Description :  BaseContractInfo控制器接口
 * @Modified :
 */
public interface BaseContractInfoClient {

    public static final String path = "/basecontract/base-contract-info/";

    @ApiOperation(value = "接收合同基本信息", notes = "接收合同基本信息")
    @PostMapping(value = path + "/receiveBasic")
    Result receive(@Valid @RequestBody
               @ApiParam(name = "baseContractInfoForm", value = "新增BaseContractInfo的form表单", required = true)
                       List<BasicInfoVoToFssc> basicInfoVos);

    @ApiOperation(value = "接收履行计划", notes = "接收履行计划")
    @PostMapping(value = path + "/receiveFinance")
    Result receiveFinance(@Valid @RequestBody
                          @ApiParam(name = "BasicInfoVo", value = "BasicInfoVo", required = true)
                                  BasicInfoVoToFssc basicInfoVo);


    /**
     * 是否能修改履行行 data为false 不能修改  true可以修改
     * @param financeId
     * @return
     */
    @ApiOperation(value = "是否能修改合同", notes = "是否能修改合同")
    @GetMapping(value = path + "/canModifyContract")
    Result<Boolean> canModifyContract(@RequestParam("financeId") Long financeId);

}
