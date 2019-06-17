package com.deloitte.services.notice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.oaservice.notice.param.OaCalenderQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaCalenderForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.notice.entity.OaCalender;

import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description : OaCalender服务类接口
 * @Modified :
 */
public interface IOaCalenderService extends IService<OaCalender> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaCalender>
     */
    IPage<OaCalender> selectPage(OaCalenderQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaCalender>
     */
    List<OaCalender> selectList(OaCalenderQueryForm queryForm);

    /**
     * 首页数据查询
     * @param num
     * @return
     */
    List<OaCalender> getHomeList(int num);

    /**
     * 新增校历
     * @param entity
     * @return
     */
    Result save(OaCalenderForm entity);

    /**
     * 更新校历
     * @param id
     * @param entity
     * @return
     */
    Result update(long id, OaCalenderForm entity);

}
