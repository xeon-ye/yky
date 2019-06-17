package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.StandardTemplateQueryForm;
import com.deloitte.platform.api.contract.vo.StandardTemplateForm;
import com.deloitte.platform.api.contract.vo.StandardTemplateVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  StandardTemplate控制器接口
 * @Modified :
 */
public interface StandardTemplateClient {

    public static final String path="/contract/standard-template";


    /**
     *  POST---新增
     * @param standardTemplateForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute StandardTemplateForm standardTemplateForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, standardTemplateForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody StandardTemplateForm standardTemplateForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<StandardTemplateVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   standardTemplateQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<StandardTemplateVo>> list(@Valid @RequestBody StandardTemplateQueryForm standardTemplateQueryForm);


    /**
     *  POST----复杂查询
     * @param  standardTemplateQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<StandardTemplateVo>> search(@Valid @RequestBody StandardTemplateQueryForm standardTemplateQueryForm);
}
