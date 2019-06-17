package com.deloitte.services.srpmp.outline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineResultTransQueryForm;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineErrorList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineResultTransFormList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineResultTransVo;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineResultTransVoList;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineResultTrans;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlineResultTrans服务类接口
 * @Modified :
 */
public interface ISrpmsOutlineResultTransService extends IService<SrpmsOutlineResultTrans> {
    /**
     * 分页查询成果转化service接口
     *
     * @param queryForm
     * @return List<SrpmsOutlineResultTransVo>
     */
    SrpmsOutlineResultTransVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlineResultTransQueryForm queryForm);

    /**
     * 保存题录-成果转化service接口
     *
     * @param data
     * @return
     */
    SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlineResultTransFormList data, UserVo user);

    /**
     * 读取题录-成果转化excel文件service接口
     *
     * @param file
     * @return
     */
    Result readExeclFile(MultipartFile file) throws Exception;

    List<SrpmsOutlineResultTransVo> exportExcelQuery(SrpmsOutlineResultTransQueryForm queryForm);
}
