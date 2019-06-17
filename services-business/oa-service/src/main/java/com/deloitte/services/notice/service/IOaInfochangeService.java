package com.deloitte.services.notice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.oaservice.notice.param.OaInfochangeQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaInfochangeForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.notice.entity.OaInfochange;

import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description : OaInfochange服务类接口
 * @Modified :
 */
public interface IOaInfochangeService extends IService<OaInfochange> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaInfochange>
     */
    IPage<OaInfochange> selectPage(OaInfochangeQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaInfochange>
     */
    List<OaInfochange> selectList(OaInfochangeQueryForm queryForm);

    /**
     * 首页数据查询
     * @param num
     * @return
     */
    List<OaInfochange> getHomeList(int num);

    /**
     * 新增校历
     * @param entity
     * @return
     */
    Result save(OaInfochangeForm entity);

    /**
     * 更新校历
     * @param id
     * @param entity
     * @return
     */
    Result update(long id, OaInfochangeForm entity);

}
