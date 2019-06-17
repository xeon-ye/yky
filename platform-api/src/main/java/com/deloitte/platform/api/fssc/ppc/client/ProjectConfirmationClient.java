package com.deloitte.platform.api.fssc.ppc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.ppc.param.ProjectConfirmationQueryForm;
import com.deloitte.platform.api.fssc.ppc.vo.ProjectConfirmationForm;
import com.deloitte.platform.api.fssc.ppc.vo.ProjectConfirmationVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description :  ProjectConfirmation控制器接口
 * @Modified :
 */
public interface ProjectConfirmationClient {

    public static final String path="/ppc/project-confirmation";


    /**
     *  POST---新增
     * @param projectConfirmationForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ProjectConfirmationForm projectConfirmationForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, projectConfirmationForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ProjectConfirmationForm projectConfirmationForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ProjectConfirmationVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   projectConfirmationQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ProjectConfirmationVo>> list(@Valid @RequestBody ProjectConfirmationQueryForm projectConfirmationQueryForm);


    /**
     *  POST----复杂查询
     * @param  projectConfirmationQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ProjectConfirmationVo>> search(@Valid @RequestBody ProjectConfirmationQueryForm projectConfirmationQueryForm);
}
