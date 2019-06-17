package com.deloitte.platform.api.isump;

import com.deloitte.platform.api.isump.param.OrgRoleQueryForm;
import com.deloitte.platform.api.isump.vo.OrgRoleForm;
import com.deloitte.platform.api.isump.vo.OrgRoleVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  OrgRole控制器接口
 * @Modified :
 */
public interface OrgRoleClient {

    public static final String path="/isump/org-role";


    /**
     *  POST---新增
     * @param orgRoleForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OrgRoleForm orgRoleForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, orgRoleForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody OrgRoleForm orgRoleForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OrgRoleVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   orgRoleForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OrgRoleVo>> list(@Valid @RequestBody OrgRoleQueryForm orgRoleQueryForm);


    /**
     *  POST----复杂查询
     * @param  orgRoleQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OrgRoleVo>> search(@Valid @RequestBody OrgRoleQueryForm orgRoleQueryForm);
}
