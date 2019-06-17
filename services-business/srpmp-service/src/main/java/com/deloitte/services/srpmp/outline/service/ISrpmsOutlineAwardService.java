package com.deloitte.services.srpmp.outline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineAwardQueryForm;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineAwardFormList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineAwardVo;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineAwardVoList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineErrorList;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineAward;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlineAward服务类接口
 * @Modified :
 */
public interface ISrpmsOutlineAwardService extends IService<SrpmsOutlineAward> {

    /**
     * 分页查询奖励service接口
     *
     * @param queryForm
     * @return List<SrpmsOutlineAwardVo>
     */
    SrpmsOutlineAwardVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlineAwardQueryForm queryForm);

    /**
     * 保存题录-奖励service接口
     *
     * @param data
     * @return
     */
    SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlineAwardFormList data, UserVo user);

    /**
     * 读取题录-奖励excel文件service接口
     *
     * @param file
     * @return
     */
    Result readExeclFile(MultipartFile file) throws Exception;

    List<SrpmsOutlineAwardVo> exportExcelQuery(SrpmsOutlineAwardQueryForm queryForm);
}
