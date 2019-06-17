package com.deloitte.platform.api.fssc.pay.client;

import com.deloitte.platform.api.fssc.pay.param.PyPamentPublicLineQueryForm;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentPublicLineForm;
import com.deloitte.platform.api.fssc.pay.vo.PyPamentPublicLineVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-22
 * @Description :  PyPamentPublicLine控制器接口
 * @Modified :
 */
public interface PyPamentPublicLineClient {

    public static final String path="/pay/py-pament-public-line";


    /**
     *  POST---新增
     * @param pyPamentPublicLineForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute PyPamentPublicLineForm pyPamentPublicLineForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, pyPamentPublicLineForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody PyPamentPublicLineForm pyPamentPublicLineForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<PyPamentPublicLineVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   pyPamentPublicLineQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<PyPamentPublicLineVo>> list(@Valid @RequestBody PyPamentPublicLineQueryForm pyPamentPublicLineQueryForm);


    /**
     *  POST----复杂查询
     * @param  pyPamentPublicLineQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<PyPamentPublicLineVo>> search(@Valid @RequestBody PyPamentPublicLineQueryForm pyPamentPublicLineQueryForm);
}
