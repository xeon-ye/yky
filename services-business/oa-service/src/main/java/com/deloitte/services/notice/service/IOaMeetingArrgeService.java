package com.deloitte.services.notice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.oaservice.notice.param.OaMeetingArrgeQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaMeetingArrgeForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.notice.entity.OaMeetingArrge;

import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description : OaMeetingArrge服务类接口
 * @Modified :
 */
public interface IOaMeetingArrgeService extends IService<OaMeetingArrge> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaMeetingArrge>
     */
    IPage<OaMeetingArrge> selectPage(OaMeetingArrgeQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaMeetingArrge>
     */
    List<OaMeetingArrge> selectList(OaMeetingArrgeQueryForm queryForm);

    /**
     * 首页数据查询
     * @param num
     * @return
     */
    List<OaMeetingArrge> getHomeList(int num);

    /**
     * 新增校历
     * @param entity
     * @return
     */
    Result save(OaMeetingArrgeForm entity);

    /**
     * 更新校历
     * @param id
     * @param entity
     * @return
     */
    Result update(long id, OaMeetingArrgeForm entity);

}
