package com.deloitte.platform.api.fssc.travle.client;

import com.deloitte.platform.api.fssc.travle.param.TasTravelLineQueryForm;
import com.deloitte.platform.api.fssc.travle.vo.TasTravelLineForm;
import com.deloitte.platform.api.fssc.travle.vo.TasTravelLineVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-08
 * @Description :  TasTravelLine控制器接口
 * @Modified :
 */
public interface TasTravelLineClient {

    public static final String path="/travle11/tas-travel-line";


    /**
     *  POST---新增
     * @param tasTravelLineForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute TasTravelLineForm tasTravelLineForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, tasTravelLineForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody TasTravelLineForm tasTravelLineForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<TasTravelLineVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   tasTravelLineQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<TasTravelLineVo>> list(@Valid @RequestBody TasTravelLineQueryForm tasTravelLineQueryForm);


    /**
     *  POST----复杂查询
     * @param  tasTravelLineQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<TasTravelLineVo>> search(@Valid @RequestBody TasTravelLineQueryForm tasTravelLineQueryForm);
}
