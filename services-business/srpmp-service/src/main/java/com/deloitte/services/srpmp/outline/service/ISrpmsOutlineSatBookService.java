package com.deloitte.services.srpmp.outline.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.srpmp.outline.param.SrpmsOutlineSatBookQueryForm;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineErrorList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineSatBookFormList;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineSatBookVo;
import com.deloitte.platform.api.srpmp.outline.vo.SrpmsOutlineSatBookVoList;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.srpmp.outline.entity.SrpmsOutlineSatBook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Author : Apeng
 * @Date : Create in 2019-02-26
 * @Description : SrpmsOutlineSatBook服务类接口
 * @Modified :
 */
public interface ISrpmsOutlineSatBookService extends IService<SrpmsOutlineSatBook> {
    /**
     * 分页查询科技著作service接口
     *
     * @param queryForm
     * @return List<SrpmsOutlineSatBookVo>
     */
    SrpmsOutlineSatBookVoList listSrpmsOutlineQuery(HttpServletRequest request, SrpmsOutlineSatBookQueryForm queryForm);

    /**
     * 保存题录-科技著作service接口
     *
     * @param data
     * @return
     */
    SrpmsOutlineErrorList saveSrpmsOutline(SrpmsOutlineSatBookFormList data, UserVo user);

    /**
     * 读取题录-科技著作excel文件service接口
     *
     * @param file
     * @return
     */
    Result readExeclFile(MultipartFile file) throws Exception;

    List<SrpmsOutlineSatBookVo> exportExcelQuery(SrpmsOutlineSatBookQueryForm queryForm);
}
