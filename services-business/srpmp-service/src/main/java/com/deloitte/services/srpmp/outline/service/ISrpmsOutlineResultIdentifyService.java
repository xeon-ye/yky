package com.deloitte.services.srpmp.outline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineResultIdentifyQueryForm;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineErrorList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineResultIdentifyFormList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineResultIdentifyVo;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineResultIdentifyVoList;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineResultIdentify;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlineResultIdentify服务类接口
 * @Modified :
 */
public interface ISrpmsOutlineResultIdentifyService extends IService<SrpmsOutlineResultIdentify> {
    /**
     * 分页查询成果鉴定service接口
     *
     * @param queryForm
     * @return List<SrpmsOutlineResultIdentifyVo>
     */
    SrpmsOutlineResultIdentifyVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlineResultIdentifyQueryForm queryForm);

    /**
     * 保存题录-成果鉴定service接口
     *
     * @param data
     * @return
     */
    SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlineResultIdentifyFormList data, UserVo user);

    /**
     * 读取题录-成果鉴定excel文件service接口
     *
     * @param file
     * @return
     */
    Result readExeclFile(MultipartFile file) throws Exception;

    List<SrpmsOutlineResultIdentifyVo> exportExcelQuery(SrpmsOutlineResultIdentifyQueryForm queryForm);
}
