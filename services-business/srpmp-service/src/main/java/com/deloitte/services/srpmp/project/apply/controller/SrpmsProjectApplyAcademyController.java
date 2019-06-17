package com.deloitte.services.srpmp.project.apply.controller;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.apply.SrpmsProjectApplyAcademyClient;
import com.deloitte.platform.api.srpmp.project.apply.vo.ext.SrpmsProjectApplyAcademySaveVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.WordImportReqVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.project.apply.service.ISrpmsProjectApplyAcademyService;
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


/**
 * @Author : lixin
 * @Date : Create in 2019-02-25
 * @Description :   SrpmsProjectApplyAcademy控制器实现类
 * @Modified :
 */
@Api(tags = "院基科费操作接口")
@Slf4j
@RestController
public class SrpmsProjectApplyAcademyController implements SrpmsProjectApplyAcademyClient {

    @Autowired
    public ISrpmsProjectApplyAcademyService  srpmsProjectApplyAcademyService;

    @Override
    @ApiOperation(value = "保存院基科费", notes = "保存院基科费")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result save(@RequestBody @ApiParam(name = "SrpmsProjectApplyAcademyController",required = true) SrpmsProjectApplyAcademySaveVo vo, DeptVo deptVo) {
        log.info("SrpmsProjectApplyAcademySaveVo.save",vo.toString() );
        Long projectId =srpmsProjectApplyAcademyService.saveAndUpdateAcademy(vo, deptVo);
        return Result.success(ImmutableBiMap.builder().put("projectId",String.valueOf(projectId)).build());
    }

    @Override
    @ApiOperation(value = "提交院基科费", notes = "提交院基科费")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result submit(@Valid @RequestBody @ApiParam(name = "SrpmsProjectApplyAcademyController",required = true) SrpmsProjectApplyAcademySaveVo vo, UserVo userVo, DeptVo deptVo) {
        srpmsProjectApplyAcademyService.submitApply(vo, userVo, deptVo);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "删除SrpmsProjectApplyAcademy", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsProjectApplyAcademyID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        srpmsProjectApplyAcademyService.deleteAcademyById(id);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "根据ID查询", notes = "根据ID查询")
    @ApiImplicitParam(paramType = "path", name = "id", value = "id", required = true, dataType = "long")
    public Result<SrpmsProjectApplyAcademySaveVo> get(@PathVariable long id, UserVo user, DeptVo dept) {
        return new Result<SrpmsProjectApplyAcademySaveVo>().sucess(srpmsProjectApplyAcademyService.queryApplyVoById(id, user, dept));
    }

    /* @Override
    @ApiOperation(value = "新增SrpmsProjectApplyAcademy", notes = "新增一个SrpmsProjectApplyAcademy")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result add(@Valid @RequestBody @ApiParam(name="srpmsProjectApplyAcademyForm",value="新增SrpmsProjectApplyAcademy的form表单",required=true)  SrpmsProjectApplyAcademyForm srpmsProjectApplyAcademyForm) {
        log.info("form:",  srpmsProjectApplyAcademyForm.toString());
        SrpmsProjectApplyAcademy srpmsProjectApplyAcademy =new BeanUtils<SrpmsProjectApplyAcademy>().copyObj(srpmsProjectApplyAcademyForm,SrpmsProjectApplyAcademy.class);
        return Result.success( srpmsProjectApplyAcademyService.save(srpmsProjectApplyAcademy));
    }


    @Override
    @ApiOperation(value = "删除SrpmsProjectApplyAcademy", notes = "根据url的id来指定删除对象")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsProjectApplyAcademyID", required = true, dataType = "long")
    public Result delete(@PathVariable long id) {
        srpmsProjectApplyAcademyService.removeById(id);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "修改SrpmsProjectApplyAcademy", notes = "修改指定SrpmsProjectApplyAcademy信息")
    @ApiImplicitParam(paramType = "path",name = "id", value = "SrpmsProjectApplyAcademy的ID", required = true, dataType = "long")
    public Result update(@PathVariable long id,
                         @Valid @RequestBody @ApiParam(name="srpmsProjectApplyAcademyForm",value="修改SrpmsProjectApplyAcademy的form表单",required=true)  SrpmsProjectApplyAcademyForm srpmsProjectApplyAcademyForm) {
        SrpmsProjectApplyAcademy srpmsProjectApplyAcademy =new BeanUtils<SrpmsProjectApplyAcademy>().copyObj(srpmsProjectApplyAcademyForm,SrpmsProjectApplyAcademy.class);
        srpmsProjectApplyAcademy.setId(id);
        srpmsProjectApplyAcademyService.saveOrUpdate(srpmsProjectApplyAcademy);
        return Result.success();
    }


    @Override
    @ApiOperation(value = "获取SrpmsProjectApplyAcademy", notes = "获取指定ID的SrpmsProjectApplyAcademy信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "SrpmsProjectApplyAcademy的ID", required = true, dataType = "long")
    public Result<SrpmsProjectApplyAcademyVo> get(@PathVariable long id) {
        log.info("get with id:{}", id);
        SrpmsProjectApplyAcademy srpmsProjectApplyAcademy=srpmsProjectApplyAcademyService.getById(id);
        SrpmsProjectApplyAcademyVo srpmsProjectApplyAcademyVo=new BeanUtils<SrpmsProjectApplyAcademyVo>().copyObj(srpmsProjectApplyAcademy,SrpmsProjectApplyAcademyVo.class);
        return new Result<SrpmsProjectApplyAcademyVo>().sucess(srpmsProjectApplyAcademyVo);
    }

    @Override
    @ApiOperation(value = "列表查询SrpmsProjectApplyAcademy", notes = "根据条件查询SrpmsProjectApplyAcademy列表数据")
    public Result<List<SrpmsProjectApplyAcademyVo>> list(@Valid @RequestBody @ApiParam(name="srpmsProjectApplyAcademyQueryForm",value="SrpmsProjectApplyAcademy查询参数",required=true) SrpmsProjectApplyAcademyQueryForm srpmsProjectApplyAcademyQueryForm) {
        log.info("search with srpmsProjectApplyAcademyQueryForm:", srpmsProjectApplyAcademyQueryForm.toString());
        List<SrpmsProjectApplyAcademy> srpmsProjectApplyAcademyList=srpmsProjectApplyAcademyService.selectList(srpmsProjectApplyAcademyQueryForm);
        List<SrpmsProjectApplyAcademyVo> srpmsProjectApplyAcademyVoList=new BeanUtils<SrpmsProjectApplyAcademyVo>().copyObjs(srpmsProjectApplyAcademyList,SrpmsProjectApplyAcademyVo.class);
        return new Result<List<SrpmsProjectApplyAcademyVo>>().sucess(srpmsProjectApplyAcademyVoList);
    }


    @Override
    @ApiOperation(value = "分页查询SrpmsProjectApplyAcademy", notes = "根据条件查询SrpmsProjectApplyAcademy分页数据")
    public Result<IPage<SrpmsProjectApplyAcademyVo>> search(@Valid @RequestBody @ApiParam(name="srpmsProjectApplyAcademyQueryForm",value="SrpmsProjectApplyAcademy查询参数",required=true) SrpmsProjectApplyAcademyQueryForm srpmsProjectApplyAcademyQueryForm) {
        log.info("search with srpmsProjectApplyAcademyQueryForm:", srpmsProjectApplyAcademyQueryForm.toString());
        IPage<SrpmsProjectApplyAcademy> srpmsProjectApplyAcademyPage=srpmsProjectApplyAcademyService.selectPage(srpmsProjectApplyAcademyQueryForm);
        IPage<SrpmsProjectApplyAcademyVo> srpmsProjectApplyAcademyVoPage=new BeanUtils<SrpmsProjectApplyAcademyVo>().copyPageObjs(srpmsProjectApplyAcademyPage,SrpmsProjectApplyAcademyVo.class);
        return new Result<IPage<SrpmsProjectApplyAcademyVo>>().sucess(srpmsProjectApplyAcademyVoPage);
    }*/

    /**
     * tanwx
     * @param id
     * @param request
     * @param response
     * @param vo
     * @param deptVo
     * @return
     */
    @Override
    @ApiOperation(value = "导出申请书",notes = "根据ID导出申请书")
    @ApiImplicitParam(paramType = "path",name = "id",value = "id",required = true,dataType = "long")
    public Result export(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {
        String fileUrl = srpmsProjectApplyAcademyService.exportWordFile(id,userVo,deptVo);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;

        try {
            String downName = "医科院基科费项目申请书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".docx";
            if (id != null && id.equals(0L)) {
                downName = "医科院基科费项目申请书模板.docx";
            }
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
    @ApiOperation(value = "导出申请书",notes = "根据ID导出申请书")
    @ApiImplicitParam(paramType = "path",name = "id",value = "id",required = true,dataType = "long")
    public Result exportPdf(@PathVariable Long id, HttpServletRequest request, HttpServletResponse response, UserVo userVo, DeptVo deptVo) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String fileUrl = srpmsProjectApplyAcademyService.exportPdfFile(id,userVo,deptVo);
            String downName = "医科院基科费项目申请书_" + DateFormatUtils.format(new Date(), "yyyyMMdd")+".pdf";
            if (id != null && id.equals(0L)) {
                downName = "医科院基科费项目申请书模板.pdf";
            }
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
    @ApiOperation(value = "导入项目申请书", notes = "导入项目申请书")
    public Result<SrpmsProjectApplyAcademySaveVo> importWord(@Valid @RequestBody  @ApiParam(name = "WordImportReqVo", value = "导入申请书", required = true) WordImportReqVo reqVo){
        SrpmsProjectApplyAcademySaveVo vo = srpmsProjectApplyAcademyService.importWord(reqVo.getFileUrl());
        return new Result<SrpmsProjectApplyAcademySaveVo>().success(vo);
    }
}



