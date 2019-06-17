package com.deloitte.platform.api.contract.client;

import com.deloitte.platform.api.contract.param.SignInfoQueryForm;
import com.deloitte.platform.api.contract.vo.SignInfoForm;
import com.deloitte.platform.api.contract.vo.SignInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : zhengchun
 * @Date : Create in 2019-03-26
 * @Description :  SignInfo控制器接口
 * @Modified :
 */
public interface SignInfoClient {

    public static final String path="/contract/sign-info";


    /**
     *  POST---新增
     * @param signInfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute SignInfoForm signInfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, signInfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody SignInfoForm signInfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<SignInfoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   signInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<SignInfoVo>> list(@Valid @RequestBody SignInfoQueryForm signInfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  signInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<SignInfoVo>> search(@Valid @RequestBody SignInfoQueryForm signInfoQueryForm);
}
