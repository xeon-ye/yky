package com.deloitte.platform.api.isump;

import com.deloitte.platform.api.isump.param.DeputyAccountQueryForm;
import com.deloitte.platform.api.isump.vo.DeputyAccountForm;
import com.deloitte.platform.api.isump.vo.DeputyAccountVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jianglong
 * @Date : Create in 2019-02-28
 * @Description :  DeputyAccount控制器接口
 * @Modified :
 */
public interface DeputyAccountClient {

    public static final String path="/isump/deputy-account";


    /**
     *  POST---新增
     * @param deputyAccountForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute DeputyAccountForm deputyAccountForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, deputyAccountForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody DeputyAccountForm deputyAccountForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<DeputyAccountVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param  deputyAccountQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<DeputyAccountVo>> list(@Valid @RequestBody DeputyAccountQueryForm deputyAccountQueryForm);


    /**
     *  POST----复杂查询
     * @param  deputyAccountQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<DeputyAccountVo>> search(@Valid @RequestBody DeputyAccountQueryForm deputyAccountQueryForm);
}
