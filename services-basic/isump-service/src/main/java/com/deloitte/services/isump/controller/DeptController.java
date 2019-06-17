package com.deloitte.services.isump.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.param.AttachmentQueryForm;
import com.deloitte.platform.api.isump.param.DeptQueryForm;
import com.deloitte.platform.api.isump.vo.AttachmentVo;
import com.deloitte.platform.api.isump.vo.DeptForm;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.DeptClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.isump.entity.Attachment;
import com.deloitte.services.isump.entity.User;
import com.deloitte.services.isump.service.IAttachmentService;
import com.deloitte.services.isump.service.IBpmService;
import io.swagger.annotations.*;
import java.util.HashMap;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

import com.deloitte.services.isump.service.IDeptService;
import com.deloitte.services.isump.entity.Dept;


/**
 * @Author : jianglong
 * @Date : Create in 2019-03-01
 * @Description :   Dept控制器实现类
 * @Modified :
 */
@Api(tags = "Dept操作接口")
@Slf4j
@RestController
public class DeptController implements DeptClient {

    @Autowired
    public IDeptService deptService;
    @Autowired
    public IAttachmentService attachmentService;

    @Override
    @ApiOperation(value = "新增Dept", notes = "新增一个Dept")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(
            @Valid @RequestBody @ApiParam(name = "deptForm", value = "新增Dept的form表单", required = true) DeptForm deptForm) {
        log.info("form:", deptForm.toString());
        return Result.success(deptService.save2(deptForm));
    }

    @Override
    @ApiOperation(value = "启用单位", notes = "根据单位的id来启用单位")
    @ApiImplicitParam(paramType = "path", name = "id", value = "DeptID", required = true, dataType = "long")
    public Result deptEnabled(@PathVariable long id) {
        deptService.deptEnabled(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "删除Dept", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "DeptID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        deptService.removeById(id);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "修改Dept", notes = "修改指定Dept信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Dept的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
            @Valid @RequestBody @ApiParam(name = "deptForm", value = "修改Dept的form表单", required = true) DeptForm deptForm) {
        Dept dept = new BeanUtils<Dept>().copyObj(deptForm, Dept.class);
        dept.setId(id);
        deptService.saveOrUpdate(dept);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "获取Dept", notes = "获取指定ID的Dept信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "Dept的ID", required = true, dataType = "long")
    public Result<DeptVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        Dept dept = deptService.getById(id);
        DeptVo deptVo = new BeanUtils<DeptVo>().copyObj(dept, DeptVo.class);
        //查询附件
        AttachmentQueryForm attachmentQueryForm = new AttachmentQueryForm();
        attachmentQueryForm.setMasterId(Long.parseLong(deptVo.getId()));
        attachmentQueryForm.setMasterType("ISUMP_DEPT");
        List<Attachment> attachmentList=attachmentService.selectList(attachmentQueryForm);
        List<AttachmentVo> attachmentVoList=new BeanUtils<AttachmentVo>().copyObjs(attachmentList,AttachmentVo.class);
        deptVo.setAttachments(attachmentVoList);
        return new Result<DeptVo>().sucess(deptVo);
    }

    @Override
    @ApiOperation(value = "获取Dept", notes = "获取CODE的Dept信息")
    @ApiImplicitParam(paramType = "path", name = "code", value = "Dept的CODE", required = true, dataType = "String")
    public Result<DeptVo> getByCode(@PathVariable(value = "code") String code) {
        log.info("get with code:{}", code);
        DeptVo dept = deptService.getByCode(code);
        return new Result<DeptVo>().sucess(dept);
    }

    @Override
    @ApiOperation(value = "列表查询Dept", notes = "根据条件查询Dept列表数据")
    public Result<List<DeptVo>> list(
            @Valid @RequestBody @ApiParam(name = "deptQueryForm", value = "Dept查询参数", required = true) DeptQueryForm deptQueryForm) {
        log.info("search with deptQueryForm:", deptQueryForm.toString());
        List<Dept> deptList = deptService.selectList(deptQueryForm);
        List<DeptVo> deptVoList = new BeanUtils<DeptVo>().copyObjs(deptList, DeptVo.class);
        return new Result<List<DeptVo>>().sucess(deptVoList);
    }


    @Override
    @ApiOperation(value = "分页查询Dept", notes = "根据条件查询Dept分页数据")
    public Result<IPage<DeptVo>> search(
            @Valid @RequestBody @ApiParam(name = "deptQueryForm", value = "Dept查询参数", required = true) DeptQueryForm deptQueryForm) {
        log.info("search with deptQueryForm:", deptQueryForm.toString());
        IPage<Dept> deptPage = deptService.selectPage(deptQueryForm);
        IPage<DeptVo> deptVoPage = new BeanUtils<DeptVo>().copyPageObjs(deptPage, DeptVo.class);
        return new Result<IPage<DeptVo>>().sucess(deptVoPage);
    }


    @Override
    @ApiOperation(value = "分页查询Dept", notes = "根据条件查询Dept分页数据")
    @ApiParam(name = "deptQueryForm", value = "Dept查询参数", required = true)
    public Result<GdcPage<DeptVo>> search2(@Valid @RequestBody DeptQueryForm deptQueryForm) {
        log.info("search with deptQueryForm:", deptQueryForm.toString());
        IPage<Dept> deptPage = deptService.selectPage(deptQueryForm);
        IPage<DeptVo> deptVoPage = new BeanUtils<DeptVo>().copyPageObjs(deptPage, DeptVo.class);
        return new Result<GdcPage<DeptVo>>().sucess(new GdcPage<DeptVo>(deptVoPage));
    }

    @Override
    @ApiOperation(value = "查询code和name的映射", notes = "查询code和name的映射")
    @ApiParam(name = "codeList", value = "查询参数", required = true)
    public Result<HashMap<String, String>> searchNameByCodes(@RequestBody List<String> codeList) {
        log.info("search with codeList:", JSON.toJSONString(codeList));
        QueryWrapper<Dept> queryWrapper = new QueryWrapper<>();
        queryWrapper.in(Dept.DEPT_CODE,codeList);
        List<Dept> deptList = deptService.list(queryWrapper);
        HashMap<String,String> codeNameMap = new HashMap<>(codeList.size());
        for(Dept dept : deptList){
            codeNameMap.put(dept.getDeptCode(),dept.getDeptName());
        }
        return Result.success(codeNameMap);
    }

    @Override
    public Result<HashMap<String, String>> searchCodeAndName(@RequestBody DeptQueryForm deptQueryForm) {
        log.info("search with deptQueryForm:", JSON.toJSONString(deptQueryForm));
        List<Dept> deptList = deptService.selectList(deptQueryForm);
        HashMap<String,String> codeNameMap = new HashMap<>();
        for(Dept dept : deptList){
            codeNameMap.put(dept.getDeptCode(),dept.getDeptName());
        }
        return Result.success(codeNameMap);
    }

    @Override
    @ApiOperation(value = "获取Dept", notes = "获取CODE的Dept信息")
    @ApiImplicitParam(paramType = "path", name = "name", value = "Dept的name", required = true, dataType = "String")
    public Result<DeptVo> getDeptByName(@PathVariable String name) {
        return Result.success(deptService.getByName(name));
    }

}



