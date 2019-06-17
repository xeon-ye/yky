package com.deloitte.services.project.service;

import com.deloitte.platform.api.oaservice.applycenter.param.SendProcessTaskQueryForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskByBpmForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-30
 * @Description :  抄送待阅已阅
 * @Modified :
 */
public interface ICarbonCopyService {
    /**
     * POST---新增
     *
     * @param sendProcessTaskForm
     * @return
     */
    Result add(SendProcessTaskForm sendProcessTaskForm);
    /**
     * 内部系统通过BPM生成待阅信息
     *
     * @param sendProcessTaskByBpmForm
     * @return
     */
    Result addByBpm(SendProcessTaskByBpmForm sendProcessTaskByBpmForm);
    /**
     * POST----复杂查询
     * 查询待阅和已阅
     *
     * @param sendProcessTaskQueryForm
     * @return
     */
    Result<GdcPage<SendProcessTaskVo>> search(SendProcessTaskQueryForm sendProcessTaskQueryForm);

}
