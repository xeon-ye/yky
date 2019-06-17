package com.deloitte.services.contract.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.deloitte.platform.api.contract.param.OrderInfoQueryForm;
import com.deloitte.platform.api.contract.vo.BasicInfoVo;
import com.deloitte.platform.api.contract.vo.OrderInfoForm;
import com.deloitte.platform.api.contract.vo.OrderInfoVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.platform.common.core.util.BeanUtils;
import com.deloitte.services.contract.common.enums.ContractErrorType;
import com.deloitte.services.contract.common.util.AssertUtils;
import com.deloitte.services.contract.entity.BasicOrderMap;
import com.deloitte.services.contract.entity.OrderInfo;
import com.deloitte.services.contract.mapper.BasicInfoMapper;
import com.deloitte.services.contract.mapper.BasicOrderMapMapper;
import com.deloitte.services.contract.mapper.OrderInfoMapper;
import com.deloitte.services.contract.service.IOrderInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  OrderInfo服务实现类
 * @Modified :
 */
@Service
@Transactional
public class OrderInfoServiceImpl extends ServiceImpl<OrderInfoMapper, OrderInfo> implements IOrderInfoService {


    @Autowired
    private OrderInfoMapper orderInfoMapper;
    @Autowired
    private BasicInfoMapper basicInfoMapper;
    @Autowired
    private BasicOrderMapMapper basicOrderMapMapper;

    @Override
    public IPage<OrderInfo> selectPage(OrderInfoQueryForm queryForm ) {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper <OrderInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return orderInfoMapper.selectPage(new Page<OrderInfo>(queryForm.getCurrentPage(),queryForm.getPageSize()), queryWrapper);

        }

    @Override
    public List<OrderInfo> selectList(OrderInfoQueryForm queryForm) {
        QueryWrapper<OrderInfo> queryWrapper = new QueryWrapper <OrderInfo>();
        //getQueryWrapper(queryWrapper,queryForm);
        return orderInfoMapper.selectList(queryWrapper);
    }

    /**
     *  通用查询
     * @param queryWrapper,queryForm
     * @return
    public QueryWrapper<OrderInfo> getQueryWrapper(QueryWrapper<OrderInfo> queryWrapper,OrderInfoQueryForm queryForm){
        //条件拼接
        if(StringUtils.isNotBlank(queryForm.getOrderNum())){
            queryWrapper.eq(OrderInfo.ORDER_NUM,queryForm.getOrderNum());
        }
        if(StringUtils.isNotBlank(queryForm.getOrderAmount())){
            queryWrapper.eq(OrderInfo.ORDER_AMOUNT,queryForm.getOrderAmount());
        }
        if(StringUtils.isNotBlank(queryForm.getOrgCode())){
            queryWrapper.eq(OrderInfo.ORG_CODE,queryForm.getOrgCode());
        }
        if(StringUtils.isNotBlank(queryForm.getOrg())){
            queryWrapper.eq(OrderInfo.ORG,queryForm.getOrg());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateTime())){
            queryWrapper.eq(OrderInfo.CREATE_TIME,queryForm.getCreateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getCreateBy())){
            queryWrapper.eq(OrderInfo.CREATE_BY,queryForm.getCreateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateTime())){
            queryWrapper.eq(OrderInfo.UPDATE_TIME,queryForm.getUpdateTime());
        }
        if(StringUtils.isNotBlank(queryForm.getUpdateBy())){
            queryWrapper.eq(OrderInfo.UPDATE_BY,queryForm.getUpdateBy());
        }
        if(StringUtils.isNotBlank(queryForm.getIsUsed())){
            queryWrapper.eq(OrderInfo.IS_USED,queryForm.getIsUsed());
        }
        if(StringUtils.isNotBlank(queryForm.getContractId())){
            queryWrapper.eq(OrderInfo.CONTRACT_ID,queryForm.getContractId());
        }
        if(StringUtils.isNotBlank(queryForm.getContractName())){
            queryWrapper.eq(OrderInfo.CONTRACT_NAME,queryForm.getContractName());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField1())){
            queryWrapper.eq(OrderInfo.SPARE_FIELD_1,queryForm.getSpareField1());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField2())){
            queryWrapper.eq(OrderInfo.SPARE_FIELD_2,queryForm.getSpareField2());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField3())){
            queryWrapper.eq(OrderInfo.SPARE_FIELD_3,queryForm.getSpareField3());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField4())){
            queryWrapper.eq(OrderInfo.SPARE_FIELD_4,queryForm.getSpareField4());
        }
        if(StringUtils.isNotBlank(queryForm.getSpareField5())){
            queryWrapper.eq(OrderInfo.SPARE_FIELD_5,queryForm.getSpareField5());
        }
        return queryWrapper;
    }
     */

    public OrderInfo saveOrder(OrderInfoForm orderInfoForm, UserVo userVo){
        OrderInfo orderInfo = new BeanUtils<OrderInfo>().copyObj(orderInfoForm,OrderInfo.class);
        // 查询关联合同信息
        Long choiceId = orderInfo.getContractId();
        List<BasicInfoVo> basicInfoVos = basicInfoMapper.queryCorrelationBasic(choiceId.toString());
        AssertUtils.asserts(basicInfoVos == null || basicInfoVos.size() <= 0, ContractErrorType.CONTRACT_IS_NULL);
        String relationCode = basicInfoVos.get(0).getRelationCode();
        if (relationCode != null && relationCode.equals("REL3000")){
            BasicInfoVo basicInfoVoRel  = basicInfoVos.get(0);
            basicInfoVos = new ArrayList<>();
            basicInfoVos.add(basicInfoVoRel);
        }

        orderInfo.setCreateBy(userVo.getId());
        orderInfoMapper.insert(orderInfo);
        Long orderId = orderInfo.getId();
        for (int i = 0; i < basicInfoVos.size(); i++){
            String contractId = basicInfoVos.get(i).getId();
            BasicOrderMap basicOrderMap = new BasicOrderMap();
            basicOrderMap.setContractId(Long.parseLong(contractId));
            basicOrderMap.setOrderId(orderId);
            basicOrderMap.setIsUsed("1");
            basicOrderMap.setCreateBy(userVo.getId());
            basicOrderMapMapper.insert(basicOrderMap);
        }
        return orderInfo;
    }

    /**
     * 根据id删除订单信息
     * @param orderInfoForm
     * @return
     */
    public boolean deleteOrderById(OrderInfoForm orderInfoForm){
        Long orderId = orderInfoForm.getId();
        orderInfoMapper.deleteById(orderId);
        QueryWrapper<BasicOrderMap> queryWrapper = new QueryWrapper <>();
        queryWrapper.eq(BasicOrderMap.ORDER_ID, orderId);
        basicOrderMapMapper.delete(queryWrapper);
        return true;
    }

    public List<OrderInfoVo> selectListByContractId(String contractId){
        return orderInfoMapper.selectListByContractId(contractId);
    }
}

