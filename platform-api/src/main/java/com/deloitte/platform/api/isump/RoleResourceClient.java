package com.deloitte.platform.api.isump;

import com.deloitte.platform.api.isump.param.RoleResourceQueryForm;
import com.deloitte.platform.api.isump.vo.RoleResourceForm;
import com.deloitte.platform.api.isump.vo.RoleResourceVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  RoleResource控制器接口
 * @Modified :
 */
public interface RoleResourceClient {

    public static final String path="/isump/role-resource";


    /**
     *  POST---新增
     * @param roleResourceForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute RoleResourceForm roleResourceForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, roleResourceForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody RoleResourceForm roleResourceForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<RoleResourceVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   roleResourceForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<RoleResourceVo>> list(@Valid @RequestBody RoleResourceQueryForm roleResourceQueryForm);


    /**
     *  POST----复杂查询
     * @param  roleResourceQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<RoleResourceVo>> search(@Valid @RequestBody RoleResourceQueryForm roleResourceQueryForm);
}
