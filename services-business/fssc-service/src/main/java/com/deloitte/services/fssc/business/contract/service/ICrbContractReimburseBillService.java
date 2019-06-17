package com.deloitte.services.fssc.business.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.contract.param.CrbContractReimburseBillQueryForm;
import com.deloitte.platform.api.fssc.contract.vo.CrbContractReimburseBillVo;
import com.deloitte.platform.api.fssc.general.param.GeExpenseBorrowPrepayQueryForm;
import com.deloitte.platform.api.fssc.general.vo.BorrowPrepayListVo;
import com.deloitte.services.fssc.business.contract.entity.CrbContractReimburseBill;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-26
 * @Description : CrbContractReimburseBill服务类接口
 * @Modified :
 */
public interface ICrbContractReimburseBillService extends IService<CrbContractReimburseBill> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<CrbContractReimburseBill>
     */
    IPage<CrbContractReimburseBill> selectPage(CrbContractReimburseBillQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<CrbContractReimburseBill>
     */
    List<CrbContractReimburseBill> selectList(CrbContractReimburseBillQueryForm queryForm);

    /**
     * *  条件查询
     *      * @param contractId
     *      *
     */

     CrbContractReimburseBillVo findByAllData(Long contractId);

    /**
     * 根据支出大类id查询是关联的合同报账单
     * @param idList
     * @return
     */
    boolean existsByExpenseMainTypeAdvIds(List<Long> idList);

    List<BorrowPrepayListVo> findAdpPrepayList(GeExpenseBorrowPrepayQueryForm form);
}
