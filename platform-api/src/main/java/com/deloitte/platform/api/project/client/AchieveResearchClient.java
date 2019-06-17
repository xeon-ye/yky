package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.AchieveResearchQueryForm;
import com.deloitte.platform.api.project.vo.AchieveResearchForm;
import com.deloitte.platform.api.project.vo.AchieveResearchVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-31
 * @Description :  AchieveResearch控制器接口
 * @Modified :
 */
public interface AchieveResearchClient {

    public static final String path="/project/achieve-research";


    /**
     *  POST---新增
     * @param achieveResearchForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute AchieveResearchForm achieveResearchForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, achieveResearchForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody AchieveResearchForm achieveResearchForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<AchieveResearchVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   achieveResearchQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<AchieveResearchVo>> list(@Valid @RequestBody AchieveResearchQueryForm achieveResearchQueryForm);


    /**
     *  POST----复杂查询
     * @param  achieveResearchQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<AchieveResearchVo>> search(@Valid @RequestBody AchieveResearchQueryForm achieveResearchQueryForm);
}
