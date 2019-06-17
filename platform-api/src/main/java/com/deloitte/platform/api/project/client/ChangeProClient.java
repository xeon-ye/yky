package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.ChangeProQueryForm;
import com.deloitte.platform.api.project.vo.ChangeProForm;
import com.deloitte.platform.api.project.vo.ChangeProVo;
import com.deloitte.platform.api.project.vo.ProjectChangeVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-20
 * @Description :  ChangePro控制器接口
 * @Modified :
 */
public interface ChangeProClient {

    public static final String path="/project/change-pro";


    /**
     *  POST---新增
     * @param changeProForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ChangeProForm changeProForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, changeProForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ChangeProForm changeProForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ChangeProVo> get(@PathVariable(value="id") long id);


    @GetMapping(value = path+"/change/{projectId}")
    Result<ProjectChangeVo> getOneChange(@PathVariable(value = "projectId") String projectId);

    /**
     *  POST----列表查询
     * @param   changeProQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ChangeProVo>> list(@Valid @RequestBody ChangeProQueryForm changeProQueryForm);


    /**
     *  POST----复杂查询
     * @param  changeProQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ChangeProVo>> search(@Valid @RequestBody ChangeProQueryForm changeProQueryForm);
}
