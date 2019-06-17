package com.deloitte.services.srpmp.project.accept.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.project.accept.client.SrpmsProjectAcceptClient;
import com.deloitte.platform.api.srpmp.project.accept.param.SrpmsProjectAcceptQueryForm;
import com.deloitte.platform.api.srpmp.project.accept.vo.SrpmsProjectAcceptForm;
import com.deloitte.platform.api.srpmp.project.accept.vo.SrpmsProjectAcceptVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.common.util.WordConvertUtil;
import com.deloitte.services.srpmp.project.accept.service.ISrpmsProjectAcceptService;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskAcademy;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskReform;
import com.deloitte.services.srpmp.project.task.entity.SrpmsProjectTaskSchStudent;
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
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;


/**
 * @Author : Apeng
 * @Date : Create in 2019-04-25
 * @Description :   SrpmsProjectAccept控制器实现类
 * @Modified :
 */
@Api(tags = "项目验收操作接口", value = "项目验收操作接口")
@Slf4j
@RestController
public class SrpmsProjectAcceptController implements SrpmsProjectAcceptClient {

    @Autowired
    public ISrpmsProjectAcceptService srpmsProjectAcceptService;

    /**
     * 根据ID查询验收报告数据接口
     *
     * @param id
     * @return
     */
    @Override
    @ApiOperation(value = "根据ID获取验收报告接口", notes = "根据ID获取验收报告接口")
    public Result<SrpmsProjectAcceptVo> get(@PathVariable long id) {
        return Result.success(srpmsProjectAcceptService.queryById(id));
    }

    /**
     * 查询验收报告分页数据接口
     *
     * @param form
     * @param user
     * @param dept
     * @return
     */
    @Override
    @ApiOperation(value = "分页查询验收数据接口", notes = "根据条件查询验收分页数据")
    public Result<IPage<SrpmsProjectAcceptVo>> search(@Valid @RequestBody @ApiParam(name = "srpmsProjectAcceptQueryForm", value = "SrpmsProjectAccept查询参数", required = true) SrpmsProjectAcceptQueryForm form, UserVo user, DeptVo dept) {
        return new Result<IPage<SrpmsProjectAcceptVo>>().success(srpmsProjectAcceptService.selectPage(form, user, dept));
    }

    @Override
    @ApiOperation(value = "项目验收催告接口", notes = "项目验收催告接口")
    public Result projectAcceptRemain() {
        srpmsProjectAcceptService.projectAcceptRemain();
        return Result.success("");
    }

    /**
     * 新增项目验收跳转界面接口
     *
     * @param form
     * @param user
     * @return
     */
    @Override
    @ApiOperation(value = "新增项目验收跳转界面接口", notes = "新增项目验收跳转界面接口")
    public Result searchNewVersion(@Valid SrpmsProjectAcceptQueryForm form, UserVo user) {
        return Result.success(srpmsProjectAcceptService.searchNewVersion(form));
    }

    /**
     * 保存项目验证报告接口
     *
     * @param form
     * @param user
     * @param dept
     * @return
     */
    @Override
    @ApiOperation(value = "保存项目验证报告接口", notes = "保存项目验证报告接口")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result save(@RequestBody @ApiParam(name = "SrpmsProjectAcceptForm", value = "新增SrpmsProjectAccept的form表单", required = true) SrpmsProjectAcceptForm form, UserVo user, DeptVo dept) {
        return Result.success(srpmsProjectAcceptService.saveOrUpdate(form, user, dept, false));
    }

    /**
     * 提交项目验证报告接口
     *
     * @param form
     * @param user
     * @param dept
     * @return
     */
    @Override
    @ApiOperation(value = "提交项目验证报告接口", notes = "提交项目验证报告接口")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result submit(@RequestBody @ApiParam(name = "SrpmsProjectAcceptForm", value = "新增SrpmsProjectAccept的form表单", required = true) SrpmsProjectAcceptForm form, UserVo user, DeptVo dept) {
        srpmsProjectAcceptService.submit(form, user, dept);
        return Result.success("");
    }

    @Override
    @ApiOperation(value = "项目是否已经执行验收操作", notes = "项目是否已经执行验收操作")
    public Result searchAcceptExists(@Valid SrpmsProjectAcceptQueryForm form, UserVo user) {
        srpmsProjectAcceptService.searchAcceptExists(form, user);
        return Result.success("");
    }

    /**
     * 导出验收报告pdf文件
     *
     * @param id
     * @param projectType
     * @param response
     * @return
     */
    @Override
    @ApiOperation(value = "导出项目验收报告", notes = "根据ID导出项目验收报告")
    public Result exportPdf(@PathVariable Long id, String projectType, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            String fileUrl = srpmsProjectAcceptService.exportPdfFile(id);
            String title = "";
            switch (projectType) {// 项目类型
                case SrpmsConstant.MODIFY_PRO_TYPE_10010201:// 院基科费
                    title = "医科院院基科费验收报告_";
                    break;
                case SrpmsConstant.MODIFY_PRO_TYPE_10010301:// 校基科费-青年教师
                case SrpmsConstant.MODIFY_PRO_TYPE_10010302:// 校基科费-学生项目
                    title = "医科院校基科费验收报告_";
                    break;
                case SrpmsConstant.MODIFY_PRO_TYPE_10010401:// 改革经费
                    title = "医科院体制改革验收报告_";
                    break;
                default:
                    break;
            }
            String downName = new StringBuffer(title)
                    .append(DateFormatUtils.format(new Date(), "yyyyMMdd"))
                    .append(SrpmsConstant.EXT_PDF).toString();
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

}



