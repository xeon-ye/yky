package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.SetupInfoQueryForm;
import com.deloitte.services.contract.entity.SetupInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : hemingzheng
 * @Date : Create in 2019-04-13
 * @Description : SetupInfo服务类接口
 * @Modified :
 */
public interface ISetupInfoService extends IService<SetupInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SetupInfo>
     */
    IPage<SetupInfo> selectPage(SetupInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SetupInfo>
     */
    List<SetupInfo> selectList(SetupInfoQueryForm queryForm);
}
