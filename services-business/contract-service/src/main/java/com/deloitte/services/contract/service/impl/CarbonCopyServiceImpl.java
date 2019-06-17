package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.bpmservice.feign.BpmProcessTaskFeignService;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryForm;
import com.deloitte.platform.api.bpmservice.param.BpmProcessTaskQueryWrapper;
import com.deloitte.platform.api.bpmservice.vo.BpmProcessTaskVo;
import com.deloitte.platform.api.contract.param.SignInfoQueryForm;
import com.deloitte.platform.api.contract.vo.*;
import com.deloitte.platform.api.isump.vo.DeptVo;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.api.oaservice.applycenter.feign.SendProcessTaskFeignService;
import com.deloitte.platform.api.oaservice.applycenter.param.SendProcessTaskQueryForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskByBpmForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskForm;
import com.deloitte.platform.api.oaservice.applycenter.vo.SendProcessTaskVo;
import com.deloitte.platform.api.utils.UserUtil;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.platform.common.core.util.GdcPage;
import com.deloitte.services.contract.common.enums.VoucherTypeEnums;
import com.deloitte.services.contract.entity.*;
import com.deloitte.services.contract.entity.Process;
import com.deloitte.services.contract.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        sendProcessTaskForm.setSourceSystem("CONTRACT");
        return sendProcessTaskFeignService.add(sendProcessTaskForm);
    }
    @Override
    public Result addByBpm(SendProcessTaskByBpmForm sendProcessTaskByBpmForm) {
        sendProcessTaskByBpmForm.setSourceSystem("CONTRACT");
        return sendProcessTaskFeignService.addByBpm(sendProcessTaskByBpmForm);
    }
    @Override
    public Result<GdcPage<SendProcessTaskVo>> search(SendProcessTaskQueryForm sendProcessTaskQueryForm) {
        sendProcessTaskQueryForm.setSourceSystem("CONTRACT");
        return sendProcessTaskFeignService.searchByFrom(sendProcessTaskQueryForm);
    }
}

