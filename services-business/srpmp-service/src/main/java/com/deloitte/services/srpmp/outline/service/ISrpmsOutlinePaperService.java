package com.deloitte.services.srpmp.outline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlinePaperQueryForm;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineErrorList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlinePaperFormList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlinePaperVo;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlinePaperVoList;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlinePaper;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlinePaper服务类接口
 * @Modified :
 */
public interface ISrpmsOutlinePaperService extends IService<SrpmsOutlinePaper> {

    /**
     * 分页查询论文service接口
     *
     * @param queryForm
     * @return List<SrpmsOutlinePaperVo>
     */
    SrpmsOutlinePaperVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlinePaperQueryForm queryForm);

    /**
     * 保存题录-论文service接口
     *
     * @param data
     * @return
     */
    SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlinePaperFormList data, UserVo user);

    /**
     * 读取题录-论文excel文件service接口
     *
     * @param file
     * @return
     */
    Result readExeclFile(MultipartFile file) throws Exception;

    List<SrpmsOutlinePaperVo> exportExcelQuery(SrpmsOutlinePaperQueryForm queryForm);
}
