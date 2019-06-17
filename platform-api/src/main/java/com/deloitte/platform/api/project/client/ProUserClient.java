package com.deloitte.platform.api.project.client;

import com.deloitte.platform.api.project.param.ProUserQueryForm;
import com.deloitte.platform.api.project.vo.ProUserForm;
import com.deloitte.platform.api.project.vo.ProUserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-05-24
 * @Description :  ProUser控制器接口
 * @Modified :
 */
public interface ProUserClient {

    public static final String path="/project/pro-user";


    /**
     *  POST---新增
     * @param proUserForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ProUserForm proUserForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, proUserForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody ProUserForm proUserForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ProUserVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   proUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ProUserVo>> list(@Valid @RequestBody ProUserQueryForm proUserQueryForm);


    /**
     *  POST----复杂查询
     * @param  proUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ProUserVo>> search(@Valid @RequestBody ProUserQueryForm proUserQueryForm);
}
