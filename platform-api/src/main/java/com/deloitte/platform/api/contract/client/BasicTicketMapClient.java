package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.BasicTicketMapQueryForm;
import com.deloitte.platform.api.contract.vo.BasicTicketMapForm;
import com.deloitte.platform.api.contract.vo.BasicTicketMapVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  BasicTicketMap控制器接口
 * @Modified :
 */
public interface BasicTicketMapClient {

    public static final String path="/contract/basic-ticket-map";


    /**
     *  POST---新增
     * @param basicTicketMapForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute BasicTicketMapForm basicTicketMapForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, basicTicketMapForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody BasicTicketMapForm basicTicketMapForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<BasicTicketMapVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   basicTicketMapQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<BasicTicketMapVo>> list(@Valid @RequestBody BasicTicketMapQueryForm basicTicketMapQueryForm);


    /**
     *  POST----复杂查询
     * @param  basicTicketMapQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<BasicTicketMapVo>> search(@Valid @RequestBody BasicTicketMapQueryForm basicTicketMapQueryForm);
}
