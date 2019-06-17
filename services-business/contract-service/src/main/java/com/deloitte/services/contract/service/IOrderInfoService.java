package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.OrderInfoQueryForm;
import com.deloitte.platform.api.contract.vo.OrderInfoForm;
import com.deloitte.platform.api.contract.vo.OrderInfoVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.services.contract.entity.OrderInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : OrderInfo服务类接口
 * @Modified :
 */
public interface IOrderInfoService extends IService<OrderInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<OrderInfo>
     */
    IPage<OrderInfo> selectPage(OrderInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<OrderInfo>
     */
    List<OrderInfo> selectList(OrderInfoQueryForm queryForm);

    /**
     * 保存订单信息
     * @param orderInfoForm
     * @param userVo
     * @return
     */
    OrderInfo saveOrder(OrderInfoForm orderInfoForm, UserVo userVo);

    boolean deleteOrderById(OrderInfoForm orderInfoForm);

    List<OrderInfoVo> selectListByContractId(String contractId);
}
