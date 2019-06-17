package com.deloitte.platform.api.isump;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.isump.param.OrganizationQueryForm;
import com.deloitte.platform.api.isump.vo.OrganizationEBSForm;
import com.deloitte.platform.api.isump.vo.OrganizationForm;
import com.deloitte.platform.api.isump.vo.OrganizationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  Organization控制器接口
 * @Modified :
 */
public interface OrganizationClient {

    public static final String path="/isump/organization";


    /**
     *  POST---新增
     * @param organizationForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OrganizationForm organizationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, organizationForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody OrganizationForm organizationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OrganizationVo> get(@PathVariable(value = "id") long id);

    /**
     * get-----根据code获取
     * @param code
     * @return
     */
    @GetMapping(value = path+"/getByCode/{code}")
    Result<OrganizationVo> getByCode(@PathVariable(value = "code") String code);

    /**
     *  POST----列表查询
     * @param
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OrganizationVo>> list(@Valid @RequestBody OrganizationQueryForm organizationQueryForm);


    /**
     *  POST----复杂查询
     * @param  organizationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OrganizationVo>> search(@Valid @RequestBody OrganizationQueryForm organizationQueryForm);

    /**
     *  POST----复杂查询
     * @param  organizationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page2/conditions")
    Result<GdcPage<OrganizationVo>> search2(@Valid @RequestBody OrganizationQueryForm organizationQueryForm);

    /**
     * 获取处室编码和名称的映射
     * @param organizationQueryForm
     * @return
     */
    @PostMapping(value = path+"/searchCodeAndName")
    Result<HashMap<String,String>> searchCodeAndName(@Valid @RequestBody OrganizationQueryForm organizationQueryForm);

    /**
     * 组织编码查询下级组织
     * @param orgCode 组织code
     * @return
     */
    @GetMapping(value = path+"/list/orgcode/{orgCode}")
    Result<List<OrganizationVo>> getOrgTreeByCode(@PathVariable(value = "orgCode") String orgCode);

    /**
     * 组织编码查询下级虚拟组织
     * @param orgCode 组织code
     * @return
     */
    @GetMapping(value = path+"/finction/list/{orgCode}")
    Result<List<OrganizationVo>> getOrgFinctionByCode(@PathVariable(value = "orgCode") String orgCode);

    /**
     * 根据组织code查询下级所有部门
     * @param orgCode 组织code
     * @return
     */
    @GetMapping(value = path+"/dept/list/{orgCode}")
    Result<List<OrganizationVo>> getOrgDeptByCode(@PathVariable(value = "orgCode") String orgCode);

    /**
     * 批量添加
     * @return
     */
    @PostMapping(value = path+"/add/list")
    Result addList(@Valid @RequestBody List<OrganizationEBSForm> organizationEBSForm);
}
