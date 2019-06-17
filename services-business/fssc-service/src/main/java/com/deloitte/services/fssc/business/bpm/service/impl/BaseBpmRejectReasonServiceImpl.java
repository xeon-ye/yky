package com.deloitte.services.fssc.business.bpm.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.fssc.business.bpm.entity.BaseBpmRejectReason;
import com.deloitte.services.fssc.business.bpm.mapper.BaseBpmRejectReasonMapper;
import com.deloitte.services.fssc.business.bpm.service.IBaseBpmRejectReasonService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @Author : qiliao
 * @Date : Create in 2019-06-10
 * @Description :  BaseBpmRejectReason服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BaseBpmRejectReasonServiceImpl extends ServiceImpl<BaseBpmRejectReasonMapper, BaseBpmRejectReason> implements IBaseBpmRejectReasonService {



}

