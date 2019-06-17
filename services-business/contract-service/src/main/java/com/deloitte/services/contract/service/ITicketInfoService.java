package com.deloitte.services.contract.service;

import com.baomidou.mybatisplus.core.metadata.IPage;

import com.deloitte.platform.api.contract.param.TicketInfoQueryForm;
import com.deloitte.platform.api.contract.vo.TicketInfoForm;
import com.deloitte.platform.api.contract.vo.TicketInfoVo;
import com.deloitte.platform.api.isump.vo.UserVo;
import com.deloitte.services.contract.entity.TicketInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description : TicketInfo服务类接口
 * @Modified :
 */
public interface ITicketInfoService extends IService<TicketInfo> {

    /**
     *  分页查询
     * @param queryForm
     * @return IPage<TicketInfo>
     */
    IPage<TicketInfo> selectPage(TicketInfoQueryForm queryForm);

    /**
     *  条件查询
     * @param queryForm
     * @return List<TicketInfo>
     */
    List<TicketInfo> selectList(TicketInfoQueryForm queryForm);

    /**
     * 保持开票信息
     * @param ticketInfoForm
     * @param userVo
     * @return
     */
    TicketInfo saveTicket(TicketInfoForm ticketInfoForm, UserVo userVo);

    /**
     * 根据id删除开票信息
     * @param ticketInfoForm
     * @return
     */
    boolean deleteTicketById(TicketInfoForm ticketInfoForm);

    /**
     * 查询收/发票信息
     * @param contractId
     * @return
     */
    List<TicketInfoVo> selectListByContractId(String contractId);
}
