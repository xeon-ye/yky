package com.deloitte.platform.api.fssc.borrow;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.borrow.param.BmBorrowBankQueryForm;
import com.deloitte.platform.api.fssc.borrow.vo.BmBorrowBankForm;
import com.deloitte.platform.api.fssc.borrow.vo.BmBorrowBankVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-06
 * @Description :  BmBorrowBank控制器接口
 * @Modified :
 */
public interface BmBorrowBankClient {

    public static final String path="/dsdsd/bm-borrow-bank";


    /**
     *  POST---新增
     * @param bmBorrowBankForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute BmBorrowBankForm bmBorrowBankForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, bmBorrowBankForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody BmBorrowBankForm bmBorrowBankForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<BmBorrowBankVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   bmBorrowBankForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<BmBorrowBankVo>> list(@Valid @RequestBody BmBorrowBankQueryForm bmBorrowBankQueryForm);


    /**
     *  POST----复杂查询
     * @param  bmBorrowBankQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<BmBorrowBankVo>> search(@Valid @RequestBody BmBorrowBankQueryForm bmBorrowBankQueryForm);
}
