package com.deloitte.services.srpmp.project.apply.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.SrpmsProjectApplySchStuClient;
import com.deloitte.platform.api.srpmp.project.apply.param.SrpmsProjectApplySchStuQueryForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplySchStuForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplySchStuVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyInnPreSubmitVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplySchStuSaveVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplySchTeachSaveVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplySchStu;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplySchStuService;
import com.google.common.collect.ImmutableBiMap;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.Date;
import java.util.List;


/**
 * @Author : caoyue
 * @Date : Create in 2019-02-21
 * @Description :   SrpmsProjectApplySchStu控制器实现类
 * @Modified :
 */
@Api(tags = "SrpmsProjectApplySchStu操作接口")
@Slf4j
@RestController
public class SrpmsProjectApplySchStuController implements SrpmsProjectApplySchStuClient {

    @Autowired
    public ISrpmsProjectApplySchStuService  srpmsProjectApplySchStuService;


    @Override
    @ApiOperation(value = "保存", notes = "保存")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result save(@RequestBody @ApiParam(name="srpmsProjectApplySchStuSaveVo",value="新增SrpmsProjectApplySchStu的form表单",required=true) SrpmsProjectApplySchStuSaveVo vo, DeptVo deptVo) {
        Long projectId = srpmsProjectApplySchStuService.saveOrUpdateApplySchStu(vo, deptVo);
        return Result.success(ImmutableBiMap.builder().put("projectId",String.valueOf(projectId)).build());
    }


    @Override
    @ApiOperation(value = "提交", notes = "提交")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result submit(@Valid @RequestBody @ApiParam(name="srpmsProjectApplySchStuSaveVo",value="新增SrpmsProjectApplySchStu的form表单",required=true) SrpmsProjectApplySchStuSaveVo vo, UserVo userVo, DeptVo deptVo) {
        srpmsProjectApplySchStuService.submitApply(vo, userVo, deptVo);
        return Result.success();
    }


//    @Override
//    @ApiOperation(value = "删除SrpmsProjectApplySchStu", notes = "根据url的id来指定删除对象")
//    @ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsProjectApplySchStuID", required = true, dataType = "long")
//    public Result delete(@PathVariable long id) {
//        srpmsProjectApplySchStuService.deleteApplyById(id);
//        return Result.success();
//    }


    @Override
    @ApiOperation(value = "获取SrpmsProjectApplySchStu", notes = "获取指定ID的SrpmsProjectApplySchStu信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsProjectApplySchStu的ID", required = true, dataType = "long")
    public Result<SrpmsProjectApplySchStuSaveVo> get(@PathVariable Long id, UserVo user, DeptVo dept) {
        return new Result<SrpmsProjectApplySchStuSaveVo>().sucess(srpmsProjectApplySchStuService.queryApplyVoById(id, user, dept));
    }

//    @Override
//    @ApiOperation(value = "列表查询SrpmsProjectApplySchStu", notes = "根据条件查询SrpmsProjectApplySchStu列表数据")
//    public Result<List<SrpmsProjectApplySchStuVo>> list(@Valid @RequestBody @ApiParam(name="srpmsProjectApplySchStuQueryForm",value="SrpmsProjectApplySchStu查询参数",required=true) SrpmsProjectApplySchStuQueryForm srpmsProjectApplySchStuQueryForm) {
//        log.info("search with srpmsProjectApplySchStuQueryForm:", srpmsProjectApplySchStuQueryForm.toString());
//        List<SrpmsProjectApplySchStu> srpmsProjectApplySchStuList=srpmsProjectApplySchStuService.selectList(srpmsProjectApplySchStuQueryForm);
//        List<SrpmsProjectApplySchStuVo> srpmsProjectApplySchStuVoList=new BeanUtils<SrpmsProjectApplySchStuVo>().copyObjs(srpmsProjectApplySchStuList,SrpmsProjectApplySchStuVo.class);
//        return new Result<List<SrpmsProjectApplySchStuVo>>().sucess(srpmsProjectApplySchStuVoList);
//    }


//    @Override
//    @ApiOperation(value = "分页查询SrpmsProjectApplySchStu", notes = "根据条件查询SrpmsProjectApplySchStu分页数据")
//    public Result<IPage<SrpmsProjectApplySchStuVo>> search(@Valid @RequestBody @ApiParam(name="srpmsProjectApplySchStuQueryForm",value="SrpmsProjectApplySchStu查询参数",required=true) SrpmsProjectApplySchStuQueryForm srpmsProjectApplySchStuQueryForm) {
//        log.info("search with srpmsProjectApplySchStuQueryForm:", srpmsProjectApplySchStuQueryForm.toString());
//        IPage<SrpmsProjectApplySchStu> srpmsProjectApplySchStuPage=srpmsProjectApplySchStuService.selectPage(srpmsProjectApplySchStuQueryForm);
//        IPage<SrpmsProjectApplySchStuVo> srpmsProjectApplySchStuVoPage=new BeanUtils<SrpmsProjectApplySchStuVo>().copyPageObjs(srpmsProjectApplySchStuPage,SrpmsProjectApplySchStuVo.class);
//        return new Result<IPage<SrpmsProjectApplySchStuVo>>().sucess(srpmsProjectApplySchStuVoPage);
//    }

    @Override
    @ApiOperation(value = "导出申请书", notes = "根据ID导出申请书")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")
    public Result export(@PathVariable long id, HttpServletRequest request, HttpServletResponse response,UserVo userVo, DeptVo deptVo) {
        String fileUrl = srpmsProjectApplySchStuService.exportWordFile(id,userVo,deptVo);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String downName = "校基科费学生项目申请书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".docx";
            response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(downName, "UTF-8"));
            is = new FileInputStream(fileUrl);
            bis = new BufferedInputStream(is);
            os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            log.error("文件读取异常！", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage(), e.getCause());
        } finally {
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(is);
        }
        return Result.success();
    }

    @Override
    @ApiOperation(value = "导出申请书", notes = "根据ID导出申请书")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")
    public Result exportPdf(@PathVariable long id, HttpServletRequest request, HttpServletResponse response,UserVo userVo, DeptVo deptVo) {

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String fileUrl = srpmsProjectApplySchStuService.exportPdfFile(id,userVo,deptVo);
            String downName = "校基科费学生项目申请书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".pdf";
            response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(downName, "UTF-8"));
            is = new FileInputStream(fileUrl);
            bis = new BufferedInputStream(is);
            os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            log.error("文件读取异常！", e);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage(), e.getCause());
        } finally {
            IOUtils.closeQuietly(os);
            IOUtils.closeQuietly(bis);
            IOUtils.closeQuietly(is);
        }
        return Result.success();
    }

    @ApiOperation(value = "导入项目申请书", notes = "导入项目申请书")
    @Override
    public Result<SrpmsProjectApplySchStuSaveVo> importWord(@Valid @RequestBody @ApiParam(name = "WordImportReqVo", value = "导入申请书", required = true) WordImportReqVo reqVo) {
        SrpmsProjectApplySchStuSaveVo vo = srpmsProjectApplySchStuService.importWord(reqVo.getFileUrl());
        return new Result<SrpmsProjectApplySchStuSaveVo>().success(vo);
    }
}



