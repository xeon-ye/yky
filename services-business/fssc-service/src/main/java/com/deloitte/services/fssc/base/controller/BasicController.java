package com.deloitte.services.fssc.base.controller;

import com.deloitte.platform.api.isump.feign.DeptFeignService;
import com.deloitte.platform.api.isump.feign.OrganizationFeignService;
import com.deloitte.platform.api.isump.feign.UserFeignService;
import com.deloitte.platform.api.isump.param.DeptQueryForm;
import com.deloitte.platform.api.isump.param.OrganizationQueryForm;
import com.deloitte.platform.api.isump.param.UserQueryForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.fssc.common.service.FsscCommonServices;
import com.deloitte.services.fssc.common.vo.Select2DataSource;
import com.deloitte.services.fssc.eums.FsscErrorType;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.handler.FSSCException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jawjiang
 */
@Api(tags = "4A(单位、部门等)-操作接口")
@Slf4j
@RestController
@RequestMapping("/base/4A")
public class BasicController {

    @Autowired
    OrganizationFeignService orgFeignService;

    @Autowired
    DeptFeignService deptFeignService;

    @Autowired
    UserFeignService userFeignService;

    @Autowired
    FsscCommonServices commonServices;

    @ApiOperation(value = "分页查询处室信息", notes = "分页查询处室信息")
    @ApiParam(name = "queryForm", value = "处室信息查询参数", required = true)
    @PostMapping(value = "org/page/conditions")
    public Result<GdcPage<OrganizationVo>> searchOrgUnit(
            @Valid @RequestBody OrganizationQueryForm queryForm) {
        log.info("search with OrganizationQueryForm:", queryForm.toString());
        Result<GdcPage<OrganizationVo>> pageVo = orgFeignService.search2(queryForm);
        return pageVo;
    }

    @ApiOperation(value = "分页查询单位信息", notes = "分页查询单位信息")
    @ApiParam(name = "queryForm", value = "单位信息查询参数", required = true)
    @PostMapping(value = "unit/page/conditions")
    public Result<GdcPage<DeptVo>> searchUnit(
            @Valid @RequestBody DeptQueryForm queryForm) {
        log.info("search with DeptQueryForm:", queryForm.toString());
        Result<GdcPage<DeptVo>> pageVo = deptFeignService.search2(queryForm);
        return pageVo;
    }

    @ApiOperation(value = "列表查询单位信息,用于下拉框数据源", notes = "列表查询单位信息,用于下拉框数据源")
    @ApiParam(name = "queryForm", value = "单位信息查询参数", required = true)
    @PostMapping(value = "unit/listSelect/conditions")
    public Result<List<Select2DataSource>> listSelectUnit(@Valid @RequestBody DeptQueryForm queryForm) {
        log.info("list with DeptQueryForm:", queryForm.toString());
        Result<List<DeptVo>> deptResult = deptFeignService.list(queryForm);
        if (deptResult.isFail() || CollectionUtils.isEmpty(deptResult.getData())){
            throw new FSSCException(FsscErrorType.GET_DEPT_NOT_EXIST);
        }
        List<Select2DataSource> dataSourceList = new ArrayList<>(deptResult.getData().size());
        DeptVo currentDept = commonServices.getCurrentDept();
        for (DeptVo dept : deptResult.getData()){
            Select2DataSource dataSource = new Select2DataSource(dept.getId(),dept.getDeptCode(),dept.getDeptName());
            dataSourceList.add(dataSource);
        }
        Select2DataSource current = new Select2DataSource(currentDept.getId(),currentDept.getDeptCode(),
                currentDept.getDeptName());
        current.setSelected(FsscEums.YES.getValue());
        dataSourceList.remove(current);
        dataSourceList.add(current);
        return Result.success(dataSourceList);
    }

    @ApiOperation(value = "列表查询处室信息,用于下拉框数据源", notes = "列表查询处室信息,用于下拉框数据源")
    @ApiParam(name = "queryForm", value = "处室信息查询参数", required = true)
    @PostMapping(value = "org/listSelect/conditions")
    public Result<List<Select2DataSource>> listSelectOrg(@Valid @RequestBody OrganizationQueryForm queryForm) {
        log.info("list with OrganizationQueryForm:", queryForm.toString());
        Result<List<OrganizationVo>> orgResult = orgFeignService.list(queryForm);
        if (orgResult.isFail() || CollectionUtils.isEmpty(orgResult.getData())){
            throw new FSSCException(FsscErrorType.GET_DEPT_NOT_EXIST);
        }
        List<Select2DataSource> dataSourceList = new ArrayList<>(orgResult.getData().size());
        OrganizationVo currentOrg = commonServices.getCurrentOrg();
        for (OrganizationVo orgVo : orgResult.getData()){
            Select2DataSource dataSource = new Select2DataSource(orgVo.getId(),orgVo.getCode(),orgVo.getName());
            dataSourceList.add(dataSource);
        }
        Select2DataSource current = new Select2DataSource(currentOrg.getId(),currentOrg.getCode(),
                currentOrg.getName());
        current.setSelected(FsscEums.YES.getValue());
        dataSourceList.remove(current);
        dataSourceList.add(current);
        return Result.success(dataSourceList);
    }

    @ApiOperation(value = "分页查询用户信息", notes = "分页查询用户信息")
    @ApiParam(name = "queryForm", value = "单位信息查询参数", required = true)
    @PostMapping(value = "user/page/conditions")
    public Result<GdcPage<UserVo>> searchUser(
            @Valid @RequestBody UserQueryForm queryForm) {
        log.info("search with UserQueryForm:", queryForm.toString());
        Result<GdcPage<UserVo>> pageVo = userFeignService.search2(queryForm);
        return pageVo;
    }

    @ApiOperation(value = "获取当前登录账号的单位", notes = "获取当前登录账号的单位")
    @PostMapping(value = "getCurrentUnit")
    public Result<DeptVo> getCurrentDept(){
        return Result.success(commonServices.getCurrentDept());
    }

    @ApiOperation(value = "获取当前登录账号的处室", notes = "获取当前登录账号的处室")
    @PostMapping(value = "getCurrentOrg")
    public Result<DeptVo> getCurrentOrg(){
        return Result.success(commonServices.getCurrentOrg());
    }

    @ApiOperation(value = "获取当前登录账号的用户", notes = "获取当前登录账号的用户")
    @PostMapping(value = "getCurrentUser")
    public Result<DeptVo> getCurrentUser(){
        return Result.success(commonServices.getCurrentUser());
    }
}
