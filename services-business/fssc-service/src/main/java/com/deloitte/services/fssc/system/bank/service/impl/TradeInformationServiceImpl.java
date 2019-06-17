package com.deloitte.services.fssc.system.bank.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.fssc.trade.param.TradeInformationQueryForm;
import com.deloitte.services.fssc.eums.FsscEums;
import com.deloitte.services.fssc.system.bank.entity.BankUnitInfo;
import com.deloitte.services.fssc.system.bank.entity.TradeInformation;
import com.deloitte.services.fssc.system.bank.mapper.TradeInformationMapper;
import com.deloitte.services.fssc.system.bank.service.IBankUnitInfoService;
import com.deloitte.services.fssc.system.bank.service.ITradeInformationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.deloitte.services.fssc.util.DateUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author : jaws
 * @Date : Create in 2019-05-13
 * @Description :  TradeInformation服务实现类
 * @Modified :
 */
@Service
@Transactional
public class TradeInformationServiceImpl extends ServiceImpl<TradeInformationMapper, TradeInformation> implements ITradeInformationService {


    @Autowired
    private TradeInformationMapper tradeInformationMapper;

    @Autowired
    private IBankUnitInfoService bankUnitInfoService;

    @Override
    public IPage<TradeInformation> selectPage(TradeInformationQueryForm queryForm) {
        QueryWrapper<TradeInformation> queryWrapper = new QueryWrapper<>();
        List<BankUnitInfo> bankUnitInfoList = null;
        if (StringUtils.isNotBlank(queryForm.getInterBankNum()) || StringUtils.isNotBlank(queryForm.getPaymentUnitCode())) {
            bankUnitInfoList = bankUnitInfoService.listByInterLineNum(queryForm.getInterBankNum(), queryForm.getPaymentUnitCode());
            if (CollectionUtils.isEmpty(bankUnitInfoList)) {
                return new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize());
            }
        }
        if (CollectionUtils.isNotEmpty(bankUnitInfoList)) {
            List<String> bankAccountList = bankUnitInfoList.stream().map(BankUnitInfo::getBankAccount).collect(Collectors.toList());
            queryForm.setBankAccountList(bankAccountList);
        }
        getQueryWrapper(queryWrapper, queryForm);
        return tradeInformationMapper.selectPage(new Page<>(queryForm.getCurrentPage(), queryForm.getPageSize()), queryWrapper);
    }

    @Override
    public List<TradeInformation> selectList(TradeInformationQueryForm queryForm) {
        QueryWrapper<TradeInformation> queryWrapper = new QueryWrapper<>();
        List<BankUnitInfo> bankUnitInfoList = null;
        if (StringUtils.isNotBlank(queryForm.getPaymentUnitCode())) {
            bankUnitInfoList = bankUnitInfoService.listByInterLineNum(null, queryForm.getPaymentUnitCode());
        } else if (StringUtils.isNotBlank(queryForm.getInterBankNum()) || StringUtils.isNotBlank(queryForm.getPaymentUnitCode())) {
            bankUnitInfoList = bankUnitInfoService.listByInterLineNum(queryForm.getInterBankNum(), queryForm.getPaymentUnitCode());
        }
        if (CollectionUtils.isNotEmpty(bankUnitInfoList)) {
            List<String> bankNumList = bankUnitInfoList.stream().map(BankUnitInfo::getBankAccount).collect(Collectors.toList());
            queryForm.setBankAccountList(bankNumList);
        }
        getQueryWrapper(queryWrapper, queryForm);
        return tradeInformationMapper.selectList(queryWrapper);
    }

    @Override
    public Integer countByTradeSerialNum(String serialNum) {
        QueryWrapper<TradeInformation> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(TradeInformation.TRADE_SERIAL_NUM,serialNum);
        return this.count(queryWrapper);
    }

    private QueryWrapper<TradeInformation> getQueryWrapper(QueryWrapper<TradeInformation> queryWrapper, TradeInformationQueryForm queryForm) {
        //条件拼接
        if (StringUtils.isNotBlank(queryForm.getPaymentBankAccount())) {
            queryWrapper.like(TradeInformation.PAYMENT_ACCOUNT, queryForm.getPaymentBankAccount());
        }
        if (StringUtils.isNotBlank(queryForm.getTradeSeqNum())) {
            queryWrapper.like(TradeInformation.TRADE_SERIAL_NUM, queryForm.getTradeSeqNum());
        }
        if (StringUtils.isNotBlank(queryForm.getQueryAccount())) {
            queryWrapper.and(wrapper -> wrapper.like(TradeInformation.RECEIVER_ACCOUNT, queryForm.getQueryAccount())
                    .or().like(TradeInformation.PAYMENT_ACCOUNT, queryForm.getQueryAccount()));
        }
        if (queryForm.getTradeAmountDown() != null && queryForm.getTradeAmountUp() != null) {
            queryWrapper.between(TradeInformation.TRADE_AMOUNT, queryForm.getTradeAmountDown(),
                    queryForm.getTradeAmountUp());
        } else if (queryForm.getTradeAmountDown() != null) {
            queryWrapper.ge(TradeInformation.TRADE_AMOUNT, queryForm.getTradeAmountDown());
        } else if (queryForm.getTradeAmountUp() != null) {
            queryWrapper.le(TradeInformation.TRADE_AMOUNT, queryForm.getTradeAmountUp());
        }
        if (FsscEums.TRADE_INFO_QUERY_TYPE_TODAY.getValue().equalsIgnoreCase(queryForm.getQueryType())) {
            queryWrapper.between(TradeInformation.TRADE_TIME, DateUtil.getTodayStart(),
                    DateUtil.getTodayEnd());
        } else {
            if (queryForm.getTradeDateStart() != null) {
                queryWrapper.ge(TradeInformation.TRADE_TIME, queryForm.getTradeDateStart());
            }
            if (queryForm.getTradeDateEnd() != null) {
                queryWrapper.le(TradeInformation.TRADE_TIME, queryForm.getTradeDateEnd());
            }
        }
        if (CollectionUtils.isNotEmpty(queryForm.getBankAccountList())) {
            if (StringUtils.isNotBlank(queryForm.getPaymentUnitCode()) || queryForm.getPaymentBankAccount() != null) {
                queryWrapper.in(TradeInformation.PAYMENT_ACCOUNT, queryForm.getBankAccountList());
            } else {
                queryWrapper.and(wrapper -> wrapper.in(TradeInformation.RECEIVER_ACCOUNT, queryForm.getBankAccountList())
                        .or().in(TradeInformation.PAYMENT_ACCOUNT, queryForm.getBankAccountList()))
                        .or().in(TradeInformation.PROXIED_ACCOUNT, queryForm.getBankAccountList());
            }
        }
        if (StringUtils.isNotBlank(queryForm.getTradeCurrency())) {
            queryWrapper.eq(TradeInformation.TRADE_CURRENCY, queryForm.getTradeCurrency());
        }
        if (StringUtils.isNotBlank(queryForm.getOrgPath())) {
            queryWrapper.like(TradeInformation.ORG_PATH, queryForm.getOrgPath());
        }
        queryWrapper.orderByDesc(TradeInformation.CREATE_TIME);
        return queryWrapper;
    }
}

