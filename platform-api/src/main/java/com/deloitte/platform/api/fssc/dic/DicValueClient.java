package com.deloitte.platform.api.fssc.dic;

import com.deloitte.platform.api.fssc.dic.param.DicValueQueryForm;
import com.deloitte.platform.api.fssc.dic.vo.DicValueForm;
import com.deloitte.platform.api.fssc.dic.vo.DicValueVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-02-20
 * @Description :  DicValue控制器接口
 * @Modified :
 */
public interface DicValueClient {

    public static final String path="/fssc.dic/dic-value";


    /**
     *  POST---新增
     * @param dicValueForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute DicValueForm dicValueForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, dicValueForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody DicValueForm dicValueForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<DicValueVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   dicValueForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<DicValueVo>> list(@Valid @RequestBody DicValueQueryForm dicValueQueryForm);


    /**
     *  POST----复杂查询
     * @param  dicValueQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<DicValueVo>> search(@Valid @RequestBody DicValueQueryForm dicValueQueryForm);
}
