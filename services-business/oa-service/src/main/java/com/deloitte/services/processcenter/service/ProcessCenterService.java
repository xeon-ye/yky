package com.deloitte.services.processcenter.service;

import com.deloitte.platform.api.bpmservice.vo.BpmProcessParamVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.services.processcenter.entity.ProcessCenterDto;

/**
 * @Author : yidaojun
 * @Date : Create in 2019-03-26
 * @Description : BPM服务类接口
 * @Modified :
 */
public interface ProcessCenterService {

    /**
     * 查询待办/已办总数/办结事宜总数/抄送事宜总数/我的请求总数
     *
     * @param processCenterDto
     */
    Result<BpmProcessParamVo> search(ProcessCenterDto processCenterDto);

}
