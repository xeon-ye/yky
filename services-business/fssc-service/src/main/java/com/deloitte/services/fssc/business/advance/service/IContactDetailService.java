package com.deloitte.services.fssc.business.advance.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.fssc.advance.param.ContactDetailQueryForm;
import com.deloitte.services.fssc.business.advance.entity.ContactDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-03-12
 * @Description : BmContactDetail服务类接口
 * @Modified :
 */
public interface IContactDetailService extends IService<ContactDetail> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<ContactDetail>
     */
    IPage<ContactDetail> selectPage(ContactDetailQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<ContactDetail>
     */
    List<ContactDetail> selectList(ContactDetailQueryForm queryForm);

    /**
     * 根据单据id查询
     * @param documentId
     * @return
     */
    List<ContactDetail> selectList(Long documentId);

    /**
     * 根据支出小类id查询是关联的对公预付款单
     * @param expenseMainTypeIdList
     * @return
     */
    boolean existsByExpenseSubCdTypeIds(List<Long> expenseMainTypeIdList);

}
