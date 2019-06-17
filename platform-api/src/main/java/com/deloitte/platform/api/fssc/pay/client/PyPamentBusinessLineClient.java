package com.deloitte.platform.api.fssc.pay.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.pay.param.PyPamentBusinessLineQueryForm;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentBusinessLineForm;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentBusinessLineVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-22
 * @Description :  PyPamentBusinessLine控制器接口
 * @Modified :
 */
public interface PyPamentBusinessLineClient {

    public static final String path="/pay/py-pament-business-line";


    /**
     *  POST---新增
     * @param pyPamentBusinessLineForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute PyPamentBusinessLineForm pyPamentBusinessLineForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, pyPamentBusinessLineForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody PyPamentBusinessLineForm pyPamentBusinessLineForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<PyPamentBusinessLineVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   pyPamentBusinessLineQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<PyPamentBusinessLineVo>> list(@Valid @RequestBody PyPamentBusinessLineQueryForm pyPamentBusinessLineQueryForm);


    /**
     *  POST----复杂查询
     * @param  pyPamentBusinessLineQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<PyPamentBusinessLineVo>> search(@Valid @RequestBody PyPamentBusinessLineQueryForm pyPamentBusinessLineQueryForm);
}
