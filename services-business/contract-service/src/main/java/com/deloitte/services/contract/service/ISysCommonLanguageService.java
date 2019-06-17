package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.SysCommonLanguageQueryForm;
import com.deloitte.services.contract.entity.SysCommonLanguage;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : SysCommonLanguage服务类接口
 * @Modified :
 */
public interface ISysCommonLanguageService extends IService<SysCommonLanguage> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SysCommonLanguage>
     */
    IPage<SysCommonLanguage> selectPage(SysCommonLanguageQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SysCommonLanguage>
     */
    List<SysCommonLanguage> selectList(SysCommonLanguageQueryForm queryForm);
}
