package com.deloitte.services.srpmp.outline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineAcaExcQueryForm;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineAcaExcFormList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineAcaExcVo;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineAcaExcVoList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineErrorList;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineAcaExc;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlineAcaExc服务类接口
 * @Modified :
 */
public interface ISrpmsOutlineAcaExcService extends IService<SrpmsOutlineAcaExc> {

    /**
     * 分页查询学术交流service接口
     *
     * @param queryForm
     * @return List<SrpmsOutlineAcaExcVo>
     */
    SrpmsOutlineAcaExcVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlineAcaExcQueryForm queryForm);

    /**
     * 保存题录-学术交流service接口
     *
     * @param data
     * @return
     */
    SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlineAcaExcFormList data, UserVo user);

    /**
     * 读取题录-学术交流excel文件service接口
     *
     * @param file
     * @return
     */
    Result readExeclFile(MultipartFile file) throws Exception;

    List<SrpmsOutlineAcaExcVo> exportExcelQuery(SrpmsOutlineAcaExcQueryForm queryForm);
}
