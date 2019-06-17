package com.deloitte.services.fssc.business.general.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.general.param.GeExpensePaymentListQueryForm;
import com.deloitte.services.fssc.business.general.entity.GeExpensePaymentList;
import com.deloitte.services.fssc.business.general.mapper.GeExpensePaymentListMapper;
import com.deloitte.services.fssc.business.general.service.IGeExpensePaymentListService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description :  GeExpensePaymentList服务实现类
 * @Modified :
 */
@Service
@Transactional
public class GeExpensePaymentListServiceImpl extends ServiceImpl<GeExpensePaymentListMapper, GeExpensePaymentList> implements IGeExpensePaymentListService {


    @Autowired
    private GeExpensePaymentListMapper geExpensePaymentListMapper;

    @Override
    public IPage<GeExpensePaymentList> selectPage(GeExpensePaymentListQueryForm queryForm ) {
        QueryWrapper<GeExpensePaymentList> queryWrapper = new QueryWrapper <GeExpensePaymentList>();
        //getQueryWrapper(queryWrapper,queryForm);
        return geExpensePaymentListMapper.selectPage(new Page<GeExpensePaymentList>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<GeExpensePaymentList> selectList(GeExpensePaymentListQueryForm queryForm) {
        QueryWrapper<GeExpensePaymentList> queryWrapper = new QueryWrapper <GeExpensePaymentList>();
        //getQueryWrapper(queryWrapper,queryForm);
        return geExpensePaymentListMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<GeExpensePaymentList> getQueryWrapper(QueryWrapper<GeExpensePaymentList> queryWrapper,BaseQueryForm<GeExpensePaymentListQueryParam> queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(geExpensePaymentList.getCreateBy())){
            queryWrapper.like(GeExpensePaymentList.CREATE_BY,geExpensePaymentList.getCreateBy());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getCreateUserName())){
            queryWrapper.like(GeExpensePaymentList.CREATE_USER_NAME,geExpensePaymentList.getCreateUserName());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getUpdateBy())){
            queryWrapper.like(GeExpensePaymentList.UPDATE_BY,geExpensePaymentList.getUpdateBy());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getUpdateTime())){
            queryWrapper.like(GeExpensePaymentList.UPDATE_TIME,geExpensePaymentList.getUpdateTime());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getCreateTime())){
            queryWrapper.like(GeExpensePaymentList.CREATE_TIME,geExpensePaymentList.getCreateTime());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getVersion())){
            queryWrapper.like(GeExpensePaymentList.VERSION,geExpensePaymentList.getVersion());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getDocumentId())){
            queryWrapper.like(GeExpensePaymentList.DOCUMENT_ID,geExpensePaymentList.getDocumentId());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getObjectType())){
            queryWrapper.like(GeExpensePaymentList.OBJECT_TYPE,geExpensePaymentList.getObjectType());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getPayAmount())){
            queryWrapper.like(GeExpensePaymentList.PAY_AMOUNT,geExpensePaymentList.getPayAmount());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getVendorId())){
            queryWrapper.like(GeExpensePaymentList.VENDOR_ID,geExpensePaymentList.getVendorId());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getVendorName())){
            queryWrapper.like(GeExpensePaymentList.VENDOR_NAME,geExpensePaymentList.getVendorName());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getBankId())){
            queryWrapper.like(GeExpensePaymentList.BANK_ID,geExpensePaymentList.getBankId());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getPayStatus())){
            queryWrapper.like(GeExpensePaymentList.PAY_STATUS,geExpensePaymentList.getPayStatus());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getRemark())){
            queryWrapper.like(GeExpensePaymentList.REMARK,geExpensePaymentList.getRemark());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getEx1())){
            queryWrapper.like(GeExpensePaymentList.EX1,geExpensePaymentList.getEx1());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getEx2())){
            queryWrapper.like(GeExpensePaymentList.EX2,geExpensePaymentList.getEx2());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getEx3())){
            queryWrapper.like(GeExpensePaymentList.EX3,geExpensePaymentList.getEx3());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getEx4())){
            queryWrapper.like(GeExpensePaymentList.EX4,geExpensePaymentList.getEx4());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getEx5())){
            queryWrapper.like(GeExpensePaymentList.EX5,geExpensePaymentList.getEx5());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getEx6())){
            queryWrapper.like(GeExpensePaymentList.EX6,geExpensePaymentList.getEx6());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getEx7())){
            queryWrapper.like(GeExpensePaymentList.EX7,geExpensePaymentList.getEx7());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getEx8())){
            queryWrapper.like(GeExpensePaymentList.EX8,geExpensePaymentList.getEx8());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getEx9())){
            queryWrapper.like(GeExpensePaymentList.EX9,geExpensePaymentList.getEx9());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getEx10())){
            queryWrapper.like(GeExpensePaymentList.EX10,geExpensePaymentList.getEx10());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getEx11())){
            queryWrapper.like(GeExpensePaymentList.EX11,geExpensePaymentList.getEx11());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getEx12())){
            queryWrapper.like(GeExpensePaymentList.EX12,geExpensePaymentList.getEx12());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getEx13())){
            queryWrapper.like(GeExpensePaymentList.EX13,geExpensePaymentList.getEx13());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getEx14())){
            queryWrapper.like(GeExpensePaymentList.EX14,geExpensePaymentList.getEx14());
        }
        if(StringUtils.isNotBlank(geExpensePaymentList.getEx15())){
            queryWrapper.like(GeExpensePaymentList.EX15,geExpensePaymentList.getEx15());
        }
        return queryWrapper;
    }
     */
}

