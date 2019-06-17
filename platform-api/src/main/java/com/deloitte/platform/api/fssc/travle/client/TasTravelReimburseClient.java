package com.deloitte.platform.api.fssc.travle.client;

import com.deloitte.platform.api.fssc.travle.param.TasTravelReimburseQueryForm;
import com.deloitte.platform.api.fssc.travle.vo.TasTravelReimburseForm;
import com.deloitte.platform.api.fssc.travle.vo.TasTravelReimburseVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-08
 * @Description :  TasTravelReimburse控制器接口
 * @Modified :
 */
public interface TasTravelReimburseClient {

    public static final String path="/travle11/tas-travel-reimburse";


    /**
     *  POST---新增
     * @param tasTravelReimburseForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute TasTravelReimburseForm tasTravelReimburseForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, tasTravelReimburseForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody TasTravelReimburseForm tasTravelReimburseForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<TasTravelReimburseVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   tasTravelReimburseQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<TasTravelReimburseVo>> list(@Valid @RequestBody TasTravelReimburseQueryForm tasTravelReimburseQueryForm);


    /**
     *  POST----复杂查询
     * @param  tasTravelReimburseQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<TasTravelReimburseVo>> search(@Valid @RequestBody TasTravelReimburseQueryForm tasTravelReimburseQueryForm);
}
