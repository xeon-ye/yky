package com.deloitte.platform.api.isump;

import com.deloitte.platform.api.isump.param.BaseUserQueryForm;
import com.deloitte.platform.api.isump.vo.BaseUserForm;
import com.deloitte.platform.api.isump.vo.BaseUserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  BaseUser控制器接口
 * @Modified :
 */
public interface BaseUserClient {

    public static final String path="/isump/base-user";


    /**
     *  POST---新增
     * @param baseUserForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute BaseUserForm baseUserForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, baseUserForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody BaseUserForm baseUserForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<BaseUserVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<BaseUserVo>> list(@Valid @RequestBody BaseUserQueryForm baseUserQueryForm);


    /**
     *  POST----复杂查询
     * @param  baseUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<BaseUserVo>> search(@Valid @RequestBody BaseUserQueryForm baseUserQueryForm);
}
