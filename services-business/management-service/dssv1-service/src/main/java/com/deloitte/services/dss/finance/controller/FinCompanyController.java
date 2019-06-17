package com.deloitte.services.dss.finance.controller;

import com.deloitte.platform.api.dss.finance.vo.FinCompanyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.dss.finance.service.IFinCompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "1.财务 机构列表")
@Slf4j
@RestController
@RequestMapping("/dss/finance/company")
public class FinCompanyController {

    @Autowired
    private IFinCompanyService iFinCompanyService;


    @ApiOperation(value = "单位目录",notes = "单位目录")
    @PostMapping("/selectCompany")
    public Result selectCompany (){
        List<FinCompanyVo> finCompanyVos = iFinCompanyService.selectCompany();
        Result success = Result.success(finCompanyVos);
        return success;
    }
}
