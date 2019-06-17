package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.TemplatePersonMapQueryForm;
import com.deloitte.platform.api.contract.vo.TemplatePersonMapForm;
import com.deloitte.platform.api.contract.vo.TemplatePersonMapVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  TemplatePersonMap控制器接口
 * @Modified :
 */
public interface TemplatePersonMapClient {

    public static final String path="/contract/template-person-map";


    /**
     *  POST---新增
     * @param templatePersonMapForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute TemplatePersonMapForm templatePersonMapForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, templatePersonMapForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody TemplatePersonMapForm templatePersonMapForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<TemplatePersonMapVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   templatePersonMapQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<TemplatePersonMapVo>> list(@Valid @RequestBody TemplatePersonMapQueryForm templatePersonMapQueryForm);


    /**
     *  POST----复杂查询
     * @param  templatePersonMapQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<TemplatePersonMapVo>> search(@Valid @RequestBody TemplatePersonMapQueryForm templatePersonMapQueryForm);
}
