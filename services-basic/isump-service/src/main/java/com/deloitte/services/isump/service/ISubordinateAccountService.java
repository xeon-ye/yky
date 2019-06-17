package com.deloitte.services.isump.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.isump.param.SubordinateAccountQueryForm;
import com.deloitte.services.isump.entity.SubordinateAccount;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : SubordinateAccount服务类接口
 * @Modified :
 */
public interface ISubordinateAccountService extends IService<SubordinateAccount> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SubordinateAccount>
     */
    IPage<SubordinateAccount> selectPage(SubordinateAccountQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SubordinateAccount>
     */
    List<SubordinateAccount> selectList(SubordinateAccountQueryForm queryForm);
}
