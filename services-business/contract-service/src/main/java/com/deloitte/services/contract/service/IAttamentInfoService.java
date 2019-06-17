package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.AttamentInfoQueryForm;
import com.deloitte.services.contract.entity.AttamentInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : AttamentInfo服务类接口
 * @Modified :
 */
public interface IAttamentInfoService extends IService<AttamentInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<AttamentInfo>
     */
    IPage<AttamentInfo> selectPage(AttamentInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<AttamentInfo>
     */
    List<AttamentInfo> selectList(AttamentInfoQueryForm queryForm);
}
