package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.ExpertQueryForm;
import com.deloitte.platform.api.project.vo.ExpertForm;
import com.deloitte.platform.api.project.vo.ExpertVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-16
 * @Description :  Expert控制器接口
 * @Modified :
 */
public interface ExpertClient {

    public static final String path="/project/expert";


    /**
     *  POST---新增
     * @param expertForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ExpertForm expertForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, expertForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ExpertForm expertForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ExpertVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   expertQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ExpertVo>> list(@Valid @RequestBody ExpertQueryForm expertQueryForm);


    /**
     *  POST----复杂查询
     * @param  expertQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ExpertVo>> search(@Valid @RequestBody ExpertQueryForm expertQueryForm);
}
