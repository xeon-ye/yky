package com.deloitte.services.fssc.system.bank.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.bank.param.DataAuditHisQueryForm;
import com.deloitte.services.fssc.system.bank.entity.DataAuditHis;
import com.deloitte.services.fssc.system.bank.mapper.DataAuditHisMapper;
import com.deloitte.services.fssc.system.bank.service.IDataAuditHisService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : qiliao
 * @Date : Create in 2019-02-25
 * @Description :  DataAuditHis服务实现类
 * @Modified :
 */
@Service
@Transactional
public class DataAuditHisServiceImpl extends ServiceImpl<DataAuditHisMapper, DataAuditHis> implements IDataAuditHisService {


    @Autowired
    private DataAuditHisMapper dataAuditHisMapper;

    @Override
    public IPage<DataAuditHis> selectPage(DataAuditHisQueryForm queryForm ) {
        QueryWrapper<DataAuditHis> queryWrapper = new QueryWrapper <DataAuditHis>();
        //getQueryWrapper(queryWrapper,queryForm);
        return dataAuditHisMapper.selectPage(new Page<DataAuditHis>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<DataAuditHis> selectList(DataAuditHisQueryForm queryForm) {
        QueryWrapper<DataAuditHis> queryWrapper = new QueryWrapper <DataAuditHis>();
        //getQueryWrapper(queryWrapper,queryForm);
        return dataAuditHisMapper.selectList(queryWrapper);
    }


}

