package com.deloitte.services.srpmp.outline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineMedicalQueryForm;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineErrorList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineMedicalFormList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineMedicalVo;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineMedicalVoList;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineMedical;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlineMedical服务类接口
 * @Modified :
 */
public interface ISrpmsOutlineMedicalService extends IService<SrpmsOutlineMedical> {

    /**
     * 分页查询新药器械service接口
     *
     * @param queryForm
     * @return List<SrpmsOutlineMedicalVo>
     */
    SrpmsOutlineMedicalVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlineMedicalQueryForm queryForm);

    /**
     * 保存题录-新药器械service接口
     *
     * @param data
     * @return
     */
    SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlineMedicalFormList data, UserVo user);

    /**
     * 读取题录-新药器械excel文件service接口
     *
     * @param file
     * @return
     */
    Result readExeclFile(MultipartFile file) throws Exception;

    List<SrpmsOutlineMedicalVo> exportExcelQuery(SrpmsOutlineMedicalQueryForm queryForm);
}
