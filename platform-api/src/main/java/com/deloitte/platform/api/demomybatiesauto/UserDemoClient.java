package com.deloitte.platform.api.demomybatiesauto;

import com.deloitte.platform.api.demomybatiesauto.param.UserDemoQueryForm;
import com.deloitte.platform.api.demomybatiesauto.vo.UserDemoForm;
import com.deloitte.platform.api.demomybatiesauto.vo.UserDemoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import com.deloitte.platform.common.core.util.GdcPage;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jack
 * @Date : Create in 2019-02-20
 * @Description :  UserDemo控制器接口
 * @Modified :
 */
public interface UserDemoClient {

    public static final String path="/demomybatiesauto/user-demo";


    /**
     *  POST---新增
     * @param userDemoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute UserDemoForm userDemoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, userDemoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody UserDemoForm userDemoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<UserDemoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   userDemoForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<UserDemoVo>> list(@Valid @RequestBody UserDemoQueryForm userDemoQueryForm);


    /**
     *  POST----复杂查询
     * @param  userDemoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<GdcPage<UserDemoVo>> search(@Valid @RequestBody UserDemoQueryForm userDemoQueryForm);
}
