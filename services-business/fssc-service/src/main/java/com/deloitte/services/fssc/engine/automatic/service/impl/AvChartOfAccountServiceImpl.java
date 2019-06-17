package com.deloitte.services.fssc.engine.automatic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvAccountElementQueryForm;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvChartOfAccountQueryForm;
import com.deloitte.platform.api.fssc.engine.automatic.param.AvUtilQueryForm;
import com.deloitte.services.fssc.engine.automatic.entity.AvAccountElement;
import com.deloitte.services.fssc.engine.automatic.entity.AvBaseElement;
import com.deloitte.services.fssc.engine.automatic.entity.AvChartOfAccount;
import com.deloitte.services.fssc.engine.automatic.entity.AvLedgerUnitRelation;
import com.deloitte.services.fssc.engine.automatic.mapper.AvBaseElementMapper;
import com.deloitte.services.fssc.engine.automatic.mapper.AvChartOfAccountMapper;
import com.deloitte.services.fssc.engine.automatic.mapper.AvLedgerUnitRelationMapper;
import com.deloitte.services.fssc.engine.automatic.service.IAvChartOfAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description :  AvChartOfAccount服务实现类
 * @Modified :
 */
@Service
@Transactional
public class AvChartOfAccountServiceImpl extends ServiceImpl<AvChartOfAccountMapper, AvChartOfAccount> implements IAvChartOfAccountService {


    @Autowired
    private AvChartOfAccountMapper avChartOfAccountMapper;
    @Autowired
    private AvLedgerUnitRelationMapper avLedgerUnitRelationMapper;
    @Autowired
    private AvBaseElementMapper avBaseElementMapper;

    @Override
    public IPage<AvChartOfAccount> selectPage(AvChartOfAccountQueryForm queryForm ) {
        QueryWrapper<AvChartOfAccount> queryWrapper = new QueryWrapper <AvChartOfAccount>();
        getQueryWrapper(queryWrapper,queryForm);
        return avChartOfAccountMapper.selectPage(new Page<AvChartOfAccount>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<AvChartOfAccount> selectList(AvChartOfAccountQueryForm queryForm) {
        QueryWrapper<AvChartOfAccount> queryWrapper = new QueryWrapper <AvChartOfAccount>();
        getQueryWrapper(queryWrapper,queryForm);
        return avChartOfAccountMapper.selectList(queryWrapper);
    }

    public List<AvBaseElement> selectUnitList(AvUtilQueryForm queryForm){
        QueryWrapper<AvBaseElement> queryWrapper = new QueryWrapper <AvBaseElement>();
        Integer start = (queryForm.getCurrentPage()-1)*queryForm.getPageSize();
        Integer end = (queryForm.getCurrentPage())*queryForm.getPageSize();
        queryForm.setStart(start);//设置开始页
        queryForm.setEnd(end);//设置结束页
//        getQueryWrapper(queryWrapper,queryForm);
        return avChartOfAccountMapper.getUtilList(queryForm);
    }

    public Integer selectUnitListCount(AvUtilQueryForm queryForm){
        QueryWrapper<AvBaseElement> queryWrapper = new QueryWrapper <AvBaseElement>();
        return avChartOfAccountMapper.getUtilListCount(queryForm);
    }

    public List<AvAccountElement> selectAccountFrameList(Long ledgerId){
        //getQueryWrapper(queryWrapper,queryForm);
        return avChartOfAccountMapper.getAccountFrameList(ledgerId);
    }


    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
     */
    public QueryWrapper<AvChartOfAccount> getQueryWrapper(QueryWrapper<AvChartOfAccount> queryWrapper,AvChartOfAccountQueryForm queryForm){
        //条件拼接
        if(queryForm.getLedgerId()!=null){
            queryWrapper.eq(AvChartOfAccount.LEDGER_ID,queryForm.getLedgerId());
        }
        if(StringUtils.isNotBlank(queryForm.getName())){
            queryWrapper.like(AvChartOfAccount.NAME,queryForm.getName());
        }
        if(StringUtils.isNotBlank(queryForm.getDescription())){
            queryWrapper.eq(AvChartOfAccount.DESCRIPTION,queryForm.getDescription());
        }
        if(StringUtils.isNotBlank(queryForm.getCurrencyCode())){
            queryWrapper.eq(AvChartOfAccount.CURRENCY_CODE,queryForm.getCurrencyCode());
        }
        if(queryForm.getCreateDate()!=null){
            queryWrapper.eq(AvChartOfAccount.CREATE_DATE,queryForm.getCreateDate());
        }
        if(queryForm.getCreateBy()!=null){
            queryWrapper.eq(AvChartOfAccount.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx1())){
            queryWrapper.eq(AvChartOfAccount.ETX_1,queryForm.getEtx1());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx2())){
            queryWrapper.eq(AvChartOfAccount.ETX_2,queryForm.getEtx2());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx3())){
            queryWrapper.eq(AvChartOfAccount.ETX_3,queryForm.getEtx3());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx4())){
            queryWrapper.eq(AvChartOfAccount.ETX_4,queryForm.getEtx4());
        }
        if(StringUtils.isNotBlank(queryForm.getEtx5())){
            queryWrapper.eq(AvChartOfAccount.ETX_5,queryForm.getEtx5());
        }
        if(StringUtils.isNotBlank(queryForm.getShortName())){
            queryWrapper.eq(AvChartOfAccount.SHORT_NAME,queryForm.getShortName());
        }
        if(StringUtils.isNotBlank(queryForm.getStatus())){
            queryWrapper.eq(AvChartOfAccount.STATUS,queryForm.getStatus());
        }
        return queryWrapper;
    }

    /**
     *
     * @param code 公司编码
     * @param type  财和预（C/Y）
     * @return
     */
   public List<AvBaseElement> selectBaseElementForAccount(String code,String type){
       QueryWrapper<AvLedgerUnitRelation> queryWrapper = new QueryWrapper <AvLedgerUnitRelation>();
       queryWrapper.eq(AvLedgerUnitRelation.BALANCE_CODE,code);
        List<AvLedgerUnitRelation> alurlist = avLedgerUnitRelationMapper.selectList(queryWrapper);//获取到该公司使用那个账薄；
       List<AvBaseElement> abeList = new ArrayList<AvBaseElement>();
       if(alurlist.size()>0){
           abeList = avBaseElementMapper.selectBaseElementForAccount(type,alurlist.get(0).getLedgerId());
       }
       return abeList;

   }
}

