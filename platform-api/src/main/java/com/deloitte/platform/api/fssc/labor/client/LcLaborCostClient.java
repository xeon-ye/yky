package com.deloitte.platform.api.fssc.labor;

import com.deloitte.platform.api.fssc.labor.param.LcLaborCostQueryForm;
import com.deloitte.platform.api.fssc.labor.vo.LcLaborCostForm;
import com.deloitte.platform.api.fssc.labor.vo.LcLaborCostVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-25
 * @Description :  LcLaborCost控制器接口
 * @Modified :
 */
public interface LcLaborCostClient {

    public static final String path="/labor/lc-labor-cost";


    /**
     *  POST---新增
     * @param lcLaborCostForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute LcLaborCostForm lcLaborCostForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, lcLaborCostForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody LcLaborCostForm lcLaborCostForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<LcLaborCostVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   lcLaborCostQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<LcLaborCostVo>> list(@Valid @RequestBody LcLaborCostQueryForm lcLaborCostQueryForm);


    /**
     *  POST----复杂查询
     * @param  lcLaborCostQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<LcLaborCostVo>> search(@Valid @RequestBody LcLaborCostQueryForm lcLaborCostQueryForm);
}
