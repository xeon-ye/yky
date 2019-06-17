package com.deloitte.services.fssc.business.rep.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.ppc.vo.IncomeClaimedVo;
import com.deloitte.platform.api.fssc.rep.param.RecieveLineMsgQueryForm;
import com.deloitte.services.fssc.business.rep.entity.RecieveLineMsg;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-10
 * @Description : RecieveLineMsg服务类接口
 * @Modified :
 */
public interface IRecieveLineMsgService extends IService<RecieveLineMsg> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<RecieveLineMsg>
     */
    IPage<RecieveLineMsg> selectPage(RecieveLineMsgQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<RecieveLineMsg>
     */
    List<RecieveLineMsg> selectList(RecieveLineMsgQueryForm queryForm);

    IPage<IncomeClaimedVo> conditionsRecie(RecieveLineMsgQueryForm recievePaymentQueryForm);

    /**
     * 收入小类是否被关联
     * @param incomeSubTypeIds
     * @return
     */
    boolean existsByReceivePayment(List<Long> incomeSubTypeIds);

}
