package com.deloitte.services.srpmp.outline.controller;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.SrpmsOutlineNewTitleClient;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineNewTitleQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineErrorList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineNewTitleFormList;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.BaseException;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineNewTitleService;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.*;
import java.util.Date;


/**
 * @Author : Apeng
 * @Date : Create in 2019-02-15
 * @Description :   SrpmsOutlineAcaExc控制器实现类
 * @Modified :
 */
@Api(value = "SrpmsOutlineNewTitle", description = "题录-新获项目操作接口")
@Slf4j
@RestController
public class SrpmsOutlineNewTitleController implements SrpmsOutlineNewTitleClient {

    @Autowired
    public ISrpmsOutlineNewTitleService srpmsOutlineNewTitleService;

    @Autowired
    public ISysDictService sysDictService;

    /**
     * 查询新获项目控制层操作
     *
     * @param queryForm
     * @return
     */
    @Override
    @ApiOperation(value = "搜索题录-新获项目", notes = "根据查询题录-新获项目信息")
    public Result searchSrpmsOutline(HttpServletRequest request, @Valid SrpmsOutlineNewTitleQueryForm queryForm, UserVo user, DeptVo dept) {

        String roleCode = user.getHonor();
        String deptCode = dept.getDeptCode();
        if (SrpmsConstant.SRPMS_ADMIN.equals(roleCode) || SrpmsConstant.SRPMS_LEADER.equals(roleCode)) {
            if (!SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode)) {
                queryForm.setOrgId(CommonUtil.getLongValue(deptCode));
            }
            queryForm.setCreateBy(null);
        } else {
            queryForm.setOrgId(CommonUtil.getLongValue(deptCode));
            queryForm.setCreateBy(user.getId());
        }
        return Result.success(srpmsOutlineNewTitleService.listSrpmsOutlineQuery(request, queryForm));
    }

    /**
     * 保存新获项目控制层操作
     *
     * @param data
     * @return
     */
    @Override
    @ApiOperation(value = "保存题录-新获项目记录", notes = "保存题录-新获项目记录")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result saveSrpmsOutline(@Valid @RequestBody SrpmsOutlineNewTitleFormList data, UserVo user) {

        SrpmsOutlineErrorList errorList = srpmsOutlineNewTitleService.saveSrpmsOutline(data, user);
        if (errorList.isErrorFlag()) {
            return new Result(PlatformErrorType.ADD_NOT_VALID.getCode(), errorList.getErrorMsg(), errorList.getListError());
        }
        return Result.success();
    }

    /**
     * 读取题录-新获项目excel文件controller层接口
     *
     * @param file
     * @return
     */
    @Override
    @ApiOperation(value = "导入题录-新获项目excel", notes = "读取题录-新获项目信息")
    public Result importExeclFile(MultipartFile file) {
        if (file == null) {
            return Result.fail(PlatformErrorType.ARGUMENT_NOT_VALID);
        }

        try {
            return srpmsOutlineNewTitleService.readExeclFile(file);
        } catch (Exception e) {
            return Result.fail(e);

        }
    }

    /**
     * 各单位经费占比
     *
     * @param queryForm
     * @return
     */
    @Override
    @ApiOperation(value = "导出题录-新获项目各单位经费占比)", notes = "导出题录-新获项目信息")
    public Result getReportProportionByOrg(@Valid SrpmsOutlineNewTitleQueryForm queryForm) {
        return Result.success(srpmsOutlineNewTitleService.getReportProportionByOrg(queryForm));
    }

    /**
     * 各项目经费占比
     *
     * @param queryForm
     * @return
     */
    @Override
    @ApiOperation(value = "导出题录-新获项目各项目经费占比)", notes = "导出题录-新获项目信息")
    public Result getReportProportionByProject(@Valid SrpmsOutlineNewTitleQueryForm queryForm) {
        return Result.success(srpmsOutlineNewTitleService.getReportProportionByProject(queryForm));
    }

    /**
     * 导出excel报表接口
     *
     * @param response
     * @param queryParam
     * @return
     */
    @Override
    @ApiOperation(value = "导出题录-excel报表", notes = "导出题录-新获项目信息")
    public Result getExcelExportReport(HttpServletRequest request, HttpServletResponse response, @Valid SrpmsOutlineQueryParam queryParam, @PathVariable int type) {

        String filePath = srpmsOutlineNewTitleService.getExcelExportReport(queryParam, type);

        StringBuilder newFileName = new StringBuilder();

        newFileName.append(DateFormatUtils.format(new Date(), "yyyy"));
        newFileName.append("年");

        switch (type) {
            case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_FIRST:// 新获科技部项目情况统计表
                newFileName.append("新获科技部项目情况统计表");
                break;
            case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_SECOND:// 新获科研项目经费情况统计表
                newFileName.append("新获科研项目总经费情况统计表");
                break;
            case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_THREE:// 到位科研项目到位经费情况统计表
                newFileName.append("新获科研项目到位经费情况统计表");
                break;
            case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_FOUR:// 科研成果及获奖情况统计表
                newFileName.append("科研成果及获奖情况统计表");
                break;
            case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_FIVE:// 专利、新药证书、医药器械证书情况统计表
                newFileName.append("专利、新药证书、医药器械证书情况统计表");
                break;
            case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_SIX:// 发表学术论文、科技著作情况统计表
                newFileName.append("发表学术论文、科技著作情况统计表");
                break;
            case SrpmsConstant.SRPMS_OUTLINE_CONTROLLER_TYPE_SEVEN:// 科技项目执行情况统计表
                newFileName.append("科技项目执行情况统计表");
                break;
            default:
                break;
        }
        newFileName.append(SrpmsConstant.EXT_EXCEL_XLSX);

        response.setCharacterEncoding("utf-8");
        response.setContentType("application/octet-stream");
        byte[] buffer = new byte[1024];
        InputStream is = null;
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            response.setHeader("Content-Disposition", "attachment;fileName=" + java.net.URLEncoder.encode(newFileName.toString(), "UTF-8"));
            is = new FileInputStream(filePath);
            bis = new BufferedInputStream(is);
            os = response.getOutputStream();
            int i = bis.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = bis.read(buffer);
            }
        } catch (Exception e) {
            log.error("文件读取异常！", e);
            CommonUtil.deleteFile(filePath);
            throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage(), e.getCause());
        } finally {
            if (os != null) {
                try {
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    log.error("文件流关闭异常:", e);
                    CommonUtil.deleteFile(filePath);
                    throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage());
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    log.error("文件流关闭异常:", e);
                    CommonUtil.deleteFile(filePath);
                    throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage());
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    log.error("文件流关闭异常:", e);
                    CommonUtil.deleteFile(filePath);
                    throw new BaseException(PlatformErrorType.SYSTEM_ERROR, e.getMessage());
                }
            }
        }

        CommonUtil.deleteFile(filePath);
        return Result.success();
    }

    /**
     * @param response
     * @param queryParam
     * @param type
     * @return
     */
    @Override
    @ApiOperation(value = "前端展示题录-报表", notes = "前端展示题录-新获项目信息")
    public Result getExportReportData(HttpServletResponse response, @Valid SrpmsOutlineQueryParam queryParam, @PathVariable int type) {

        return Result.success(srpmsOutlineNewTitleService.getExportReportData(queryParam, type));
    }

    /**
     * 导出数据
     *
     * @param response
     * @param queryForm
     * @return
     */
    @Override
    @ApiOperation(value = "题录-新获项目导出数据", notes = "导出题录-新获项目信息")
    public Result exportExeclFile(HttpServletRequest request, HttpServletResponse response, @Valid SrpmsOutlineNewTitleQueryForm queryForm, UserVo user, DeptVo dept) {

        String roleCode = user.getHonor();
        String deptCode = dept.getDeptCode();
        if (SrpmsConstant.SRPMS_ADMIN.equals(roleCode) || SrpmsConstant.SRPMS_LEADER.equals(roleCode)) {
            if (!SrpmsConstant.SRPMS_FIRST_LEVEL_DEPT_CODE.equals(deptCode)) {
                queryForm.setOrgId(CommonUtil.getLongValue(deptCode));
            }
            queryForm.setCreateBy(null);
        } else {
            queryForm.setOrgId(CommonUtil.getLongValue(deptCode));
            queryForm.setCreateBy(user.getId());
        }
        return Result.success(srpmsOutlineNewTitleService.exportExcel(request, response, queryForm));
    }

    /**
     * 查询单位下拉
     *
     * @param user
     * @param dept
     * @return
     */
    @Override
    public Result searchSelectDept(UserVo user, DeptVo dept) {
        return Result.success(srpmsOutlineNewTitleService.listSelectDept(user, dept));
    }

}
