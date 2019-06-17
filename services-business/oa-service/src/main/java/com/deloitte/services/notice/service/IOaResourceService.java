package com.deloitte.services.notice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.oaservice.notice.param.OaResourceQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaResourceForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.notice.entity.OaResource;

import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description : OaResource服务类接口
 * @Modified :
 */
public interface IOaResourceService extends IService<OaResource> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaResource>
     */
    IPage<OaResource> selectPage(OaResourceQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaResource>
     */
    List<OaResource> selectList(OaResourceQueryForm queryForm);

    /**
     * 首页数据查询
     * @param num
     * @return
     */
    List<OaResource> getHomeList(int num);

    /**
     * 新增校历
     * @param entity
     * @return
     */
    Result save(OaResourceForm entity);

    /**
     * 更新校历
     * @param id
     * @param entity
     * @return
     */
    Result update(long id, OaResourceForm entity);

}
