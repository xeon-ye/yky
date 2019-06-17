package com.deloitte.services.srpmp.project.mpr.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.base.vo.ext.TaskNodeActionVO;
import com.deloitte.platform.api.srpmp.project.mpr.param.MprEvaBaseInfoQueryForm;
import com.deloitte.platform.api.srpmp.project.mpr.vo.*;
import com.deloitte.platform.api.srpmp.project.mpr.MprEvaBaseInfoClient;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import com.deloitte.platform.common.core.util.BeanUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.Date;
import java.util.List;

import com.deloitte.services.srpmp.project.mpr.service.IMprEvaBaseInfoService;
import com.deloitte.services.srpmp.project.mpr.entity.MprEvaBaseInfo;


/**
 * @Author : LIJUN
 * @Date : Create in 2019-03-27
 * @Description :   MprEvaBaseInfo控制器实现类
 * @Modified :
 */
@Api(tags = "中期绩效报告基本信息操作接口")
@Slf4j
@RestController
public class MprEvaBaseInfoController implements MprEvaBaseInfoClient {

    @Autowired
    public IMprEvaBaseInfoService  mprEvaBaseInfoService;

    @Override
    public Result<MprEvaBaseInfoVo> getMprBaseInfo(@PathVariable(value = "projectId") Long projectId) {
        log.info("get with projectId:{}", projectId);
        MprEvaBaseInfo baseInfo = mprEvaBaseInfoService.getMprBaseInfo(projectId);
        MprEvaBaseInfoVo baseInfoVo = new BeanUtils<MprEvaBaseInfoVo>().copyObj(baseInfo, MprEvaBaseInfoVo.class);
        return new Result<MprEvaBaseInfoVo>().sucess(baseInfoVo);
    }

    @Override
    @ApiOperation(value = "保存或者更新中期绩效报告", notes = "保存或者更新中期绩效报告")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    public Result saveOrUpdate(@Valid @RequestBody @ApiParam(name="saveOrUpdateAnnexOneForm",value="保存或者更新中期绩效报告附件一表单",required=true)
                                           MprSaveOrUpdateAnnexOneForm saveOrUpdateAnnexOneForm, UserVo user, DeptVo deptVo) {
        log.info("MprEvaBaseInfoController saveOrUpdate form:{}",  saveOrUpdateAnnexOneForm.toString());
        mprEvaBaseInfoService.saveOrUpdateAnnexOne(saveOrUpdateAnnexOneForm, user, deptVo);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "根据项目编号获取附件一", notes = "根据项目编号获取附件一")
    @ApiImplicitParam(paramType = "path", name = "projectId", value = "projectId", required = true, dataType = "long")
    public Result<MprSaveOrUpdateAnnexOneVo> getAnnexOne(@PathVariable(value = "projectId") Long projectId) {
        log.info("MprEvaBaseInfoController getAnnexOne with projectId:{}", projectId);
        return new Result<MprSaveOrUpdateAnnexOneVo>().sucess(mprEvaBaseInfoService.getAnnexOne(projectId));
    }

    @Override
    @ApiOperation(value = "导出附件一为Excel", notes = "导出附件一为Excel")
    @ApiImplicitParam(paramType = "path", name = "projectId", value = "projectId", required = true, dataType = "long")
    public Result exportAnnexOneExcel(@PathVariable(value = "projectId") Long projectId, HttpServletRequest request, HttpServletResponse response) {
        String fileUrl = mprEvaBaseInfoService.exportAnnexOneExcel(projectId);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String downName = "中国医学与健康科技创新工程项目中期绩效考评信息表.xlsx";
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
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    log.error("文件流关闭异常:", e);
                    throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage());
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    log.error("文件流关闭异常:", e);
                    throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage());
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("文件流关闭异常:", e);
                    throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage());
                }
            }
        }
        CommonUtil.deleteFile(fileUrl);
        return Result.success();
    }

    // 以下代码：wuhebiao新增

    @Override
    @ApiOperation(value = "查询年度报告详情", notes = "查询年度报告详情")
    public Result getByConditions(@Valid @RequestBody MprEvaBaseInfoQueryForm mprEvaBaseInfoQueryForm) {
        log.info("search with mprEvaBaseInfoQueryForm: {}", mprEvaBaseInfoQueryForm.toString());
        return Result.success( mprEvaBaseInfoService.getByConditions(mprEvaBaseInfoQueryForm));
    }

    @Override
    @ApiOperation(value = "判断年度报告是否存在", notes = "判断年度报告是否存在")
    public Result checkReportExists(@Valid @RequestBody MprEvaBaseInfoQueryForm mprEvaBaseInfoQueryForm) {
        log.info("search with mprEvaBaseInfoQueryForm: {}", mprEvaBaseInfoQueryForm.toString());
        return new Result().sucess(mprEvaBaseInfoService.checkReportExists(mprEvaBaseInfoQueryForm));
    }

    @Override
    @ApiParam(name = "mprEvaBaseInfoQueryForm", value = "MprEvaBaseInfo查询参数", required = true)
    @ApiOperation(value = "我的申请、我的报告、报告列表", notes = "根据条件查询我的申请、我的报告、报告列表数据")
    public Result<IPage<MprEvaBaseInfoVo>> selectList(@Valid @RequestBody MprEvaBaseInfoQueryForm mprEvaBaseInfoQueryForm, UserVo user, DeptVo dept) {
        log.info("search with mprEvaBaseInfoQueryForm: {}", mprEvaBaseInfoQueryForm.toString());
        IPage<MprEvaBaseInfoVo> voPage = mprEvaBaseInfoService.selectList(mprEvaBaseInfoQueryForm, user, dept);
        return new Result<IPage<MprEvaBaseInfoVo>>().sucess(voPage);
    }

//    @Override
//    @ApiOperation(value = "列表查询MprEvaBaseInfo", notes = "根据条件查询MprEvaBaseInfo我的报告列表数据")
//    public Result<List<MprEvaBaseInfoVo>> selectOneList(@Valid @RequestBody @ApiParam(name="mprEvaBaseInfoQueryForm",value="MprEvaBaseInfo查询参数",required=true) MprEvaBaseInfoQueryForm mprEvaBaseInfoQueryForm,
//                                                        UserVo user, DeptVo dept) {
//        log.info("search with mprEvaBaseInfoQueryForm:", mprEvaBaseInfoQueryForm.toString());
//
//        List<MprEvaBaseInfo> mprEvaBaseInfoList=mprEvaBaseInfoService.selectOneList(mprEvaBaseInfoQueryForm, user, dept);
//        List<MprEvaBaseInfoVo> mprEvaBaseInfoVoList=new BeanUtils<MprEvaBaseInfoVo>().copyObjs(mprEvaBaseInfoList,MprEvaBaseInfoVo.class);
//        return new Result<List<MprEvaBaseInfoVo>>().sucess(mprEvaBaseInfoVoList);
//    }

//    @Override
//    @ApiOperation(value = "列表查询MprEvaBaseInfo", notes = "根据条件查询MprEvaBaseInfo我的部门或所有部门报告列表数据")
//    public Result<List<MprEvaBaseInfoVo>> selectAllDeptList(@Valid @RequestBody @ApiParam(name="mprEvaBaseInfoQueryForm",value="MprEvaBaseInfo查询参数",required=true) MprEvaBaseInfoQueryForm mprEvaBaseInfoQueryForm,
//                                                            UserVo user, DeptVo dept) {
//        log.info("search with mprEvaBaseInfoQueryForm:", mprEvaBaseInfoQueryForm.toString());
//
//        List<MprEvaBaseInfo> mprEvaBaseInfoList=mprEvaBaseInfoService.selectAllDeptList(mprEvaBaseInfoQueryForm, user, dept);
//        List<MprEvaBaseInfoVo> mprEvaBaseInfoVoList=new BeanUtils<MprEvaBaseInfoVo>().copyObjs(mprEvaBaseInfoList,MprEvaBaseInfoVo.class);
//        return new Result<List<MprEvaBaseInfoVo>>().sucess(mprEvaBaseInfoVoList);
//    }


//    @Override
//    @ApiOperation(value = "分页查询MprEvaBaseInfo", notes = "根据条件查询MprEvaBaseInfo分页数据")
//    public Result<IPage<MprEvaBaseInfoVo>> search(@Valid @RequestBody @ApiParam(name="mprEvaBaseInfoQueryForm",value="MprEvaBaseInfo查询参数",required=true) MprEvaBaseInfoQueryForm mprEvaBaseInfoQueryForm,
//                                                  UserVo user, DeptVo dept) {
//        log.info("search with mprEvaBaseInfoQueryForm:", mprEvaBaseInfoQueryForm.toString());
//        IPage<MprEvaBaseInfo> mprEvaBaseInfoPage=mprEvaBaseInfoService.selectPage(mprEvaBaseInfoQueryForm, user, dept);
//        IPage<MprEvaBaseInfoVo> mprEvaBaseInfoVoPage=new BeanUtils<MprEvaBaseInfoVo>().copyPageObjs(mprEvaBaseInfoPage,MprEvaBaseInfoVo.class);
//        return new Result<IPage<MprEvaBaseInfoVo>>().sucess(mprEvaBaseInfoVoPage);
//    }

    @Override
    @ApiOperation(value = "保存或者更新年度报告", notes = "保存或者更新年度报告")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    public Result saveOrUpdate(@Valid @RequestBody MprSaveOrUpdateForm saveOrUpdateForm, UserVo user, DeptVo dept) {
        log.info("form:", saveOrUpdateForm.toString());
        long reportId = mprEvaBaseInfoService.saveOrUpdate(saveOrUpdateForm, user, dept);
        JSONObject json = new JSONObject();
        json.put("reportId", ""+reportId);

        return Result.success(json);
    }

    @Override
    @ApiOperation(value = "提交年度报告", notes = "提交年度报告")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    public Result submitReport(@Valid @RequestBody MprSaveOrUpdateForm mprSaveOrUpdateForm, UserVo user, DeptVo dept) {
        log.info("form:", mprSaveOrUpdateForm.toString());
        mprEvaBaseInfoService.submitReport(mprSaveOrUpdateForm, user, dept);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "审核通过年度报告", notes = "审核通过年度报告")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    public Result agreeReport(@Valid @RequestBody TaskNodeActionVO actionVO, DeptVo deptVo) {
        log.info("form:", actionVO.toString());
        mprEvaBaseInfoService.agreeReport(actionVO, deptVo);
        return Result.success();
    }

    @Override
    @ApiOperation(value = "拒绝年度报告", notes = "拒绝年度报告")
    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
    public Result refuseReport(@Valid @RequestBody TaskNodeActionVO actionVO) {
        log.info("form:", actionVO.toString());
        mprEvaBaseInfoService.refuseReport(actionVO);
        return Result.success();
    }

//    @Override
//    @ApiOperation(value = "驳回年度报告", notes = "驳回年度报告")
//    @ApiResponses(@ApiResponse(code = 200, message = "处理成功", response = Result.class))
//    public Result rejectReport(@Valid @RequestBody TaskNodeActionVO actionVO) {
//        log.info("form:", actionVO.toString());
//        mprEvaBaseInfoService.rejectReport(actionVO);
//        return Result.success();
//    }
}



