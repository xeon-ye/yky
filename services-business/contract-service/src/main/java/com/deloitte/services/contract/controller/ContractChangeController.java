package com.deloitte.services.contract.controller;

import com.deloitte.platform.api.contract.vo.BasicInfoForm;
import com.deloitte.platform.api.contract.vo.BasicInfoVo;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.contract.entity.BasicInfo;
import com.deloitte.services.contract.service.IBasicInfoService;
import com.deloitte.services.contract.service.ICommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "5-合同变更操作接口")
@Slf4j
@RestController
@RequestMapping("/contractChange")
public class ContractChangeController {

    @Autowired
    private IBasicInfoService iBasicInfoService;
    @Autowired
    private ICommonService commonService;

    /**
     * yangyuanqing
     * @param basicInfoForm : id; relationCode; relation
     * @return
     */
    @ApiOperation(value = "5-1-1获取变更合同信息", notes = "5-1-1获取变更合同信息")
    @PostMapping("/getChangeContract")
    public Result<BasicInfoVo> getChangeContract(@Valid @RequestBody BasicInfoForm basicInfoForm){
        BasicInfoVo result = iBasicInfoService.getChangeContract(basicInfoForm);
        return new Result<BasicInfoVo>().sucess(result);
    }

    /**
     * yangyuanqing
     * @param basicInfoForm
     * @return
     */
    @ApiOperation(value = "5-1-2保存变更合同信息", notes = "5-1-2保存变更合同信息")
    @PostMapping("/insertChangeContract")
    public Result<BasicInfoVo> insertChangeContract(@Valid @RequestBody BasicInfoForm basicInfoForm){
        UserVo userVo = commonService.getCurrentUser();
        OrganizationVo organizationVo = commonService.getOrganization();
        BasicInfo result = iBasicInfoService.insertChangeContract(basicInfoForm, userVo, organizationVo);
        BasicInfoVo basicInfoVo =new BeanUtils<BasicInfoVo>().copyObj(result, BasicInfoVo.class);
        return new Result<BasicInfoVo>().sucess(basicInfoVo);
    }
}
