package com.deloitte.platform.api.fssc.general;

import com.deloitte.platform.api.fssc.general.param.GeExpenseBorrowPrepayQueryForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpenseBorrowPrepayForm;
import com.deloitte.platform.api.fssc.general.vo.GeExpenseBorrowPrepayVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : jaws
 * @Date : Create in 2019-03-14
 * @Description :  GeExpenseBorrowPrepay控制器接口
 * @Modified :
 */
public interface GeExpenseBorrowPrepayClient {

    public static final String path="/general/ge-expense-borrow-prepay";


    /**
     *  POST---新增
     * @param geExpenseBorrowPrepayForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute GeExpenseBorrowPrepayForm geExpenseBorrowPrepayForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, geExpenseBorrowPrepayForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody GeExpenseBorrowPrepayForm geExpenseBorrowPrepayForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<GeExpenseBorrowPrepayVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   geExpenseBorrowPrepayQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<GeExpenseBorrowPrepayVo>> list(@Valid @RequestBody GeExpenseBorrowPrepayQueryForm geExpenseBorrowPrepayQueryForm);


    /**
     *  POST----复杂查询
     * @param  geExpenseBorrowPrepayQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<GeExpenseBorrowPrepayVo>> search(@Valid @RequestBody GeExpenseBorrowPrepayQueryForm geExpenseBorrowPrepayQueryForm);
}
