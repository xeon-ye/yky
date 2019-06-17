package com.deloitte.platform.api.oaservice.meeting.client;

import com.deloitte.platform.api.oaservice.meeting.param.OaMeetingAddressQueryForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingAddressForm;
import com.deloitte.platform.api.oaservice.meeting.vo.OaMeetingAddressVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-12
 * @Description :  OaMeetingAddress控制器接口
 * @Modified :
 */
public interface OaMeetingAddressClient {

    public static final String path="/oaservice/meeting/address";


    /**
     *  POST---新增
     * @param oaMeetingAddressForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OaMeetingAddressForm oaMeetingAddressForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, oaMeetingAddressForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody OaMeetingAddressForm oaMeetingAddressForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OaMeetingAddressVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   oaMeetingAddressQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OaMeetingAddressVo>> list(@Valid @RequestBody OaMeetingAddressQueryForm oaMeetingAddressQueryForm);


    /**
     *  POST----复杂查询
     * @param  oaMeetingAddressQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OaMeetingAddressVo>> search(@Valid @RequestBody OaMeetingAddressQueryForm oaMeetingAddressQueryForm);
}
