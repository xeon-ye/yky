package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.SysProjectInfoQueryForm;
import com.deloitte.platform.api.contract.vo.BasicProjectMapForm;
import com.deloitte.platform.api.contract.vo.BasicProjectMapVo;
import com.deloitte.platform.api.contract.vo.SysProjectInfoVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.services.contract.entity.BasicProjectMap;
import com.deloitte.services.contract.entity.SysProjectInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : SysProjectInfo服务类接口
 * @Modified :
 */
public interface ISysProjectInfoService extends IService<SysProjectInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<SysProjectInfo>
     */
    IPage<SysProjectInfo> selectPage(SysProjectInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<SysProjectInfo>
     */
    List<SysProjectInfo> selectList(SysProjectInfoQueryForm queryForm);
    //通过contractId查询项目信息
    List<SysProjectInfoVo> querySysProjectInfo(String contractId);

    /**
     * 新增合同项目关联信息
     * @param basicProjectMap
     * @return
     */
    BasicProjectMap saveSysProject(BasicProjectMap basicProjectMap, UserVo userVo);

    /**
     * 根据id删除合同项目关联信息
     * @param id
     * @return
     */
    int deleteBasicProjectMapById(Long id);

    /**
     * 根据合同id和项目id删除合同项目关联信息
     * @param basicProjectMap
     * @return
     */
    boolean deleteBasicProjectMap(BasicProjectMap basicProjectMap);

    /**
     * 查询项目信息（调用科研接口）
     * @param sysProjectInfoQueryForm
     * @return
     */
    IPage<SysProjectInfoVo> searchNoUser(SysProjectInfoQueryForm sysProjectInfoQueryForm);
}
