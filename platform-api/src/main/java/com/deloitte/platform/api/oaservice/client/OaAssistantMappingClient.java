package com.deloitte.platform.api.oaservice.client;

import com.deloitte.platform.api.oaservice.param.OaAssistantMappingQueryForm;
import com.deloitte.platform.api.oaservice.vo.OaAssistantMappingForm;
import com.deloitte.platform.api.oaservice.vo.OaAssistantMappingVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-03
 * @Description :  OaAssistantMapping控制器接口
 * @Modified :
 */
public interface OaAssistantMappingClient {

    public static final String path="/oaservice/assistantMapping";


    /**
     *  POST---新增
     * @param oaAssistantMappingForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OaAssistantMappingForm oaAssistantMappingForm);

    /**
     *  POST---新增
     * @param oaAssistantMappingForm
     * @return
     */
    @PostMapping(value = path+"/save")
    Result save(@Valid @RequestBody OaAssistantMappingForm[] oaAssistantMappingForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") String id);

    /**
     *  Patch----部分更新
     * @param  id, oaAssistantMappingForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") String id, @Valid @RequestBody OaAssistantMappingForm oaAssistantMappingForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OaAssistantMappingVo> get(@PathVariable(value="id") String id);


    /**
     *  POST----列表查询
     * @param   oaAssistantMappingQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OaAssistantMappingVo>> list(@Valid @RequestBody OaAssistantMappingQueryForm oaAssistantMappingQueryForm);


    /**
     *  POST----复杂查询
     * @param  oaAssistantMappingQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OaAssistantMappingVo>> search(@Valid @RequestBody OaAssistantMappingQueryForm oaAssistantMappingQueryForm);
}
