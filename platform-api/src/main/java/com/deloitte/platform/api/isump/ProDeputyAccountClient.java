package com.deloitte.platform.api.isump;

import com.deloitte.platform.api.isump.param.ProDeputyAccountQueryForm;
import com.deloitte.platform.api.isump.vo.ProDeputyAccountForm;
import com.deloitte.platform.api.isump.vo.ProDeputyAccountVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-03-22
 * @Description :  ProDeputyAccount控制器接口
 * @Modified :
 */
public interface ProDeputyAccountClient {

    public static final String path="/isump/pro-deputy-account";


    /**
     *  POST---新增
     * @param proDeputyAccountForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute ProDeputyAccountForm proDeputyAccountForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, proDeputyAccountForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody ProDeputyAccountForm proDeputyAccountForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<ProDeputyAccountVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   proDeputyAccountQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<ProDeputyAccountVo>> list(@Valid @RequestBody ProDeputyAccountQueryForm proDeputyAccountQueryForm);


    /**
     *  POST----复杂查询
     * @param  proDeputyAccountQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<ProDeputyAccountVo>> search(@Valid @RequestBody ProDeputyAccountQueryForm proDeputyAccountQueryForm);
}
