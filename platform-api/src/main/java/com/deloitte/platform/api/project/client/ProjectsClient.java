package com.deloitte.platform.api.project.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.project.param.ProjectsQueryForm;
import com.deloitte.platform.api.project.vo.ProjectsForm;
import com.deloitte.platform.api.project.vo.ProjectsVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-26
 * @Description :  Projects控制器接口
 * @Modified :
 */
public interface ProjectsClient {

    public static final String path="/project/projects";


    /**
     *  POST---新增
     * @param projectsForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ProjectsForm projectsForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, projectsForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ProjectsForm projectsForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/get/{id}")
    Result<ProjectsVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   projectsQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ProjectsVo>> list(@Valid @RequestBody ProjectsQueryForm projectsQueryForm);


    /**
     *  POST----复杂查询
     * @param  projectsQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ProjectsVo>> search(@Valid @RequestBody ProjectsQueryForm projectsQueryForm);

}
