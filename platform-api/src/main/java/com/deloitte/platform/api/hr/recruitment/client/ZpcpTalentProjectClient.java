package com.deloitte.platform.api.hr.recruitment.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.recruitment.param.ZpcpTalentProjectQueryForm;
import com.deloitte.platform.api.hr.recruitment.vo.DeleteForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpTalentProjectForm;
import com.deloitte.platform.api.hr.recruitment.vo.ZpcpTalentProjectVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-04-23
 * @Description :  ZpcpTalentProject控制器接口
 * @Modified :
 */
public interface ZpcpTalentProjectClient {

    public static final String path="/hr/zpcp-talent-project";


    /**
     *  POST---新增
     * @param zpcpTalentProjectForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ZpcpTalentProjectForm zpcpTalentProjectForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, zpcpTalentProjectForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ZpcpTalentProjectForm zpcpTalentProjectForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ZpcpTalentProjectVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   zpcpTalentProjectQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ZpcpTalentProjectVo>> list(@Valid @RequestBody ZpcpTalentProjectQueryForm zpcpTalentProjectQueryForm);


    /**
     *  POST----复杂查询
     * @param  zpcpTalentProjectQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ZpcpTalentProjectVo>> search(@Valid @RequestBody ZpcpTalentProjectQueryForm zpcpTalentProjectQueryForm);

    /**
     *  POST---批量新增或更新
     * @param talentProjectForms
     * @return
     */
    @PostMapping(value = path+"/addOrUpdateList")
    Result addOrUpdateList(@Valid @RequestBody List<ZpcpTalentProjectForm> talentProjectForms);


    /**
     * 批量删除
     * @param deleteForm
     * @return
     */
    @PostMapping(value = path+"/deleteList")
    public Result deleteList(@Valid @RequestBody DeleteForm deleteForm);
}
