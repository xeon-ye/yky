package com.deloitte.services.fssc.system.bank.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.bank.param.DataAuditHisQueryForm;
import com.deloitte.services.fssc.system.bank.entity.DataAuditHis;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-25
 * @Description : DataAuditHis服务类接口
 * @Modified :
 */
public interface IDataAuditHisService extends IService<DataAuditHis> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<DataAuditHis>
     */
    IPage<DataAuditHis> selectPage(DataAuditHisQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<DataAuditHis>
     */
    List<DataAuditHis> selectList(DataAuditHisQueryForm queryForm);
}
