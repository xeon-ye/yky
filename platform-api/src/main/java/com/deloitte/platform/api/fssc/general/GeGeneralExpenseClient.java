package com.deloitte.platform.api.fssc.general;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.general.param.GeGeneralExpenseQueryForm;
import com.deloitte.platform.api.fssc.general.vo.GeGeneralExpenseForm;
import com.deloitte.platform.api.fssc.general.vo.GeGeneralExpenseVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description :  GeGeneralExpense控制器接口
 * @Modified :
 */
public interface GeGeneralExpenseClient {

    public static final String path="/general/ge-general-expense";


    /**
     *  POST---新增
     * @param geGeneralExpenseForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GeGeneralExpenseForm geGeneralExpenseForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, geGeneralExpenseForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody GeGeneralExpenseForm geGeneralExpenseForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GeGeneralExpenseVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   geGeneralExpenseQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GeGeneralExpenseVo>> list(@Valid @RequestBody GeGeneralExpenseQueryForm geGeneralExpenseQueryForm);


    /**
     *  POST----复杂查询
     * @param  geGeneralExpenseQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GeGeneralExpenseVo>> search(@Valid @RequestBody GeGeneralExpenseQueryForm geGeneralExpenseQueryForm);
}
