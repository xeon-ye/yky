package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.SysWatermarkQueryForm;
import com.deloitte.services.contract.entity.SysWatermark;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : SysWatermark服务类接口
 * @Modified :
 */
public interface ISysWatermarkService extends IService<SysWatermark> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SysWatermark>
     */
    IPage<SysWatermark> selectPage(SysWatermarkQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SysWatermark>
     */
    List<SysWatermark> selectList(SysWatermarkQueryForm queryForm);

    /**
     * 获取水印信息
     * @param departmentCode
     * @return
     */
    String getWatermark(String departmentCode);
}
