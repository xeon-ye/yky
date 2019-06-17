package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.OuQueryForm;
import com.deloitte.platform.api.project.vo.OuForm;
import com.deloitte.platform.api.project.vo.OuVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-04-24
 * @Description :  Ou控制器接口
 * @Modified :
 */
public interface OuClient {

    public static final String path="/project/ou";


    /**
     *  POST---新增
     * @param ouForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OuForm ouForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, ouForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody OuForm ouForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OuVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   ouQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OuVo>> list(@Valid @RequestBody OuQueryForm ouQueryForm);


    /**
     *  POST----复杂查询
     * @param  ouQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OuVo>> search(@Valid @RequestBody OuQueryForm ouQueryForm);
}
