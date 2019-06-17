package com.deloitte.services.isump.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.isump.param.ProDeputyAccountQueryForm;
import com.deloitte.services.isump.entity.ProDeputyAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-03-22
 * @Description : ProDeputyAccount服务类接口
 * @Modified :
 */
public interface IProDeputyAccountService extends IService<ProDeputyAccount> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ProDeputyAccount>
     */
    IPage<ProDeputyAccount> selectPage(ProDeputyAccountQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ProDeputyAccount>
     */
    List<ProDeputyAccount> selectList(ProDeputyAccountQueryForm queryForm);
}
