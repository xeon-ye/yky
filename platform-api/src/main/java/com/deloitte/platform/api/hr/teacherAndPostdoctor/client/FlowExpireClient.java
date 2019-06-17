package com.deloitte.platform.api.hr.teacherAndPostdoctor.client;

import com.deloitte.platform.api.hr.teacherAndPostdoctor.param.FlowExpireQueryForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.FlowExpireForm;
import com.deloitte.platform.api.hr.teacherAndPostdoctor.vo.FlowExpireVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jetvae
 * @Date : Create in 2019-05-14
 * @Description :  FlowExpire控制器接口
 * @Modified :
 */
public interface FlowExpireClient {

    public static final String path="/hr/flow-expire";


    /**
     *  POST---新增
     * @param flowExpireForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute FlowExpireForm flowExpireForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, flowExpireForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody FlowExpireForm flowExpireForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<FlowExpireVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   flowExpireQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<FlowExpireVo>> list(@Valid @RequestBody FlowExpireQueryForm flowExpireQueryForm);


    /**
     *  POST----复杂查询
     * @param  flowExpireQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<FlowExpireVo>> search(@Valid @RequestBody FlowExpireQueryForm flowExpireQueryForm);
}
