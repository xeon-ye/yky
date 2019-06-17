package com.deloitte.platform.api.hr.employee.client;

import com.deloitte.platform.api.hr.employee.param.CorptopublicApplyQueryForm;
import com.deloitte.platform.api.hr.employee.vo.CorptopublicApplyForm;
import com.deloitte.platform.api.hr.employee.vo.CorptopublicApplyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-08
 * @Description :  CorptopublicApply控制器接口
 * @Modified :
 */
public interface CorptopublicApplyClient {

    public static final String path="/hr/corptopublic-apply";


    /**
     *  POST---新增
     * @param corptopublicApplyForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CorptopublicApplyForm corptopublicApplyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, corptopublicApplyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody CorptopublicApplyForm corptopublicApplyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CorptopublicApplyVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   corptopublicApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CorptopublicApplyVo>> list(@Valid @RequestBody CorptopublicApplyQueryForm corptopublicApplyQueryForm);


    /**
     *  POST----复杂查询
     * @param  corptopublicApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CorptopublicApplyVo>> search(@Valid @RequestBody CorptopublicApplyQueryForm corptopublicApplyQueryForm);
}
