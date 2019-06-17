package com.deloitte.platform.api.hr.check.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckTemplateQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckTemplateForm;
import com.deloitte.platform.api.hr.check.vo.CheckTemplateVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :  CheckTemplate控制器接口
 * @Modified :
 */
public interface CheckTemplateClient {

    public static final String path="/hr/check-template";


    /**
     *  POST---新增
     * @param checkTemplateForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckTemplateForm checkTemplateForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkTemplateForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckTemplateForm checkTemplateForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckTemplateVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkTemplateQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckTemplateVo>> list(@Valid @RequestBody CheckTemplateQueryForm checkTemplateQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkTemplateQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckTemplateVo>> search(@Valid @RequestBody CheckTemplateQueryForm checkTemplateQueryForm);


    /**
     *  Patch----批量删除
     * @param   ids
     * @return
     */
    @PostMapping(value = path+"/batchDelete")
    Result batchDelete(@Valid @RequestBody List<String> ids);

}
