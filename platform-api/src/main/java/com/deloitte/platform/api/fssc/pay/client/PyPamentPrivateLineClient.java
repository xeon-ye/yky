package com.deloitte.platform.api.fssc.pay.client;

import com.deloitte.platform.api.fssc.pay.param.PyPamentPrivateLineQueryForm;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentPrivateLineForm;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentPrivateLineVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-22
 * @Description :  PyPamentPrivateLine控制器接口
 * @Modified :
 */
public interface PyPamentPrivateLineClient {

    public static final String path="/pay/py-pament-private-line";


    /**
     *  POST---新增
     * @param pyPamentPrivateLineForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute PyPamentPrivateLineForm pyPamentPrivateLineForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, pyPamentPrivateLineForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody PyPamentPrivateLineForm pyPamentPrivateLineForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<PyPamentPrivateLineVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   pyPamentPrivateLineQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<PyPamentPrivateLineVo>> list(@Valid @RequestBody PyPamentPrivateLineQueryForm pyPamentPrivateLineQueryForm);


    /**
     *  POST----复杂查询
     * @param  pyPamentPrivateLineQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<PyPamentPrivateLineVo>> search(@Valid @RequestBody PyPamentPrivateLineQueryForm pyPamentPrivateLineQueryForm);
}
