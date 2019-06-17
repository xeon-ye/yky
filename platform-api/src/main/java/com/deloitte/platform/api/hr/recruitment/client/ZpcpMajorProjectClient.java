package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpMajorProjectQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpMajorProjectForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpMajorProjectVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :  ZpcpMajorProject控制器接口
 * @Modified :
 */
public interface ZpcpMajorProjectClient {

    public static final String path="/hr/zpcp-major-project";


    /**
     *  POST---新增
     * @param zpcpMajorProjectForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ZpcpMajorProjectForm zpcpMajorProjectForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, zpcpMajorProjectForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ZpcpMajorProjectForm zpcpMajorProjectForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ZpcpMajorProjectVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   zpcpMajorProjectQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ZpcpMajorProjectVo>> list(@Valid @RequestBody ZpcpMajorProjectQueryForm zpcpMajorProjectQueryForm);


    /**
     *  POST----复杂查询
     * @param  zpcpMajorProjectQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ZpcpMajorProjectVo>> search(@Valid @RequestBody ZpcpMajorProjectQueryForm zpcpMajorProjectQueryForm);

    /**
     *  POST---批量新增或更新
     * @param majorProjectForms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<ZpcpMajorProjectForm> majorProjectForms);


    /**
     * 批量删除
     * @param deleteForm
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    public Result deleteList(@Valid @RequestBody DeleteForm deleteForm);
}
