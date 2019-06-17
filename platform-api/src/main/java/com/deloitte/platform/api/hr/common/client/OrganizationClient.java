package com.deloitte.platform.api.hr.common.client;

import com.deloitte.platform.api.hr.common.param.HrOrganizationQueryForm;
import com.deloitte.platform.api.hr.common.vo.HrOrganizationForm;
import com.deloitte.platform.api.hr.common.vo.HrOrganizationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-18
 * @Description :  Organization控制器接口
 * @Modified :
 */
public interface OrganizationClient {

    public static final String path="/hr/organization";


    /**
     *  POST---新增
     * @param hrOrganizationForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute HrOrganizationForm hrOrganizationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, hrOrganizationForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody HrOrganizationForm hrOrganizationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<HrOrganizationVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   hrOrganizationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<HrOrganizationVo>> list(@Valid @RequestBody HrOrganizationQueryForm hrOrganizationQueryForm);


    /**
     *  POST----复杂查询
     * @param  hrOrganizationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<HrOrganizationVo>> search(@Valid @RequestBody HrOrganizationQueryForm hrOrganizationQueryForm);

    /**
     * GET ---- 获取组织树
     * @return
     */
    @GetMapping(value = path+"/getTree")
    Result<HrOrganizationVo> getTree();


}
