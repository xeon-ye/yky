package com.deloitte.platform.api.fssc.ito.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.fssc.ito.param.DetailsOfFundsQueryForm;
import com.deloitte.platform.api.fssc.ito.vo.DetailsOfFundsForm;
import com.deloitte.platform.api.fssc.ito.vo.DetailsOfFundsVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-04-15
 * @Description :  DetailsOfFunds控制器接口
 * @Modified :
 */
public interface DetailsOfFundsClient {

    public static final String path="/ito/details-of-funds";


    /**
     *  POST---新增
     * @param detailsOfFundsForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute DetailsOfFundsForm detailsOfFundsForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, detailsOfFundsForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody DetailsOfFundsForm detailsOfFundsForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<DetailsOfFundsVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   detailsOfFundsQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<DetailsOfFundsVo>> list(@Valid @RequestBody DetailsOfFundsQueryForm detailsOfFundsQueryForm);


    /**
     *  POST----复杂查询
     * @param  detailsOfFundsQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<DetailsOfFundsVo>> search(@Valid @RequestBody DetailsOfFundsQueryForm detailsOfFundsQueryForm);
}
