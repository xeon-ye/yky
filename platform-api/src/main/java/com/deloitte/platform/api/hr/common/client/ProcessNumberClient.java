package com.deloitte.platform.api.hr.common.client;

import com.deloitte.platform.api.hr.common.param.ProcessNumberQueryForm;
import com.deloitte.platform.api.hr.common.vo.ProcessNumberForm;
import com.deloitte.platform.api.hr.common.vo.ProcessNumberVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-15
 * @Description :  ProcessNumber控制器接口
 * @Modified :
 */
public interface ProcessNumberClient {

    public static final String path="/hr/process-number";


    /**
     *  POST---新增
     * @param processNumberForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ProcessNumberForm processNumberForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, processNumberForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ProcessNumberForm processNumberForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ProcessNumberVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   processNumberQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ProcessNumberVo>> list(@Valid @RequestBody ProcessNumberQueryForm processNumberQueryForm);


    /**
     *  POST----复杂查询
     * @param  processNumberQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ProcessNumberVo>> search(@Valid @RequestBody ProcessNumberQueryForm processNumberQueryForm);
}
