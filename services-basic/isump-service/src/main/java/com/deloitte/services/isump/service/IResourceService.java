package com.deloitte.services.isump.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.isump.param.ResourceQueryForm;
import com.deloitte.platform.api.isump.vo.ResourceVo;
import com.deloitte.services.isump.entity.Resource;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description : Resource服务类接口
 * @Modified :
 */
public interface IResourceService extends IService<Resource> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<Resource>
     */
    IPage<Resource> selectPage(ResourceQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<Resource>
     */
    List<Resource> selectList(ResourceQueryForm queryForm);

    /**
     * 根据副账号，获取指定系统菜单权限
     * @param deputyAccountId
     * @param sysCode
     * @return
     */
    ResourceVo findTree(long deputyAccountId,String sysCode);
}
