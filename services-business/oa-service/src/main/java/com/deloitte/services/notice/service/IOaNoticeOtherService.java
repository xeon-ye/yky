package com.deloitte.services.notice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.oaservice.notice.param.OaNoticeOtherQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeOtherForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.notice.entity.OaNoticeOther;

import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description : OaNoticeOther服务类接口
 * @Modified :
 */
public interface IOaNoticeOtherService extends IService<OaNoticeOther> {

    /**
     *  分页查询
     *  门户首页数据查询
     * @param queryForm
     * @return IPage<OaNoticeOther>
     */
    IPage<OaNoticeOther> selectPage(OaNoticeOtherQueryForm queryForm, String token);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaNoticeOther>
     */
    List<OaNoticeOther> selectList(OaNoticeOtherQueryForm queryForm);

    /**
     * 首页数据查询
     * @param num
     * @return
     */
    List<OaNoticeOther> getHomeList(int num, String typeCode, String token);

    /**
     * 新增校历
     * @param entity
     * @return
     */
    Result save(OaNoticeOtherForm entity);

    /**
     * 更新校历
     * @param id
     * @param entity
     * @return
     */
    Result update(long id, OaNoticeOtherForm entity);

    /**
     * 根据权限查找数据
     * @param oaNoticeOtherQueryForm
     * @param token
     * @return
     */
    IPage<OaNoticeOther> selectPageWithPermission(OaNoticeOtherQueryForm oaNoticeOtherQueryForm, String token);
}
