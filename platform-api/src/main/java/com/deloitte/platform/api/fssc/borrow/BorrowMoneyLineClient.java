package com.deloitte.platform.api.fssc.borrow;

import com.deloitte.platform.api.fssc.borrow.param.BorrowMoneyLineQueryForm;
import com.deloitte.platform.api.fssc.borrow.vo.BorrowMoneyLineForm;
import com.deloitte.platform.api.fssc.borrow.vo.BorrowMoneyLineVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-04
 * @Description :  BorrowMoneyLine控制器接口
 * @Modified :
 */
public interface BorrowMoneyLineClient {

    public static final String path="/borrow/borrow-money-line";


    /**
     *  POST---新增
     * @param borrowMoneyLineForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute BorrowMoneyLineForm borrowMoneyLineForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, borrowMoneyLineForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody BorrowMoneyLineForm borrowMoneyLineForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<BorrowMoneyLineVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   borrowMoneyLineForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<BorrowMoneyLineVo>> list(@Valid @RequestBody BorrowMoneyLineQueryForm borrowMoneyLineQueryForm);


    /**
     *  POST----复杂查询
     * @param  borrowMoneyLineQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<BorrowMoneyLineVo>> search(@Valid @RequestBody BorrowMoneyLineQueryForm borrowMoneyLineQueryForm);
}
