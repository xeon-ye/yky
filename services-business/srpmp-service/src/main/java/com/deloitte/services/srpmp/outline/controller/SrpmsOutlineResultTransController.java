package com.deloitte.services.srpmp.outline.controller;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.SrpmsOutlineResultTransClient;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineResultTransQueryForm;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineErrorList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineResultTransFormList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineResultTransVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlineResultTransService;
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
 * @Description :   SrpmsOutlineResultTrans控制器实现类
 * @Modified :
 */
@Api(value = "SrpmsOutlineResultTrans", description = "题录-成果转化操作接口")
@Slf4j
@RestController
public class SrpmsOutlineResultTransController implements SrpmsOutlineResultTransClient {

    @Autowired
    public ISrpmsOutlineResultTransService  srpmsOutlineResultTransService;

    @Autowired
    public ISysDictService sysDictService;

    /**
     * 查询成果转化控制层操作
     *
     * @param queryForm
     * @return
     */
    @Override
    @ApiOperation(value = "搜索题录-成果转化", notes = "根据查询题录-成果转化信息")
    public Result searchSrpmsOutline(HttpServletRequest request, @Valid SrpmsOutlineResultTransQueryForm queryForm, UserVo user, DeptVo dept) {

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
        return Result.success(srpmsOutlineResultTransService.listSrpmsOutlineQuery(request, queryForm));
    }

    /**
     *保存成果转化控制层操作
     *
     * @param data
     * @return
     */
    @Override
    @ApiOperation(value = "保存题录-成果转化记录", notes = "保存题录-成果转化记录")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result saveSrpmsOutline(@Valid @RequestBody SrpmsOutlineResultTransFormList data, UserVo user) {

        SrpmsOutlineErrorList errorList = srpmsOutlineResultTransService.saveSrpmsOutline(data, user);
        if(errorList.isErrorFlag()) {
            return new Result(PlatformErrorType.ADD_NOT_VALID.getCode(), errorList.getErrorMsg(), errorList.getListError());
        }
        return Result.success();
    }

    /**
     * 读取题录-成果转化excel文件controller层接口
     *
     * @param file
     * @return
     */
    @Override
    @ApiOperation(value = "题录-成果转化读取excel", notes = "读取题录-成果转化信息")
    public Result readExeclFile(MultipartFile file) {
        if(file == null) {
            return Result.fail(PlatformErrorType.ARGUMENT_NOT_VALID);
        }

        try {
            return srpmsOutlineResultTransService.readExeclFile(file);
        }catch (Exception e) {
            return Result.fail(e);

        }
    }

    @Override
    @ApiOperation(value = "题录-成果转化导出数据", notes = "导出题录-新获项目信息")
    public Result exportExeclFile(HttpServletRequest request, HttpServletResponse response, SrpmsOutlineResultTransQueryForm queryForm, UserVo user, DeptVo dept) {
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
            List<SrpmsOutlineResultTransVo> voList = srpmsOutlineResultTransService.exportExcelQuery(queryForm);

            //excel文件名
            String fileName = SrpmsConstant.SRPMS_OUTLINE_TRANS_FILE_NAME;
            //sheet名
            String sheetName = SrpmsConstant.SRPMS_OUTLINE_TRANS_SHEET_NAME;
            //excel标题
            String[] title = SrpmsConstant.SRPMS_OUTLINE_FILE_TITLE_RESULT_TRANS;

            String[][] content = new String[voList.size()][];
            SrpmsOutlineResultTransVo vo;
            for (int i = 0; i < voList.size(); i++) {
                content[i] = new String[title.length];
                vo = voList.get(i);
                content[i][0] = vo.getOrgName();
                content[i][1] = vo.getYear();
                content[i][2] = vo.getMonth();
                content[i][3] = vo.getProResultName();
                content[i][4] = vo.getProInCharge();
                content[i][5] = vo.getTransWayName();
                content[i][6] = vo.getContractSigningYear();
                content[i][7] = vo.getContractAmount().toString();
                content[i][8] = vo.getYearEnsuredAmount().toString();
                content[i][9] = vo.getTransFeeSourceName();
                content[i][10] = vo.getProCode();
                content[i][11] = vo.getProName();
                content[i][12] = vo.getRemarks();
            }
            Map<String, List<String>> dictListMap = sysDictService.getSysDictListMap(new String[]{SrpmsConstant.TRANS_WAY, SrpmsConstant.TRANS_FEE_SOURCE});
            Map<Integer, String[]> mapSelect = new HashMap<>();
            String[] firstArray = new String[dictListMap.get(SrpmsConstant.TRANS_WAY).size()];
            mapSelect.put(5, dictListMap.get(SrpmsConstant.TRANS_WAY).toArray(firstArray));
            String[] secoendArray = new String[dictListMap.get(SrpmsConstant.TRANS_FEE_SOURCE).size()];
            mapSelect.put(9, dictListMap.get(SrpmsConstant.TRANS_FEE_SOURCE).toArray(secoendArray));
            ExcelUtil.exportExcel(fileName, sheetName, title, content, request, response , mapSelect);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("导出excel数据失败");
        }
        return Result.success();
    }
}



