package com.deloitte.services.fssc.business.borrow.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.borrow.param.BmBorrowBankQueryForm;
import com.deloitte.services.fssc.business.borrow.entity.BmBorrowBank;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-06
 * @Description : BmBorrowBank服务类接口
 * @Modified :
 */
public interface IBmBorrowBankService extends IService<BmBorrowBank> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<BmBorrowBank>
     */
    IPage<BmBorrowBank> selectPage(BmBorrowBankQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<BmBorrowBank>
     */
    List<BmBorrowBank> selectList(BmBorrowBankQueryForm queryForm);
}
