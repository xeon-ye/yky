package com.deloitte.platform.api.fssc.general;

import com.deloitte.platform.api.fssc.general.param.GeGeneralExpenseLineQueryForm;
import com.deloitte.platform.api.fssc.general.vo.GeGeneralExpenseLineForm;
import com.deloitte.platform.api.fssc.general.vo.GeGeneralExpenseLineVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description :  GeGeneralExpenseLine控制器接口
 * @Modified :
 */
public interface GeGeneralExpenseLineClient {

    public static final String path="/general/ge-general-expense-line";


    /**
     *  POST---新增
     * @param geGeneralExpenseLineForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GeGeneralExpenseLineForm geGeneralExpenseLineForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, geGeneralExpenseLineForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody GeGeneralExpenseLineForm geGeneralExpenseLineForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GeGeneralExpenseLineVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   geGeneralExpenseLineQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GeGeneralExpenseLineVo>> list(@Valid @RequestBody GeGeneralExpenseLineQueryForm geGeneralExpenseLineQueryForm);


    /**
     *  POST----复杂查询
     * @param  geGeneralExpenseLineQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GeGeneralExpenseLineVo>> search(@Valid @RequestBody GeGeneralExpenseLineQueryForm geGeneralExpenseLineQueryForm);
}
