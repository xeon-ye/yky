package com.deloitte.services.srpmp.project.apply.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.SrpmsProjectApplySchTeachClient;
import com.deloitte.platform.api.srpmp.project.apply.param.SrpmsProjectApplySchTeachQueryForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplySchTeachForm;
import com.deloitte.platform.api.srpmp.project.apply.vo.SrpmsProjectApplySchTeachVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyAcademySaveVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplySchStuSaveVo;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplySchTeachSaveVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplySchStuService;
import com.google.common.collect.ImmutableBiMap;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplySchTeachService;
import com.deloitte.services.srpmp.project.apply.entity.SrpmsProjectApplySchTeach;


/**
 * @Author : lixin
 * @Date : Create in 2019-02-20
 * @Description :   SrpmsProjectApplySchTeach控制器实现类
 * @Modified :
 */
@Api(tags = "校基科费-教师-操作接口")
@Slf4j
@RestController
public class SrpmsProjectApplySchTeachController implements SrpmsProjectApplySchTeachClient {

    @Autowired
    public ISrpmsProjectApplySchTeachService srpmsProjectApplySchTeachService;

    @Autowired
    public ISrpmsProjectApplySchStuService srpmsProjectApplySchStuService;


    @Override
    @ApiOperation(value = "save", notes = "save")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result<Map<String, Object>> save(@RequestBody @ApiParam(name = "SrpmsProjectApplySchTeachSaveVo", value = "SrpmsProjectApplySchTeachSaveVo", required = true) SrpmsProjectApplySchTeachSaveVo vo, DeptVo dept) {
        log.info("SrpmsProjectApplySchTeachController.save", vo.toString());
        Long projectId = srpmsProjectApplySchTeachService.saveAndUpdateSchTeach(vo, dept);
        return Result.success(ImmutableBiMap.builder().put("projectId",String.valueOf(projectId)).build());
    }

    @Override
    @ApiOperation(value = "获取", notes = "获取")
    @ApiImplicitParam(paramType = "path", name = "id", value = "id", required = true, dataType = "long")
    public Result<SrpmsProjectApplySchTeachSaveVo> get(@PathVariable long id, UserVo user, DeptVo dept) {
        return new Result<SrpmsProjectApplySchTeachSaveVo>().sucess(srpmsProjectApplySchTeachService.queryApplyVoById(id, user, dept));
    }

    @Override
    @ApiOperation(value = "提交", notes = "提交")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result submit(@Valid @RequestBody @ApiParam(name = "SrpmsProjectApplySchTeachSaveVo", value = "SrpmsProjectApplySchTeachSaveVo", required = true)  SrpmsProjectApplySchTeachSaveVo vo,UserVo userVo, DeptVo deptVo) {
        srpmsProjectApplySchTeachService.submitApply(vo, userVo,deptVo);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "删除SrpmsProjectApplySchTeach", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsProjectApplySchTeachID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        srpmsProjectApplySchTeachService.removeSchTeachById(id);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "导出申请书", notes = "根据ID导出申请书")
    @ApiImplicitParam(paramType = "path", name = "id", value = "ID", required = true, dataType = "long")
    public Result export(@PathVariable long id, HttpServletRequest request, HttpServletResponse response,UserVo userVo, DeptVo deptVo) {
        String fileUrl = srpmsProjectApplySchTeachService.exportWordFile(id,userVo,deptVo);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String downName = "校基科费教师项目申请书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".docx";
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
            String fileUrl = srpmsProjectApplySchTeachService.exportPdfFile(id,userVo,deptVo);
            String downName = "校基科费教师项目申请书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".pdf";
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
    public Result<SrpmsProjectApplySchTeachSaveVo> importWord(@Valid @RequestBody @ApiParam(name = "WordImportReqVo", value = "导入申请书", required = true) WordImportReqVo reqVo) {
        SrpmsProjectApplySchTeachSaveVo vo = srpmsProjectApplySchTeachService.importWord(reqVo.getFileUrl());
        return new Result<SrpmsProjectApplySchStuSaveVo>().success(vo);
    }

    /*@Override
   @ApiOperation(value = "列表查询SrpmsProjectApplySchTeach", notes = "根据条件查询SrpmsProjectApplySchTeach列表数据")
   public Result<List<SrpmsProjectApplySchTeachVo>> list(@Valid @RequestBody @ApiParam(name = "srpmsProjectApplySchTeachQueryForm", value = "SrpmsProjectApplySchTeach查询参数", required = true) SrpmsProjectApplySchTeachQueryForm srpmsProjectApplySchTeachQueryForm) {
       List<SrpmsProjectApplySchTeach> srpmsProjectApplySchTeachList = srpmsProjectApplySchTeachService.selectList(srpmsProjectApplySchTeachQueryForm);
       List<SrpmsProjectApplySchTeachVo> srpmsProjectApplySchTeachVoList = new BeanUtils<SrpmsProjectApplySchTeachVo>().copyObjs(srpmsProjectApplySchTeachList, SrpmsProjectApplySchTeachVo.class);
       return new Result<List<SrpmsProjectApplySchTeachVo>>().sucess(srpmsProjectApplySchTeachVoList);
   }


   @Override
    @ApiOperation(value = "新增SrpmsProjectApplySchTeach", notes = "新增一个SrpmsProjectApplySchTeach")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name = "srpmsProjectApplySchTeachForm", value = "新增SrpmsProjectApplySchTeach的form表单", required = true) SrpmsProjectApplySchTeachForm srpmsProjectApplySchTeachForm) {
        log.info("form:", srpmsProjectApplySchTeachForm.toString());
        SrpmsProjectApplySchTeach srpmsProjectApplySchTeach = new BeanUtils<SrpmsProjectApplySchTeach>().copyObj(srpmsProjectApplySchTeachForm, SrpmsProjectApplySchTeach.class);
        return Result.success(srpmsProjectApplySchTeachService.save(srpmsProjectApplySchTeach));
    }




    @Override
    @ApiOperation(value = "修改SrpmsProjectApplySchTeach", notes = "修改指定SrpmsProjectApplySchTeach信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsProjectApplySchTeach的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name = "srpmsProjectApplySchTeachForm", value = "修改SrpmsProjectApplySchTeach的form表单", required = true) SrpmsProjectApplySchTeachForm srpmsProjectApplySchTeachForm) {
        SrpmsProjectApplySchTeach srpmsProjectApplySchTeach = new BeanUtils<SrpmsProjectApplySchTeach>().copyObj(srpmsProjectApplySchTeachForm, SrpmsProjectApplySchTeach.class);
        srpmsProjectApplySchTeach.setId(id);
        srpmsProjectApplySchTeachService.saveOrUpdate(srpmsProjectApplySchTeach);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取SrpmsProjectApplySchTeach", notes = "获取指定ID的SrpmsProjectApplySchTeach信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsProjectApplySchTeach的ID", required = true, dataType = "long")
    public Result<SrpmsProjectApplySchTeachVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        SrpmsProjectApplySchTeach srpmsProjectApplySchTeach = srpmsProjectApplySchTeachService.getById(id);
        SrpmsProjectApplySchTeachVo srpmsProjectApplySchTeachVo = new BeanUtils<SrpmsProjectApplySchTeachVo>().copyObj(srpmsProjectApplySchTeach, SrpmsProjectApplySchTeachVo.class);
        return new Result<SrpmsProjectApplySchTeachVo>().sucess(srpmsProjectApplySchTeachVo);
    }

    @Override
    @ApiOperation(value = "分页查询SrpmsProjectApplySchTeach", notes = "根据条件查询SrpmsProjectApplySchTeach分页数据")
    public Result<IPage<SrpmsProjectApplySchTeachVo>> search(@Valid @RequestBody @ApiParam(name = "srpmsProjectApplySchTeachQueryForm", value = "SrpmsProjectApplySchTeach查询参数", required = true) SrpmsProjectApplySchTeachQueryForm srpmsProjectApplySchTeachQueryForm) {
        log.info("search with srpmsProjectApplySchTeachQueryForm:", srpmsProjectApplySchTeachQueryForm.toString());
        IPage<SrpmsProjectApplySchTeach> srpmsProjectApplySchTeachPage = srpmsProjectApplySchTeachService.selectPage(srpmsProjectApplySchTeachQueryForm);
        IPage<SrpmsProjectApplySchTeachVo> srpmsProjectApplySchTeachVoPage = new BeanUtils<SrpmsProjectApplySchTeachVo>().copyPageObjs(srpmsProjectApplySchTeachPage, SrpmsProjectApplySchTeachVo.class);
        return new Result<IPage<SrpmsProjectApplySchTeachVo>>().sucess(srpmsProjectApplySchTeachVoPage);
    }
*/
}



