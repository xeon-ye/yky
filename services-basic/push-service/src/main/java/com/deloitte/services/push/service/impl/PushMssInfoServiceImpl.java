package com.deloitte.services.push.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.push.entity.PushMssInfo;
import com.deloitte.services.push.mapper.PushMssInfoMapper;
import com.deloitte.services.push.service.IPushMssInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-03
 * @Description :  OaAssistantMapping服务实现类
 * @Modified :
 */
@Service
@Transactional
public class PushMssInfoServiceImpl extends ServiceImpl<PushMssInfoMapper, PushMssInfo> implements IPushMssInfoService {

}

