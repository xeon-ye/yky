package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.ImprovementsQueryForm;
import com.deloitte.platform.api.project.vo.ImprovementsForm;
import com.deloitte.platform.api.project.vo.ImprovementsVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  Improvements控制器接口
 * @Modified :
 */
public interface ImprovementsClient {

    public static final String path="/project/improvements";


    /**
     *  POST---新增
     * @param improvementsForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ImprovementsForm improvementsForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, improvementsForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ImprovementsForm improvementsForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ImprovementsVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   improvementsQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ImprovementsVo>> list(@Valid @RequestBody ImprovementsQueryForm improvementsQueryForm);


    /**
     *  POST----复杂查询
     * @param  improvementsQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ImprovementsVo>> search(@Valid @RequestBody ImprovementsQueryForm improvementsQueryForm);
}
