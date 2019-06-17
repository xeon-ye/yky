package com.deloitte.services.isump.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.isump.param.DeputyAccountQueryForm;
import com.deloitte.services.isump.entity.DeputyAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : DeputyAccount服务类接口
 * @Modified :
 */
public interface IDeputyAccountService extends IService<DeputyAccount> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<DeputyAccount>
     */
    IPage<DeputyAccount> selectPage(DeputyAccountQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<DeputyAccount>
     */
    List<DeputyAccount> selectList(DeputyAccountQueryForm queryForm);
}
