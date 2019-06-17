package com.deloitte.services.fssc.business.basecontract.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.fssc.business.basecontract.entity.BaseContractPlanMap;
import com.deloitte.services.fssc.business.basecontract.mapper.BaseContractPlanMapMapper;
import com.deloitte.services.fssc.business.basecontract.service.IBaseContractPlanMapService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @Author : qiliao
 * @Date : Create in 2019-04-18
 * @Description :  BaseContractPlanMap服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BaseContractPlanMapServiceImpl extends ServiceImpl<BaseContractPlanMapMapper, BaseContractPlanMap> implements IBaseContractPlanMapService {



}

