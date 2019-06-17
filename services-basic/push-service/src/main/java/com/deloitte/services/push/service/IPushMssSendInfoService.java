package com.deloitte.services.push.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.platform.api.push.param.PushMssSendInfoForm;
import com.deloitte.services.push.entity.PushMssSendInfo;

import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-03
 * @Description : OaAssistantMapping服务类接口
 * @Modified :
 */
public interface IPushMssSendInfoService extends IService<PushMssSendInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaAssistantMapping>
     */
    IPage<PushMssSendInfo>  selectPage(PushMssSendInfoForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaAssistantMapping>
     */
    List<PushMssSendInfo> selectList(PushMssSendInfoForm queryForm);

    Long getMssMonthTotal(String date);
}
