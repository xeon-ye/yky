package com.deloitte.services.fssc.system.unit.service.impl;

import com.deloitte.services.fssc.system.unit.entity.UnitAuditHis;
import com.deloitte.services.fssc.system.unit.mapper.UnitAuditHisMapper;
import com.deloitte.services.fssc.system.unit.service.IUnitAuditHisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description :  UnitAuditHis服务实现类
 * @Modified :
 */
@Service
@Transactional
public class UnitAuditHisServiceImpl extends ServiceImpl<UnitAuditHisMapper, UnitAuditHis> implements IUnitAuditHisService {


    @Autowired
    private UnitAuditHisMapper unitAuditHisMapper;


}

