package com.deloitte.services.oaservice.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.deloitte.services.oaservice.entity.OaMssSendInfo;
import com.deloitte.services.oaservice.entity.OaMssSendInfoForm;

import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-03
 * @Description : OaAssistantMapping服务类接口
 * @Modified :
 */
public interface IOaMssSendInfoService extends IService<OaMssSendInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OaAssistantMapping>
     */
    IPage<OaMssSendInfo>  selectPage(OaMssSendInfoForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OaAssistantMapping>
     */
    List<OaMssSendInfo> selectList(OaMssSendInfoForm queryForm);

    Long getMssMonthTotal(String date);
}
