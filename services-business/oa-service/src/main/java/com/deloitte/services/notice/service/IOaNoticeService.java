package com.deloitte.services.notice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskFormApprove;
import com.deloitte.platform.api.bpmservice.vo.NextNodeParamVo;
import com.deloitte.platform.api.oaservice.notice.param.OaNoticeQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeBpmProcessTaskForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaNoticeForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.notice.entity.OaNotice;

import java.util.List;
import java.util.Map;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-15
 * @Description : OaNotice服务类接口
 * @Modified :
 */
public interface IOaNoticeService extends IService<OaNotice> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaNotice>
     */
    IPage<OaNotice> selectPage(OaNoticeQueryForm queryForm, String token);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaNotice>
     */
    List<OaNotice> selectList(OaNoticeQueryForm queryForm);

    /**
     * 首页列表查询
     * @param num
     * @return
     */
    List<OaNotice> getHomeList(int num, String noticeTypeCode, String token);

    /**
     * 新增公告
     * @param entity
     * @return
     */
    Result save(OaNoticeForm entity);

    /**
     * 更新公告
     * @param id
     * @param entity
     * @return
     */
    Result update(long id, OaNoticeForm entity);

    /**
     * 提交审批流程
     * @param oaNoticeForm
     * @return
     */
    Result submitStart(OaNoticeForm oaNoticeForm, String token);

    /**
     * 请求审批
     * @param bpmProcessTaskFormApprove
     * @return
     */
    Result submit(OaNoticeBpmProcessTaskForm bpmProcessTaskFormApprove);

    /**
     * 出从前提交请求
     * @param bpmProcessTaskForm
     * @return
     */
    Result reSubmit(OaNoticeBpmProcessTaskForm bpmProcessTaskForm);

    /**
     * 驳回审批
     * @param bpmProcessTaskForm
     * @return
     */
    Result reply(OaNoticeBpmProcessTaskForm bpmProcessTaskForm);

    /**
     * 根据权限获取Notice数据
     */
    IPage<OaNotice> selectPageWithPermission(OaNoticeQueryForm queryForm, String token);

    /**
     * 获取Notice的下一个流程审批人节点
     * @param map
     * @return
     */
    Result<List<NextNodeParamVo>> getNextNodeParamVos(Map<String, Object> map);
}
