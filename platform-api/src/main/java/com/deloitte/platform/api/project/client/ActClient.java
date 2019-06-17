package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.ActQueryForm;
import com.deloitte.platform.api.project.vo.ActForm;
import com.deloitte.platform.api.project.vo.ActVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-14
 * @Description :  Act控制器接口
 * @Modified :
 */
public interface ActClient {

    public static final String path="/project/act";


    /**
     *  POST---新增
     * @param actForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ActForm actForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, actForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ActForm actForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ActVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   actQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ActVo>> list(@Valid @RequestBody ActQueryForm actQueryForm);


    /**
     *  POST----复杂查询
     * @param  actQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ActVo>> search(@Valid @RequestBody ActQueryForm actQueryForm);
}
