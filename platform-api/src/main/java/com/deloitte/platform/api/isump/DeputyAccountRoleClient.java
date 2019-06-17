package com.deloitte.platform.api.isump;

import com.deloitte.platform.api.isump.param.DeputyAccountRoleQueryForm;
import com.deloitte.platform.api.isump.vo.DeputyAccountRoleForm;
import com.deloitte.platform.api.isump.vo.DeputyAccountRoleVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  DeputyAccountRole控制器接口
 * @Modified :
 */
public interface DeputyAccountRoleClient {

    public static final String path="/isump/deputy-account-role";


    /**
     *  POST---新增
     * @param deputyAccountRoleForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute DeputyAccountRoleForm deputyAccountRoleForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, deputyAccountRoleForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody DeputyAccountRoleForm deputyAccountRoleForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<DeputyAccountRoleVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<DeputyAccountRoleVo>> list(@Valid @RequestBody DeputyAccountRoleQueryForm deputyAccountRoleQueryForm);


    /**
     *  POST----复杂查询
     * @param  deputyAccountRoleQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<DeputyAccountRoleVo>> search(@Valid @RequestBody DeputyAccountRoleQueryForm deputyAccountRoleQueryForm);
}
