package com.deloitte.services.fssc.business.basecontract.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.fssc.business.basecontract.entity.BaseContractProject;
import com.deloitte.services.fssc.business.basecontract.mapper.BaseContractProjectMapper;
import com.deloitte.services.fssc.business.basecontract.service.IBaseContractProjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @Author : qiliao
 * @Date : Create in 2019-05-29
 * @Description :  BaseContractProject服务实现类
 * @Modified :
 */
@Service
@Transactional
public class BaseContractProjectServiceImpl extends ServiceImpl<BaseContractProjectMapper, BaseContractProject> implements IBaseContractProjectService {



}

