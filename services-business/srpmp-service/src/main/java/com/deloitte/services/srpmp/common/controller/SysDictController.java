package com.deloitte.services.srpmp.common.controller;

import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.common.SysDictClient;
import com.deloitte.platform.api.srpmp.common.param.SysDictQueryForm;
import com.deloitte.platform.api.srpmp.common.vo.SysDictMainTainChildVo;
import com.deloitte.platform.api.srpmp.common.vo.SysDictMainTainTreeVo;
import com.deloitte.platform.api.srpmp.common.vo.SysDictMainTainVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.common.entity.SysDict;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author : pengchao
 * @Date : Create in 2019-02-14
 * @Description :   SysDict控制器实现类
 * @Modified :
 */
@Api(value= "SysDict", description = "值列表的查询API")
@Slf4j
@RequestMapping("/srpmp/sys-dict")
@RestController
public class SysDictController implements SysDictClient {

    @Autowired
    public ISysDictService  sysDictService;

    @Override
    @ApiOperation(value = "新增基础信息节点", notes = "新增基础信息节点")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result addNode(@Valid @RequestBody @ApiParam(name="SysDictMainTainChildVo",value="新增SysDict的form表单",required=true) SysDictMainTainChildVo mainTainChildVo, UserVo user) {
        sysDictService.saveOrUpdate(mainTainChildVo, user);
        return Result.success("");
    }

    @Override
    @ApiOperation(value = "新增基础信息节点", notes = "新增基础信息节点")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result addTree(@Valid @RequestBody @ApiParam(name="SysDictMainTainChildVo",value="新增SysDict的form表单",required=true) SysDictMainTainTreeVo tainTreeVo, UserVo user) {
        sysDictService.saveTree(tainTreeVo, user);
        return Result.success("");
    }

    @Override
    @ApiOperation(value = "根据条件查询基础信息维护信息", notes = "根据条件查询基础信息维护信息")
    public Result search(@Valid @RequestBody @ApiParam(name="SysDictMainTainVo",value="SysDict查询参数",required=true) SysDictMainTainVo mainTainVo) {
        return Result.success(sysDictService.search(mainTainVo));
    }

    @Override
    @ApiOperation(value = "获取SysDict", notes = "根据传递的值列表CODE查询值列表。多个CODE时用+隔开")
    @ApiImplicitParam(paramType = "path", name = "codes", value = "SysDict的ID", required = true, dataType = "long")
    public Result getByCodes(@PathVariable String codes) {
        log.info("get with codes:{}", codes);
        return Result.success(sysDictService.selectByCodes(codes));
    }

    @Override
    @ApiOperation(value = "查询基础维护信息列表", notes = "查询基础维护信息列表")
    public Result list(@Valid @RequestBody @ApiParam(name="sysDictQueryForm",value="SysDict查询参数",required=true) SysDictQueryForm sysDictQueryForm) {
        return Result.success(sysDictService.list(sysDictQueryForm));
    }
}