package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.PersonQueryForm;
import com.deloitte.platform.api.project.vo.PersonForm;
import com.deloitte.platform.api.project.vo.PersonVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-15
 * @Description :  Person控制器接口
 * @Modified :
 */
public interface PersonClient {

    public static final String path="/project/person";


    /**
     *  POST---新增
     * @param personForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute PersonForm personForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, personForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody PersonForm personForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<PersonVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   personQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<PersonVo>> list(@Valid @RequestBody PersonQueryForm personQueryForm);


    /**
     *  POST----复杂查询
     * @param  personQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<PersonVo>> search(@Valid @RequestBody PersonQueryForm personQueryForm);
}
