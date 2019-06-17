package com.deloitte.services.fssc.business.rep.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.rep.param.RecieveIncomeMsgQueryForm;
import com.deloitte.services.fssc.business.rep.entity.RecieveIncomeMsg;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-10
 * @Description : RecieveIncomeMsg服务类接口
 * @Modified :
 */
public interface IRecieveIncomeMsgService extends IService<RecieveIncomeMsg> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<RecieveIncomeMsg>
     */
    IPage<RecieveIncomeMsg> selectPage(RecieveIncomeMsgQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<RecieveIncomeMsg>
     */
    List<RecieveIncomeMsg> selectList(RecieveIncomeMsgQueryForm queryForm);
}
