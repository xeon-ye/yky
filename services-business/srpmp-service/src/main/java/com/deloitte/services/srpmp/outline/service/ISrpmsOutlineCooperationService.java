package com.deloitte.services.srpmp.outline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineCooperationQueryForm;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineCooperationFormList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineCooperationVo;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineCooperationVoList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineErrorList;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineCooperation;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlineCooperation服务类接口
 * @Modified :
 */
public interface ISrpmsOutlineCooperationService extends IService<SrpmsOutlineCooperation> {

    /**
     * 分页查询国合项目service接口
     *
     * @param queryForm
     * @return List<SrpmsOutlineCooperationVo>
     */
    SrpmsOutlineCooperationVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlineCooperationQueryForm queryForm);

    /**
     * 保存题录-国合项目service接口
     *
     * @param data
     * @return
     */
    SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlineCooperationFormList data, UserVo user);

    /**
     * 读取题录-国合项目excel文件service接口
     *
     * @param file
     * @return
     */
    Result readExeclFile(MultipartFile file) throws Exception;

    List<SrpmsOutlineCooperationVo> exportExcelQuery(SrpmsOutlineCooperationQueryForm queryForm);
}
