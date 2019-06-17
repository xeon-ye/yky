package com.deloitte.platform.api.fssc.ppc;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.ppc.param.ProjectRecieveDetailQueryForm;
import com.deloitte.platform.api.fssc.ppc.vo.ProjectRecieveDetailForm;
import com.deloitte.platform.api.fssc.ppc.vo.ProjectRecieveDetailVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-01
 * @Description :  ProjectRecieveDetail控制器接口
 * @Modified :
 */
public interface ProjectRecieveDetailClient {

    public static final String path="/ppc/project-recieve-detail";


    /**
     *  POST---新增
     * @param projectRecieveDetailForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ProjectRecieveDetailForm projectRecieveDetailForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, projectRecieveDetailForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ProjectRecieveDetailForm projectRecieveDetailForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ProjectRecieveDetailVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   projectRecieveDetailQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ProjectRecieveDetailVo>> list(@Valid @RequestBody ProjectRecieveDetailQueryForm projectRecieveDetailQueryForm);


    /**
     *  POST----复杂查询
     * @param  projectRecieveDetailQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ProjectRecieveDetailVo>> search(@Valid @RequestBody ProjectRecieveDetailQueryForm projectRecieveDetailQueryForm);
}
