package com.deloitte.platform.api.fssc.carryforward.client;

import com.deloitte.platform.api.fssc.carryforward.param.DssScientificPayQueryForm;
import com.deloitte.platform.api.fssc.carryforward.vo.DssScientificPayForm;
import com.deloitte.platform.api.fssc.carryforward.vo.DssScientificPayVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-06-11
 * @Description :  DssScientificPay控制器接口
 * @Modified :
 */
public interface DssScientificPayClient {

    public static final String path="/fssc/dss-scientific-pay";


    /**
     *  POST---新增
     * @param dssScientificPayForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute DssScientificPayForm dssScientificPayForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, dssScientificPayForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody DssScientificPayForm dssScientificPayForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<DssScientificPayVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   dssScientificPayQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<DssScientificPayVo>> list(@Valid @RequestBody DssScientificPayQueryForm dssScientificPayQueryForm);


    /**
     *  POST----复杂查询
     * @param  dssScientificPayQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<DssScientificPayVo>> search(@Valid @RequestBody DssScientificPayQueryForm dssScientificPayQueryForm);
}
