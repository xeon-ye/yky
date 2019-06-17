package com.deloitte.platform.api.hr.check.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckTimeQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckTimeForm;
import com.deloitte.platform.api.hr.check.vo.CheckTimeVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :  CheckTime控制器接口
 * @Modified :
 */
public interface CheckTimeClient {

    public static final String path="/hr/check-time";


    /**
     *  POST---新增
     * @param checkTimeForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckTimeForm checkTimeForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkTimeForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckTimeForm checkTimeForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckTimeVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkTimeQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckTimeVo>> list(@Valid @RequestBody CheckTimeQueryForm checkTimeQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkTimeQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckTimeVo>> search(@Valid @RequestBody CheckTimeQueryForm checkTimeQueryForm);

    /**
     *  Patch----部分更新
     * @param   ids
     * @return
     */
    @PostMapping(value = path+"/batchDelete")
    Result batchDelete(@Valid @RequestBody List<String> ids);
}
