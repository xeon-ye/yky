package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.MonitorInfoQueryForm;
import com.deloitte.platform.api.contract.vo.MonitorInfoForm;
import com.deloitte.platform.api.contract.vo.MonitorInfoVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.services.contract.entity.MonitorInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : MonitorInfo服务类接口
 * @Modified :
 */
public interface IMonitorInfoService extends IService<MonitorInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<MonitorInfo>
     */
    IPage<MonitorInfo> selectPage(MonitorInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<MonitorInfo>
     */
    List<MonitorInfo> selectList(MonitorInfoQueryForm queryForm);
    //根据contractId查询其他监控信息
    List<MonitorInfoVo> queryMonitorInfo(String contractId);

    /**
     * 保存其他监控信息
     * @param monitorInfo
     * @return
     */
    MonitorInfo saveMonitorInfo(MonitorInfo monitorInfo, UserVo userVo);

    boolean deleteMonitorInfoById(Long id);

    MonitorInfo updateMonitorInfo(MonitorInfo monitorInfo, UserVo userVo);
}
