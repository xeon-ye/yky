package com.deloitte.platform.api.fssc.ppc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.ppc.param.ProjectBilingInfoQueryForm;
import com.deloitte.platform.api.fssc.ppc.vo.ProjectBilingInfoForm;
import com.deloitte.platform.api.fssc.ppc.vo.ProjectBilingInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description :  ProjectBilingInfo控制器接口
 * @Modified :
 */
public interface ProjectBilingInfoClient {

    public static final String path="/ppc/project-biling-info";


    /**
     *  POST---新增
     * @param projectBilingInfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ProjectBilingInfoForm projectBilingInfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, projectBilingInfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ProjectBilingInfoForm projectBilingInfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ProjectBilingInfoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   projectBilingInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ProjectBilingInfoVo>> list(@Valid @RequestBody ProjectBilingInfoQueryForm projectBilingInfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  projectBilingInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ProjectBilingInfoVo>> search(@Valid @RequestBody ProjectBilingInfoQueryForm projectBilingInfoQueryForm);
}
