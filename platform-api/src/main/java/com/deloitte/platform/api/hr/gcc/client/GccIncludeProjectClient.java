package com.deloitte.platform.api.hr.gcc.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.gcc.param.GccBaseBatchForm;
import com.deloitte.platform.api.hr.gcc.param.GccIncludeProjectQueryForm;
import com.deloitte.platform.api.hr.gcc.vo.GccIncludeProjectForm;
import com.deloitte.platform.api.hr.gcc.vo.GccIncludeProjectVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-04-01
 * @Description :  GccIncludeProject控制器接口
 * @Modified :
 */
public interface GccIncludeProjectClient {

    public static final String path="/hr/gcc-include-project";


    /**
     *  POST---新增
     * @param gccIncludeProjectForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GccIncludeProjectForm gccIncludeProjectForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, gccIncludeProjectForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody GccIncludeProjectForm gccIncludeProjectForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GccIncludeProjectVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   gccIncludeProjectQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GccIncludeProjectVo>> list(@Valid @RequestBody GccIncludeProjectQueryForm gccIncludeProjectQueryForm);


    /**
     *  POST----复杂查询
     * @param  gccIncludeProjectQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GccIncludeProjectVo>> search(@Valid @RequestBody GccIncludeProjectQueryForm gccIncludeProjectQueryForm);



    /**
     *  POST----通过用户ID获取入选项目
     * @param   userId
     * @return
     */
    @GetMapping(value = path+"/list/{userId}")
    Result<List<GccIncludeProjectVo>> getListByUserId(@PathVariable("userId") Long userId);



    /**
     *  Delete---批量删除
     * @param  form
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    Result deleteList(@Valid @RequestBody GccBaseBatchForm form);


    /**
     *  POST---批量新增或更新
     * @param talentProjectForms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<GccIncludeProjectForm> talentProjectForms);

}
