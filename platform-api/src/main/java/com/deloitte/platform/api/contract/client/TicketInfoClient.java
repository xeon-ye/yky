package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.TicketInfoQueryForm;
import com.deloitte.platform.api.contract.vo.TicketInfoForm;
import com.deloitte.platform.api.contract.vo.TicketInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  TicketInfo控制器接口
 * @Modified :
 */
public interface TicketInfoClient {

    public static final String path="/contract/ticket-info";


    /**
     *  POST---新增
     * @param ticketInfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute TicketInfoForm ticketInfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, ticketInfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody TicketInfoForm ticketInfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<TicketInfoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   ticketInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<TicketInfoVo>> list(@Valid @RequestBody TicketInfoQueryForm ticketInfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  ticketInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<TicketInfoVo>> search(@Valid @RequestBody TicketInfoQueryForm ticketInfoQueryForm);
}
