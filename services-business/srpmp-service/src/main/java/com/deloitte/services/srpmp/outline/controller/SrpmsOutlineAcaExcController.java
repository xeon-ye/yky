package com.deloitte.services.srpmp.outline.controller;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.SrpmsOutlineAcaExcClient;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineAcaExcQueryForm;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineAcaExcFormList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineAcaExcVo;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineErrorList;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineAcaExcService;
import com.deloitte.services.srpmp.outline.util.CommonUtil;
import com.deloitte.services.srpmp.outline.util.ExcelUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
 * @Description :   SrpmsOutlineAcaExc控制器实现类
 * @Modified :
 */
@Api(value = "SrpmsOutlineAcaExc", description = "题录-学术交流操作接口")
@Slf4j
@RestController
public class SrpmsOutlineAcaExcController implements SrpmsOutlineAcaExcClient {

    @Autowired
    public ISrpmsOutlineAcaExcService  srpmsOutlineAcaExcService;

    @Autowired
    public ISysDictService sysDictService;

    /**
     * 查询学术交流控制层操作
     *
     * @param queryForm
     * @return
     */
    @Override
    @ApiOperation(value = "搜索题录-学术交流", notes = "根据查询题录-学术交流信息")
    public Result searchSrpmsOutline(HttpServletRequest request, @Valid SrpmsOutlineAcaExcQueryForm queryForm, UserVo user, DeptVo dept) {

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
        return Result.success(srpmsOutlineAcaExcService.listSrpmsOutlineQuery(request, queryForm));
    }

    /**
     *保存学术交流控制层操作
     *
     * @param data
     * @return
     */
    @Override
    @ApiOperation(value = "保存题录-学术交流记录", notes = "保存题录-学术交流记录")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result saveSrpmsOutline(@Valid @RequestBody SrpmsOutlineAcaExcFormList data, UserVo user) {

        SrpmsOutlineErrorList errorList = srpmsOutlineAcaExcService.saveSrpmsOutline(data, user);
        if(StringUtils.isNotBlank(errorList.getErrorMsg())) {
            return new Result(PlatformErrorType.ADD_NOT_VALID.getCode(), errorList.getErrorMsg(), errorList.getListError());
        }
        return Result.success();
    }

    /**
     * 读取题录-学术交流excel文件controller层接口
     *
     * @param file
     * @return
     */
    @Override
    @ApiOperation(value = "题录-学术交流读取excel", notes = "读取题录-学术交流信息")
    public Result readExeclFile(MultipartFile file) {
        if(file == null) {
            return Result.fail(PlatformErrorType.ARGUMENT_NOT_VALID);
        }

        try {
            return srpmsOutlineAcaExcService.readExeclFile(file);
        }catch (Exception e) {
            return Result.fail(e);

        }
    }

    @Override
    @ApiOperation(value = "题录-学术交流导出数据", notes = "导出题录-新获项目信息")
    public Result exportExeclFile(HttpServletRequest request, HttpServletResponse response, SrpmsOutlineAcaExcQueryForm queryForm, UserVo user, DeptVo dept) {
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
            List<SrpmsOutlineAcaExcVo> voList = srpmsOutlineAcaExcService.exportExcelQuery(queryForm);

            //excel文件名
            String fileName = SrpmsConstant.SRPMS_OUTLINE_ACA_EXC_FILE_NAME;
            //sheet名
            String sheetName = SrpmsConstant.SRPMS_OUTLINE_ACA_EXC_SHEET_NAME;
            //excel标题
            String[] title = SrpmsConstant.SRPMS_OUTLINE_FILE_TITLE_ACA_EXC;

            String[][] content = new String[voList.size()][];
            SrpmsOutlineAcaExcVo vo;
            for (int i = 0; i < voList.size(); i++) {
                content[i] = new String[title.length];
                vo = voList.get(i);
                content[i][0] = vo.getOrgName();
                content[i][1] = vo.getYear();
                content[i][2] = vo.getMonth();
                content[i][3] = vo.getRegionName();
                content[i][4] = vo.getSponsorFlagName();
                content[i][5] = vo.getJoinOrg();
                content[i][6] = vo.getJoinType();
                content[i][7] = vo.getJoinCat();
                content[i][8] = CommonUtil.objectToString(vo.getHoldingTime()).split("T")[0];
                content[i][9] = vo.getTeamName();
            }

            Map<String, List<String>> dictListMap = sysDictService.getSysDictListMap(new String[]{SrpmsConstant.PATENT_REGION, SrpmsConstant.SPONSOR_FLAG});
            Map<Integer, String[]> mapSelect = new HashMap<>();
            String[] firstArray = new String[dictListMap.get(SrpmsConstant.PATENT_REGION).size()];
            mapSelect.put(3, dictListMap.get(SrpmsConstant.PATENT_REGION).toArray(firstArray));
            String[] secoendArray = new String[dictListMap.get(SrpmsConstant.SPONSOR_FLAG).size()];
            mapSelect.put(4, dictListMap.get(SrpmsConstant.SPONSOR_FLAG).toArray(secoendArray));
            ExcelUtil.exportExcel(fileName, sheetName, title, content, request, response , mapSelect);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("导出excel数据失败");
        }
        return Result.success();
    }
}



