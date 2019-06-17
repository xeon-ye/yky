package com.deloitte.platform.api.fssc.travle;

import com.deloitte.platform.api.fssc.travle.param.TasTravelApplyInfoQueryForm;
import com.deloitte.platform.api.fssc.travle.vo.TasTravelApplyInfoForm;
import com.deloitte.platform.api.fssc.travle.vo.TasTravelApplyInfoVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : hjy
 * @Date : Create in 2019-04-01
 * @Description :  TasTravleApplyInfo控制器接口
 * @Modified :
 */
public interface TasTravleApplyInfoClient {

    public static final String path="/travle/tas-travle-apply-info";


    /**
     *  POST---新增
     * @param tasTravelApplyInfoForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute TasTravelApplyInfoForm tasTravelApplyInfoForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, tasTravelApplyInfoForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody TasTravelApplyInfoForm tasTravelApplyInfoForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<TasTravelApplyInfoVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   tasTravelApplyInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<TasTravelApplyInfoVo>> list(@Valid @RequestBody TasTravelApplyInfoQueryForm tasTravelApplyInfoQueryForm);


    /**
     *  POST----复杂查询
     * @param  tasTravelApplyInfoQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<TasTravelApplyInfoVo>> search(@Valid @RequestBody TasTravelApplyInfoQueryForm tasTravelApplyInfoQueryForm);
}
