package com.deloitte.services.fssc.business.ito.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.ito.param.DetailsOfFundsQueryForm;
import com.deloitte.services.fssc.business.ito.entity.DetailsOfFunds;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-15
 * @Description : DetailsOfFunds服务类接口
 * @Modified :
 */
public interface IDetailsOfFundsService extends IService<DetailsOfFunds> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<DetailsOfFunds>
     */
    IPage<DetailsOfFunds> selectPage(DetailsOfFundsQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<DetailsOfFunds>
     */
    List<DetailsOfFunds> selectList(DetailsOfFundsQueryForm queryForm);

    /**
     * 是否存在被收入上缴使用的款项小类
     * @param incomeSubTypeIds
     * @return
     */
    boolean existsByFunds(List<Long> incomeSubTypeIds);

    /**
     * 改变收款单行状态
     */
    void setRecieveLineY(Long documentId,String documentType,String flag);
}
