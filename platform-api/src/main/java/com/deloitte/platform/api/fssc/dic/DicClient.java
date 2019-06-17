package com.deloitte.platform.api.fssc.dic;

import com.deloitte.platform.api.fssc.dic.param.DicQueryForm;
import com.deloitte.platform.api.fssc.dic.vo.DicForm;
import com.deloitte.platform.api.fssc.dic.vo.DicVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description :  Dic控制器接口
 * @Modified :
 */
public interface DicClient {

    public static final String path="/fssc/dic";


    /**
     *  POST---新增
     * @param dicForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute DicForm dicForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, dicForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody DicForm dicForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<DicVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   dicForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<DicVo>> list(@Valid @RequestBody DicQueryForm dicQueryForm);


    /**
     *  POST----复杂查询
     * @param  dicQueryForm
     * @return
     */
    @GetMapping(value = path+"/page/conditions")
    Result<IPage<DicVo>> search(@Valid @RequestBody DicQueryForm dicQueryForm);
}
