package com.deloitte.platform.api.isump;

import com.deloitte.platform.api.isump.param.RoleQueryForm;
import com.deloitte.platform.api.isump.vo.RoleForm;
import com.deloitte.platform.api.isump.vo.RoleVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  Role控制器接口
 * @Modified :
 */
public interface RoleClient {

    public static final String path="/isump/role";


    /**
     *  POST---新增
     * @param roleForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute RoleForm roleForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, roleForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody RoleForm roleForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<RoleVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<RoleVo>> list(@Valid @RequestBody RoleQueryForm roleQueryForm);


    /**
     *  POST----复杂查询
     * @param  roleQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<RoleVo>> search(@Valid @RequestBody RoleQueryForm roleQueryForm);

    /**
     * get ----- 根据副账号ID查询当前副账号的角色（包括当前岗位共有角色和私有角色）
     * @param id
     * @return
     */
    @PostMapping(value = path+"/getByDeputyAccountId")
    Result<List<RoleVo>> getByDeputyAccountId(@RequestParam("id") long id,@RequestParam("type") String type,@RequestParam("service") String service);
}
