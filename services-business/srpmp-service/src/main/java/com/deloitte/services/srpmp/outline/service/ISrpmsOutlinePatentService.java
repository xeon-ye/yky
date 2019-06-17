package com.deloitte.services.srpmp.outline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlinePatentQueryForm;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineErrorList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlinePatentFormList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlinePatentVo;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlinePatentVoList;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlinePatent;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlinePatent服务类接口
 * @Modified :
 */
public interface ISrpmsOutlinePatentService extends IService<SrpmsOutlinePatent> {

    /**
     * 分页查询专利service接口
     *
     * @param queryForm
     * @return List<SrpmsOutlinePatentVo>
     */
    SrpmsOutlinePatentVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlinePatentQueryForm queryForm);

    /**
     * 保存题录-专利service接口
     *
     * @param data
     * @return
     */
    SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlinePatentFormList data, UserVo user);

    /**
     * 读取题录-专利excel文件service接口
     *
     * @param file
     * @return
     */
    Result readExeclFile(MultipartFile file) throws Exception;

    List<SrpmsOutlinePatentVo> exportExcelQuery(SrpmsOutlinePatentQueryForm queryForm);
}
