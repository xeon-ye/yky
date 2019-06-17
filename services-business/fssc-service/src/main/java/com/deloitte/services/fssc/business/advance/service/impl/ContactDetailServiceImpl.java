package com.deloitte.services.fssc.business.advance.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.advance.param.ContactDetailQueryForm;
import com.deloitte.services.fssc.business.advance.entity.ContactDetail;
import com.deloitte.services.fssc.business.advance.mapper.ContactDetailMapper;
import com.deloitte.services.fssc.business.advance.service.IContactDetailService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : hjy
 * @Date : Create in 2019-03-12
 * @Description :  BmContactDetail服务实现类
 * @Modified :
 */
@Service
@Transactional
public class ContactDetailServiceImpl extends ServiceImpl<ContactDetailMapper, ContactDetail> implements IContactDetailService {


    @Autowired
    private ContactDetailMapper contactDetailMapper;

    @Override
    public IPage<ContactDetail> selectPage(ContactDetailQueryForm queryForm ) {
        QueryWrapper<ContactDetail> queryWrapper = new QueryWrapper <ContactDetail>();
        //getQueryWrapper(queryWrapper,queryForm);
        return contactDetailMapper.selectPage(new Page<ContactDetail>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<ContactDetail> selectList(ContactDetailQueryForm queryForm) {
        QueryWrapper<ContactDetail> queryWrapper = new QueryWrapper <ContactDetail>();
        //getQueryWrapper(queryWrapper,queryForm);
        return contactDetailMapper.selectList(queryWrapper);
    }

    @Override
    public List<ContactDetail> selectList(Long documentId) {
        QueryWrapper<ContactDetail> queryWrapper = new QueryWrapper <>();
        queryWrapper.eq(ContactDetail.DOCUMENT_ID,documentId);
        return this.list(queryWrapper);
    }

    public boolean existsByExpenseSubCdTypeIds(List<Long> ExpenseSubTypeList) {
        return contactDetailMapper.existsByExpenseSubTypeIds(ExpenseSubTypeList) > 0 ? true : false;
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<ContactDetail> getQueryWrapper(QueryWrapper<ContactDetail> queryWrapper,BaseQueryForm<ContactDetailQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(bmContactDetail.getCreateBy())){
            queryWrapper.like(ContactDetail.CREATE_BY,bmContactDetail.getCreateBy());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getCreateUserName())){
            queryWrapper.like(ContactDetail.CREATE_USER_NAME,bmContactDetail.getCreateUserName());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getUpdateBy())){
            queryWrapper.like(ContactDetail.UPDATE_BY,bmContactDetail.getUpdateBy());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getUpdateTime())){
            queryWrapper.like(ContactDetail.UPDATE_TIME,bmContactDetail.getUpdateTime());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getCreateTime())){
            queryWrapper.like(ContactDetail.CREATE_TIME,bmContactDetail.getCreateTime());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getEx1())){
            queryWrapper.like(ContactDetail.EX1,bmContactDetail.getEx1());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getEx2())){
            queryWrapper.like(ContactDetail.EX2,bmContactDetail.getEx2());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getEx3())){
            queryWrapper.like(ContactDetail.EX3,bmContactDetail.getEx3());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getEx4())){
            queryWrapper.like(ContactDetail.EX4,bmContactDetail.getEx4());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getEx5())){
            queryWrapper.like(ContactDetail.EX5,bmContactDetail.getEx5());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getVersion())){
            queryWrapper.like(ContactDetail.VERSION,bmContactDetail.getVersion());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getMainTypeId())){
            queryWrapper.like(ContactDetail.MAIN_TYPE_ID,bmContactDetail.getMainTypeId());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getSubTypeId())){
            queryWrapper.like(ContactDetail.SUB_TYPE_ID,bmContactDetail.getSubTypeId());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getRemark())){
            queryWrapper.like(ContactDetail.REMARK,bmContactDetail.getRemark());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getAdvancePaymentId())){
            queryWrapper.like(ContactDetail.DOCUMENT_ID,bmContactDetail.getAdvancePaymentId());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getMainTypeName())){
            queryWrapper.like(ContactDetail.MAIN_TYPE_NAME,bmContactDetail.getMainTypeName());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getSubTypeName())){
            queryWrapper.like(ContactDetail.SUB_TYPE_NAME,bmContactDetail.getSubTypeName());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getContactName())){
            queryWrapper.like(ContactDetail.CONTACT_NAME,bmContactDetail.getContactName());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getContactNumberId())){
            queryWrapper.like(ContactDetail.CONTACT_NUMBER_ID,bmContactDetail.getContactNumberId());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getContactNumber())){
            queryWrapper.like(ContactDetail.CONTACT_NUMBER,bmContactDetail.getContactNumber());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getUnitId())){
            queryWrapper.like(ContactDetail.UNIT_ID,bmContactDetail.getUnitId());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getDeptId())){
            queryWrapper.like(ContactDetail.DEPT_ID,bmContactDetail.getDeptId());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getUnitName())){
            queryWrapper.like(ContactDetail.UNIT_NAME,bmContactDetail.getUnitName());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getDeptName())){
            queryWrapper.like(ContactDetail.DEPT_NAME,bmContactDetail.getDeptName());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getSubjectSuperficil())){
            queryWrapper.like(ContactDetail.SUBJECT_SUPERFICIL,bmContactDetail.getSubjectSuperficil());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getAgreedPaymentLot())){
            queryWrapper.like(ContactDetail.AGREED_PAYMENT_LOT,bmContactDetail.getAgreedPaymentLot());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getAgreedPaymentAmount())){
            queryWrapper.like(ContactDetail.AGREED_PAYMENT_AMOUNT,bmContactDetail.getAgreedPaymentAmount());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getAgreedPropertions())){
            queryWrapper.like(ContactDetail.AGREED_PROPERTIONS,bmContactDetail.getAgreedPropertions());
        }
        if(StringUtils.isNotBlank(bmContactDetail.getPlanPaymentTime())){
            queryWrapper.like(ContactDetail.PLAN_PAYMENT_TIME,bmContactDetail.getPlanPaymentTime());
        }
        return queryWrapper;
    }
     */
}

