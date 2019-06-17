package com.deloitte.services.srpmp.outline.controller;

import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.SrpmsOutlinePaperClient;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlinePaperQueryForm;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineErrorList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlinePaperFormList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlinePaperVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.exception.PlatformErrorType;
import com.deloitte.services.srpmp.common.service.ISysDictService;
import com.deloitte.services.srpmp.common.constant.SrpmsConstant;
import com.deloitte.services.srpmp.outline.service.ISrpmsOutlinePaperService;
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
 * @Description :   SrpmsOutlinePaper控制器实现类
 * @Modified :
 */
@Api(value = "SrpmsOutlinePaper", description = "题录-论文操作接口")
@Slf4j
@RestController
public class SrpmsOutlinePaperController implements SrpmsOutlinePaperClient {

    @Autowired
    public ISrpmsOutlinePaperService  srpmsOutlinePaperService;

    @Autowired
    public ISysDictService sysDictService;

    /**
     * 查询论文控制层操作
     *
     * @param queryForm
     * @return
     */
    @Override
    @ApiOperation(value = "搜索题录-论文", notes = "根据查询题录-论文信息")
    public Result searchSrpmsOutline(HttpServletRequest request, @Valid SrpmsOutlinePaperQueryForm queryForm, UserVo user, DeptVo dept) {

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
        return Result.success(srpmsOutlinePaperService.listSrpmsOutlineQuery(request, queryForm));
    }

    /**
     *保存论文控制层操作
     *
     * @param data
     * @return
     */
    @Override
    @ApiOperation(value = "保存题录-论文记录", notes = "保存题录-论文记录")
    @ApiResponses(
            @ApiResponse(code = 200, message = "处理成功", response = Result.class)
    )
    public Result saveSrpmsOutline(@Valid @RequestBody SrpmsOutlinePaperFormList data, UserVo user) {

        SrpmsOutlineErrorList errorList = srpmsOutlinePaperService.saveSrpmsOutline(data, user);
        if(errorList.isErrorFlag()) {
            return new Result(PlatformErrorType.ADD_NOT_VALID.getCode(), errorList.getErrorMsg(), errorList.getListError());
        }
        return Result.success();
    }

    /**
     * 读取题录-论文excel文件controller层接口
     *
     * @param file
     * @return
     */
    @Override
    @ApiOperation(value = "题录-论文读取excel", notes = "读取题录-论文信息")
    public Result readExeclFile(MultipartFile file) {
        if(file == null) {
            return Result.fail(PlatformErrorType.ARGUMENT_NOT_VALID);
        }

        try {
            return srpmsOutlinePaperService.readExeclFile(file);
        }catch (Exception e) {
            return Result.fail(e);

        }
    }

    @Override
    @ApiOperation(value = "题录-论文导出数据", notes = "导出题录-新获项目信息")
    public Result exportExeclFile(HttpServletRequest request, HttpServletResponse response, SrpmsOutlinePaperQueryForm queryForm, UserVo user, DeptVo dept) {
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
            List<SrpmsOutlinePaperVo> voList = srpmsOutlinePaperService.exportExcelQuery(queryForm);

            //excel文件名
            String fileName = SrpmsConstant.SRPMS_OUTLINE_PAPER_FILE_NAME;
            //sheet名
            String sheetName = SrpmsConstant.SRPMS_OUTLINE_PAPER_SHEET_NAME;
            //excel标题
            String[] title = SrpmsConstant.SRPMS_OUTLINE_FILE_TITLE_PAPER;

            String[][] content = new String[voList.size()][];
            SrpmsOutlinePaperVo vo;
            for (int i = 0; i < voList.size(); i++) {
                content[i] = new String[title.length];
                vo = voList.get(i);
                content[i][0] = vo.getOrgName();
                content[i][1] = vo.getYear();
                content[i][2] = vo.getMonth();
                content[i][3] = vo.getCorrespondenceAuthor();
                content[i][4] = vo.getFirstAuthor();
                content[i][5] = vo.getOtherAuthor();
                content[i][6] = vo.getPaperCatName();
                content[i][7] = vo.getPaperTitle();
                content[i][8] = vo.getJournalTitle();
                content[i][9] = vo.getPublicationOrg();
                content[i][10] = vo.getPaperVolume();
                content[i][11] = vo.getStage();
                content[i][12] = vo.getPage();
                content[i][13] = vo.getInfluenceFactor().toString();
                content[i][14] = vo.getPropertyName();
                content[i][15] = vo.getRegionName();
                content[i][16] = vo.getProCode();
                content[i][17] = vo.getProName();
                content[i][18] = vo.getHproInCharge();
            }
            Map<String, List<String>> dictListMap = sysDictService.getSysDictListMap(new String[]{SrpmsConstant.JOURNAL_PROPERTY, SrpmsConstant.REGION, SrpmsConstant.PAPER_CAT});
            Map<Integer, String[]> mapSelect = new HashMap<>();
            String[] firstArray = new String[dictListMap.get(SrpmsConstant.JOURNAL_PROPERTY).size()];
            mapSelect.put(14, dictListMap.get(SrpmsConstant.JOURNAL_PROPERTY).toArray(firstArray));
            String[] secoendArray = new String[dictListMap.get(SrpmsConstant.REGION).size()];
            mapSelect.put(15, dictListMap.get(SrpmsConstant.REGION).toArray(secoendArray));
            String[] threeArray = new String[dictListMap.get(SrpmsConstant.PAPER_CAT).size()];
            mapSelect.put(6, dictListMap.get(SrpmsConstant.PAPER_CAT).toArray(threeArray));
            ExcelUtil.exportExcel(fileName, sheetName, title, content, request, response , mapSelect);

        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail("导出excel数据失败");
        }
        return Result.success();
    }
}



