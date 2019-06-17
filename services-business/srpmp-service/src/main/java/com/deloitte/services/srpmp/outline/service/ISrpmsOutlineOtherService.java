package com.deloitte.services.srpmp.outline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineOtherQueryForm;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineErrorList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineOtherFormList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineOtherVo;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineOtherVoList;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineOther;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlineOther服务类接口
 * @Modified :
 */
public interface ISrpmsOutlineOtherService extends IService<SrpmsOutlineOther> {

    /**
     * 分页查询其他service接口
     *
     * @param queryForm
     * @return List<SrpmsOutlineOtherVo>
     */
    SrpmsOutlineOtherVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlineOtherQueryForm queryForm);

    /**
     * 保存题录-其他service接口
     *
     * @param data
     * @return
     */
    SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlineOtherFormList data, UserVo user);

    /**
     * 读取题录-其他excel文件service接口
     *
     * @param file
     * @return
     */
    Result readExeclFile(MultipartFile file) throws Exception;

    List<SrpmsOutlineOtherVo> exportExcelQuery(SrpmsOutlineOtherQueryForm queryForm);
}
