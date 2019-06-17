package com.deloitte.services.project.service.impl;

import com.deloitte.platform.api.oaservice.applycenter.feign.SendProcessTaskFeignService;
import com.deloitte.platform.api.oaservice.applycenter.param.SendProcessTaskQueryForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskByBpmForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.project.service.ICarbonCopyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-30
 * @Description :  抄送待阅已阅
 * @Modified :
 */
@Service
@Transactional
@Slf4j
public class CarbonCopyServiceImpl implements ICarbonCopyService {
    @Autowired
    private SendProcessTaskFeignService sendProcessTaskFeignService;
    @Override
    public Result add(SendProcessTaskForm sendProcessTaskForm) {
        sendProcessTaskForm.setSourceSystem("PROJECT");
        return sendProcessTaskFeignService.add(sendProcessTaskForm);
    }
    @Override
    public Result addByBpm(SendProcessTaskByBpmForm sendProcessTaskByBpmForm) {
        sendProcessTaskByBpmForm.setSourceSystem("PROJECT");
        return sendProcessTaskFeignService.addByBpm(sendProcessTaskByBpmForm);
    }
    @Override
    public Result<GdcPage<SendProcessTaskVo>> search(SendProcessTaskQueryForm sendProcessTaskQueryForm) {
        sendProcessTaskQueryForm.setSourceSystem("PROJECT");
        return sendProcessTaskFeignService.search(sendProcessTaskQueryForm);
    }

}

