package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.TicketInfoQueryForm;
import com.deloitte.platform.api.contract.vo.BasicInfoVo;
import com.deloitte.platform.api.contract.vo.TicketInfoForm;
import com.deloitte.platform.api.contract.vo.TicketInfoVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.contract.common.enums.ContractErrorType;
import com.deloitte.services.contract.common.util.AssertUtils;
import com.deloitte.services.contract.entity.BasicTicketMap;
import com.deloitte.services.contract.entity.OrderInfo;
import com.deloitte.services.contract.entity.TicketInfo;
import com.deloitte.services.contract.mapper.BasicInfoMapper;
import com.deloitte.services.contract.mapper.BasicTicketMapMapper;
import com.deloitte.services.contract.mapper.TicketInfoMapper;
import com.deloitte.services.contract.service.ITicketInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  TicketInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class TicketInfoServiceImpl extends ServiceImpl<TicketInfoMapper, TicketInfo> implements ITicketInfoService {


    @Autowired
    private TicketInfoMapper ticketInfoMapper;
    @Autowired
    private BasicInfoMapper basicInfoMapper;
    @Autowired
    private BasicTicketMapMapper basicTicketMapMappere;

    @Override
    public IPage<TicketInfo> selectPage(TicketInfoQueryForm queryForm ) {
        QueryWrapper<TicketInfo> queryWrapper = new QueryWrapper <TicketInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return ticketInfoMapper.selectPage(new Page<TicketInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<TicketInfo> selectList(TicketInfoQueryForm queryForm) {
        QueryWrapper<TicketInfo> queryWrapper = new QueryWrapper <TicketInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return ticketInfoMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<TicketInfo> getQueryWrapper(QueryWrapper<TicketInfo> queryWrapper,TicketInfoQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getTaxpayerCode())){
            queryWrapper.eq(TicketInfo.TAXPAYER_CODE,queryForm.getTaxpayerCode());
        }
        if(StringUtils.isNotBlank(queryForm.getTaxpayer())){
            queryWrapper.eq(TicketInfo.TAXPAYER,queryForm.getTaxpayer());
        }
        if(StringUtils.isNotBlank(queryForm.getTaxNum())){
            queryWrapper.eq(TicketInfo.TAX_NUM,queryForm.getTaxNum());
        }
        if(StringUtils.isNotBlank(queryForm.getInvoiceCode())){
            queryWrapper.eq(TicketInfo.INVOICE_CODE,queryForm.getInvoiceCode());
        }
        if(StringUtils.isNotBlank(queryForm.getInvoiceNum())){
            queryWrapper.eq(TicketInfo.INVOICE_NUM,queryForm.getInvoiceNum());
        }
        if(StringUtils.isNotBlank(queryForm.getAmountExcludTax())){
            queryWrapper.eq(TicketInfo.AMOUNT_EXCLUD_TAX,queryForm.getAmountExcludTax());
        }
        if(StringUtils.isNotBlank(queryForm.getTaxRate())){
            queryWrapper.eq(TicketInfo.TAX_RATE,queryForm.getTaxRate());
        }
        if(StringUtils.isNotBlank(queryForm.getTaxAmount())){
            queryWrapper.eq(TicketInfo.TAX_AMOUNT,queryForm.getTaxAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getAmount())){
            queryWrapper.eq(TicketInfo.AMOUNT,queryForm.getAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getTicketTime())){
            queryWrapper.eq(TicketInfo.TICKET_TIME,queryForm.getTicketTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(TicketInfo.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(TicketInfo.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(TicketInfo.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(TicketInfo.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(TicketInfo.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(TicketInfo.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(TicketInfo.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(TicketInfo.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(TicketInfo.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(TicketInfo.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        return queryWrapper;
    }
     */

    /**
     * 保持开票信息
     * @param ticketInfoForm
     * @param userVo
     * @return
     */
    public TicketInfo saveTicket(TicketInfoForm ticketInfoForm, UserVo userVo){
        TicketInfo ticketInfo = new BeanUtils<TicketInfo>().copyObj(ticketInfoForm,TicketInfo.class);
        // 查询关联合同信息
        Long choiceId = ticketInfoForm.getContractId();
        List<BasicInfoVo> basicInfoVos = basicInfoMapper.queryCorrelationBasic(choiceId.toString());
        AssertUtils.asserts(basicInfoVos == null || basicInfoVos.size() <= 0, ContractErrorType.CONTRACT_IS_NULL);
        String relationCode = basicInfoVos.get(0).getRelationCode();
        if (relationCode != null && relationCode.equals("REL3000")){
            BasicInfoVo basicInfoVoRel  = basicInfoVos.get(0);
            basicInfoVos = new ArrayList<>();
            basicInfoVos.add(basicInfoVoRel);
        }

        // 收支类型
        String incomeExpenditureTypeCode = basicInfoVos.get(0).getIncomeExpenditureTypeCode();

        ticketInfo.setCreateBy(userVo.getId());
        ticketInfo.setContractType(incomeExpenditureTypeCode);
        ticketInfoMapper.insert(ticketInfo);

        Long ticketId = ticketInfo.getId();
        for (int i = 0; i < basicInfoVos.size(); i++){
            String contractId = basicInfoVos.get(i).getId();
            BasicTicketMap basicTicketMap = new BasicTicketMap();
            basicTicketMap.setContractId(Long.parseLong(contractId));
            basicTicketMap.setTicketId(ticketId);
            basicTicketMap.setIsUsed("1");
            basicTicketMap.setCreateBy(userVo.getId());
            basicTicketMapMappere.insert(basicTicketMap);
        }
        return ticketInfo;
    }

    /**
     * 根据id删除开票信息
     * @param ticketInfoForm
     * @return
     */
    public boolean deleteTicketById(TicketInfoForm ticketInfoForm){
        Long ticketId = ticketInfoForm.getId();
        ticketInfoMapper.deleteById(ticketId);
        QueryWrapper<BasicTicketMap> queryWrapper = new QueryWrapper <>();
        queryWrapper.eq(BasicTicketMap.TICKET_ID, ticketId);
        basicTicketMapMappere.delete(queryWrapper);
        return true;
    }

    public List<TicketInfoVo> selectListByContractId(String contractId){
        return ticketInfoMapper.selectListByContractId(contractId);
    }
}

