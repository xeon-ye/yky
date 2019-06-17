package com.deloitte.services.srpmp.outline.controller;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.SrpmsOutlinePatentClient;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlinePatentQueryForm;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineErrorList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlinePatentFormList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlinePatentVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlinePatentService;
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
 * @Description :   SrpmsOutlinePatent控制器实现类
 * @Modified :
 */
@Api(value = "SrpmsOutlinePatent", description = "题录-专利操作接口")
@Slf4j
@RestController
public class SrpmsOutlinePatentController implements SrpmsOutlinePatentClient {

    @Autowired
    public ISrpmsOutlinePatentService  srpmsOutlinePatentService;

    @Autowired
    public ISysDictService sysDictService;

    /**
     * 查询专利控制层操作
     *
     * @param queryForm
     * @return
     */
    @Override
    @ApiOperation(value = "搜索题录-专利", notes = "根据查询题录-专利信息")
    public Result searchSrpmsOutline(HttpServletRequest request, @Valid SrpmsOutlinePatentQueryForm queryForm, UserVo user, DeptVo dept) {

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
        return Result.success(srpmsOutlinePatentService.listSrpmsOutlineQuery(request, queryForm));
    }

    /**
     *保存专利控制层操作
     *
     * @param data
     * @return
     */
    @Override
    @ApiOperation(value = "保存题录-专利记录", notes = "保存题录-专利记录")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result saveSrpmsOutline(@Valid @RequestBody SrpmsOutlinePatentFormList data, UserVo user) {

        SrpmsOutlineErrorList errorList = srpmsOutlinePatentService.saveSrpmsOutline(data, user);
        if(errorList.isErrorFlag()) {
            return new Result(PlatformErrorType.ADD_NOT_VALID.getCode(), errorList.getErrorMsg(), errorList.getListError());
        }
        return Result.success();
    }

    /**
     * 读取题录-专利excel文件controller层接口
     *
     * @param file
     * @return
     */
    @Override
    @ApiOperation(value = "题录-专利读取excel", notes = "读取题录-专利信息")
    public Result readExeclFile(MultipartFile file) {
        if(file == null) {
            return Result.fail(PlatformErrorType.ARGUMENT_NOT_VALID);
        }

        try {
            return srpmsOutlinePatentService.readExeclFile(file);
        }catch (Exception e) {
            return Result.fail(e);

        }
    }

    @Override
    @ApiOperation(value = "题录-专利导出数据", notes = "导出题录-新获项目信息")
    public Result exportExeclFile(HttpServletRequest request, HttpServletResponse response, SrpmsOutlinePatentQueryForm queryForm, UserVo user, DeptVo dept) {
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
            List<SrpmsOutlinePatentVo> voList = srpmsOutlinePatentService.exportExcelQuery(queryForm);

            //excel文件名
            String fileName = SrpmsConstant.SRPMS_OUTLINE_PATENT_FILE_NAME;
            //sheet名
            String sheetName = SrpmsConstant.SRPMS_OUTLINE_PATENT_SHEET_NAME;
            //excel标题
            String[] title = SrpmsConstant.SRPMS_OUTLINE_FILE_TITLE_PATENT;

            String[][] content = new String[voList.size()][];
            SrpmsOutlinePatentVo vo;
            for (int i = 0; i < voList.size(); i++) {
                content[i] = new String[title.length];
                vo = voList.get(i);
                content[i][0] = vo.getOrgName();
                content[i][1] = vo.getYear();
                content[i][2] = vo.getMonth();
                content[i][3] = vo.getApplicationNum();
                content[i][4] = vo.getPatentNum();
                content[i][5] = vo.getPatentName();
                content[i][6] = vo.getPatentCatName();
                content[i][7] = vo.getAuthorizedFlagName();
                content[i][8] = vo.getProposer();
                content[i][9] = CommonUtil.objectToString(vo.getApplicationTime()).split("T")[0];
                content[i][10] = CommonUtil.objectToString(vo.getAuthorizedTime()).split("T")[0];
                content[i][11] = vo.getAuthorizedRegionName();
                content[i][12] = vo.getProCode();
                content[i][13] = vo.getProName();
            }
            Map<String, List<String>> dictListMap = sysDictService.getSysDictListMap(new String[]{SrpmsConstant.PATENT_CAT, SrpmsConstant.AUTHORIZED_FLAG, SrpmsConstant.REGION});
            Map<Integer, String[]> mapSelect = new HashMap<>();
            String[] firstArray = new String[dictListMap.get(SrpmsConstant.PATENT_CAT).size()];
            mapSelect.put(5, dictListMap.get(SrpmsConstant.PATENT_CAT).toArray(firstArray));
            String[] secoendArray = new String[dictListMap.get(SrpmsConstant.AUTHORIZED_FLAG).size()];
            mapSelect.put(6, dictListMap.get(SrpmsConstant.AUTHORIZED_FLAG).toArray(secoendArray));
            String[] threeArray = new String[dictListMap.get(SrpmsConstant.REGION).size()];
            mapSelect.put(10, dictListMap.get(SrpmsConstant.REGION).toArray(threeArray));
            ExcelUtil.exportExcel(fileName, sheetName, title, content, request, response , mapSelect);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("导出excel数据失败");
        }
        return Result.success();
    }
}



