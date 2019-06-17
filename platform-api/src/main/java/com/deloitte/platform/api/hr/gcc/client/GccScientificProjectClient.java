package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccScientificProjectQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccScientificProjectForm;
import com.deloitte.platform.api.hr.gcc.vo.GccScientificProjectVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccScientificProject控制器接口
 * @Modified :
 */
public interface GccScientificProjectClient {

    public static final String path="/hr/gcc-scientific-project";


    /**
     *  POST---新增
     * @param gccScientificProjectForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccScientificProjectForm gccScientificProjectForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccScientificProjectForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccScientificProjectForm gccScientificProjectForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccScientificProjectVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccScientificProjectQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccScientificProjectVo>> list(@Valid @RequestBody GccScientificProjectQueryForm gccScientificProjectQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccScientificProjectQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccScientificProjectVo>> search(@Valid @RequestBody GccScientificProjectQueryForm gccScientificProjectQueryForm);

    /**
     *  POST---批量新增或更新
     * @param forms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<GccScientificProjectForm> forms);

    /**
     * 批量删除
     * @param form
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    Result deleteList(@Valid @RequestBody GccBaseBatchForm form);
}
