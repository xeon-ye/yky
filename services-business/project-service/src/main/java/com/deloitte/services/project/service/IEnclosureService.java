package com.deloitte.services.project.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.project.param.EnclosureQueryForm;
import com.deloitte.platform.api.project.vo.CancelProjectFrom;
import com.deloitte.services.project.entity.Enclosure;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description : Enclosure服务类接口
 * @Modified :
 */
public interface IEnclosureService extends IService<Enclosure> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Enclosure>
     */
    IPage<Enclosure> selectPage(EnclosureQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Enclosure>
     */
    List<Enclosure> selectList(EnclosureQueryForm queryForm);

    /**
     * 保存附件
     * @param enclosureForm
     */
    void saveEnclosureFile(CancelProjectFrom cancelProjectFrom);

    List<Enclosure> selectListByAppId(String applicationId);
    List<Enclosure> selectListByRevId(String reviewId);
    void delListByRev(String reviewId);
}
