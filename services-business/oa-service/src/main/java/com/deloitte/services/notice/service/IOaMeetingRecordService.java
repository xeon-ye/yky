package com.deloitte.services.notice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.oaservice.notice.param.OaMeetingRecordQueryForm;
import com.deloitte.platform.api.oaservice.notice.vo.OaMeetingRecordForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.notice.entity.OaMeetingRecord;

import java.util.List;

/**
 * @Author : jianghaixun
 * @Date : Create in 2019-04-19
 * @Description : OaMeetingRecord服务类接口
 * @Modified :
 */
public interface IOaMeetingRecordService extends IService<OaMeetingRecord> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaMeetingRecord>
     */
    IPage<OaMeetingRecord> selectPage(OaMeetingRecordQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaMeetingRecord>
     */
    List<OaMeetingRecord> selectList(OaMeetingRecordQueryForm queryForm);

    /**
     * 首页数据查询
     * @param num
     * @return
     */
    List<OaMeetingRecord> getHomeList(int num);

    /**
     * 新增会议记录
     * @param entity
     * @return
     */
    Result save(OaMeetingRecordForm entity);

    /**
     * 更新会议记录
     * @param entity
     * @return
     */
    Result update(long id, OaMeetingRecordForm entity);

}
