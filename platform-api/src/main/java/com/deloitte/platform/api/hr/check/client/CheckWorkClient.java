package com.deloitte.platform.api.hr.check.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckWorkQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckWorkForm;
import com.deloitte.platform.api.hr.check.vo.CheckWorkInfoVo;
import com.deloitte.platform.api.hr.check.vo.CheckWorkVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :  CheckWork控制器接口
 * @Modified :
 */
public interface CheckWorkClient {

    public static final String path="/hr/check-work";


    /**
     *  POST---新增
     * @param checkWorkForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckWorkForm checkWorkForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkWorkForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckWorkForm checkWorkForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckWorkVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkWorkQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckWorkVo>> list(@Valid @RequestBody CheckWorkQueryForm checkWorkQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkWorkQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckWorkVo>> search(@Valid @RequestBody CheckWorkQueryForm checkWorkQueryForm);

    /**
     *  POST----查询考核工作详情
     * @param  checkWorkQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/getCheckWorkInfo")
    Result<IPage<CheckWorkInfoVo>> getCheckWorkInfo(@Valid @RequestBody CheckWorkQueryForm checkWorkQueryForm);

    /**
     *  Patch----部分更新
     * @param   ids
     * @return
     */
    @PostMapping(value = path+"/batchDelete")
    Result batchDelete(@Valid @RequestBody List<String> ids);
}
