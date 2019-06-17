package com.deloitte.platform.api.fssc.travle.client;

import com.deloitte.platform.api.fssc.travle.param.TasTravelStandardsQueryForm;
import com.deloitte.platform.api.fssc.travle.vo.TasTravelStandardsForm;
import com.deloitte.platform.api.fssc.travle.vo.TasTravelStandardsVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-17
 * @Description :  TasTravelStandards控制器接口
 * @Modified :
 */
public interface TasTravelStandardsClient {

    public static final String path="/travle11/tas-travel-standards";


    /**
     *  POST---新增
     * @param tasTravelStandardsForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute TasTravelStandardsForm tasTravelStandardsForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, tasTravelStandardsForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody TasTravelStandardsForm tasTravelStandardsForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<TasTravelStandardsVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   tasTravelStandardsQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<TasTravelStandardsVo>> list(@Valid @RequestBody TasTravelStandardsQueryForm tasTravelStandardsQueryForm);


    /**
     *  POST----复杂查询
     * @param  tasTravelStandardsQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<TasTravelStandardsVo>> search(@Valid @RequestBody TasTravelStandardsQueryForm tasTravelStandardsQueryForm);
}
