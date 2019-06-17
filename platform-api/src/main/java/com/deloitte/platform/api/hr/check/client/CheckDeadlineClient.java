package com.deloitte.platform.api.hr.check.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckDeadlineQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckDeadlineForm;
import com.deloitte.platform.api.hr.check.vo.CheckDeadlineInfoVo;
import com.deloitte.platform.api.hr.check.vo.CheckDeadlineVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :  CheckDeadline控制器接口
 * @Modified :
 */
public interface CheckDeadlineClient {

    public static final String path="/hr/check-deadline";


    /**
     *  POST---新增
     * @param checkDeadlineForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckDeadlineForm checkDeadlineForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkDeadlineForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckDeadlineForm checkDeadlineForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckDeadlineVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkDeadlineQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckDeadlineVo>> list(@Valid @RequestBody CheckDeadlineQueryForm checkDeadlineQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkDeadlineQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckDeadlineVo>> search(@Valid @RequestBody CheckDeadlineQueryForm checkDeadlineQueryForm);

    /**
     *  POST----查询截止时间的详细信息
     * @param  checkDeadlineQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/getCheckDeadlineInfo")
    Result<IPage<CheckDeadlineInfoVo>> getCheckDeadlineInfo(@Valid @RequestBody CheckDeadlineQueryForm checkDeadlineQueryForm);

    /**
     *  Patch----部分更新
     * @param   ids
     * @return
     */
    @PostMapping(value = path+"/batchDelete")
    Result batchDelete(@Valid @RequestBody List<String> ids);
}
