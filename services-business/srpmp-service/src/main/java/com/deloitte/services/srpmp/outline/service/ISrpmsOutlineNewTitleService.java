package com.deloitte.services.srpmp.outline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineNewTitleQueryForm;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineQueryParam;
import com.deloitte.platform.api.srpmp.outline.vo.*;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineNewTitle;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-22
 * @Description : SrpmsOutlineNewTitle服务类接口
 * @Modified :
 */
public interface ISrpmsOutlineNewTitleService extends IService<SrpmsOutlineNewTitle> {

    /**
     * 分页查询新获项目service接口
     *
     * @param queryForm
     * @return
     */
    SrpmsOutlineNewTitleVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlineNewTitleQueryForm queryForm);

    /**
     * 导出excel查询service接口
     *
     * @param queryForm
     * @return
     */
    SrpmsOutlineReportList getExportReportData(SrpmsOutlineQueryParam queryForm, int type);

    /**
     * 导出报表service接口
     *
     * @param queryForm
     * @return
     */
    String getExcelExportReport(SrpmsOutlineQueryParam queryForm, int type);

    /**
     * 保存题录-新获项目service接口
     *
     * @param data
     * @return
     */
    SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlineNewTitleFormList data, UserVo user);

    /**
     * 读取题录-新获项目excel文件service接口
     *
     * @param file
     * @return
     */
    Result readExeclFile(MultipartFile file) throws Exception;

    /**
     * 各单位经费占比service接口
     *
     * @param queryForm
     * @return
     */
    Result getReportProportionByOrg(SrpmsOutlineNewTitleQueryForm queryForm);

    /**
     * 各项目经费占比service接口
     * @param queryForm
     * @return
     */
    Result getReportProportionByProject(SrpmsOutlineNewTitleQueryForm queryForm);

    Result exportExcel(HttpServletRequest request, HttpServletResponse response, SrpmsOutlineNewTitleQueryForm queryForm);

    /**
     * 查询单位下拉
     *
     * @param user
     * @param dept
     * @return
     */
    List<SrpmsDeptVo> listSelectDept(UserVo user, DeptVo dept);
}
