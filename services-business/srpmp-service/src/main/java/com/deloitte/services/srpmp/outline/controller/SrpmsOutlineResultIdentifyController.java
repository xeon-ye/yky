package com.deloitte.services.srpmp.outline.controller;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.SrpmsOutlineResultIdentifyClient;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineResultIdentifyQueryForm;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineErrorList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineResultIdentifyFormList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineResultIdentifyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineResultIdentifyService;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.outline.util.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description :   SrpmsOutlineResultIdentify控制器实现类
 * @Modified :
 */
@Api(value = "SrpmsOutlineResultIdentify", description = "题录-成果鉴定操作接口")
@Slf4j
@RestController
public class SrpmsOutlineResultIdentifyController implements SrpmsOutlineResultIdentifyClient {

    @Autowired
    public ISrpmsOutlineResultIdentifyService  srpmsOutlineResultIdentifyService;

    @Autowired
    public ISysDictService sysDictService;

    /**
     * 查询成果鉴定控制层操作
     *
     * @param queryForm
     * @return
     */
    @Override
    @ApiOperation(value = "搜索题录-成果鉴定", notes = "根据查询题录-成果鉴定信息")
    public Result searchSrpmsOutline(HttpServletRequest request, @Valid SrpmsOutlineResultIdentifyQueryForm queryForm, UserVo user, DeptVo dept) {

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
        return Result.success(srpmsOutlineResultIdentifyService.listSrpmsOutlineQuery(request, queryForm));
    }

    /**
     *保存成果鉴定控制层操作
     *
     * @param data
     * @return
     */
    @Override
    @ApiOperation(value = "保存题录-成果鉴定记录", notes = "保存题录-成果鉴定记录")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result saveSrpmsOutline(@Valid @RequestBody SrpmsOutlineResultIdentifyFormList data, UserVo user) {

        SrpmsOutlineErrorList errorList = srpmsOutlineResultIdentifyService.saveSrpmsOutline(data, user);
        if(errorList.isErrorFlag()) {
            return new Result(PlatformErrorType.ADD_NOT_VALID.getCode(), errorList.getErrorMsg(), errorList.getListError());
        }
        return Result.success();
    }

    /**
     * 读取题录-成果鉴定excel文件controller层接口
     *
     * @param file
     * @return
     */
    @Override
    @ApiOperation(value = "题录-成果鉴定读取excel", notes = "读取题录-成果鉴定信息")
    public Result readExeclFile(MultipartFile file) {
        if(file == null) {
            return Result.fail(PlatformErrorType.ARGUMENT_NOT_VALID);
        }

        try {
            return srpmsOutlineResultIdentifyService.readExeclFile(file);
        }catch (Exception e) {
            return Result.fail(e);

        }
    }

    @Override
    @ApiOperation(value = "题录-成果鉴定导出数据", notes = "导出题录-新获项目信息")
    public Result exportExeclFile(HttpServletRequest request, HttpServletResponse response, SrpmsOutlineResultIdentifyQueryForm queryForm, UserVo user, DeptVo dept) {
        try {

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
            List<SrpmsOutlineResultIdentifyVo> voList = srpmsOutlineResultIdentifyService.exportExcelQuery(queryForm);

            //excel文件名
            String fileName = SrpmsConstant.SRPMS_OUTLINE_IDENTIFY_FILE_NAME;
            //sheet名
            String sheetName = SrpmsConstant.SRPMS_OUTLINE_IDENTIFY_SHEET_NAME;
            //excel标题
            String[] title = SrpmsConstant.SRPMS_OUTLINE_FILE_TITLE_RESULT_IDENTIFY;

            String[][] content = new String[voList.size()][];
            SrpmsOutlineResultIdentifyVo vo;
            for (int i = 0; i < voList.size(); i++) {
                content[i] = new String[title.length];
                vo = voList.get(i);
                content[i][0] = vo.getOrgName();
                content[i][1] = vo.getYear();
                content[i][2] = vo.getMonth();
                content[i][3] = vo.getProResultName();
                content[i][4] = vo.getCompletionOrg();
                content[i][5] = vo.getCompletionPerson();
                content[i][6] = vo.getTaskSource();
                content[i][7] = vo.getIdentifyOrg();
                content[i][8] = vo.getIdentifyWayName();
                content[i][9] = CommonUtil.objectToString(vo.getIdentifyTime()).split("T")[0];
                content[i][10] = vo.getProCode();
                content[i][11] = vo.getProName();
            }
            Map<String, List<String>> dictListMap = sysDictService.getSysDictListMap(new String[]{SrpmsConstant.IDENTIFY_WAY});
            Map<Integer, String[]> mapSelect = new HashMap<>();
            String[] firstArray = new String[dictListMap.get(SrpmsConstant.IDENTIFY_WAY).size()];
            mapSelect.put(8, dictListMap.get(SrpmsConstant.IDENTIFY_WAY).toArray(firstArray));
            ExcelUtil.exportExcel(fileName, sheetName, title, content, request, response , mapSelect);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("导出excel数据失败");
        }
        return Result.success();
    }
}



