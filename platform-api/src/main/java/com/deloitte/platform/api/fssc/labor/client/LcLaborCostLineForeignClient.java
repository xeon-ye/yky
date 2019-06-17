package com.deloitte.platform.api.fssc.labor;

import com.deloitte.platform.api.fssc.labor.param.LcLaborCostLineForeignQueryForm;
import com.deloitte.platform.api.fssc.labor.vo.LcLaborCostLineForeignForm;
import com.deloitte.platform.api.fssc.labor.vo.LcLaborCostLineForeignVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-25
 * @Description :  LcLaborCostLineForeign控制器接口
 * @Modified :
 */
public interface LcLaborCostLineForeignClient {

    public static final String path="/labor/lc-labor-cost-line-foreign";


    /**
     *  POST---新增
     * @param lcLaborCostLineForeignForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute LcLaborCostLineForeignForm lcLaborCostLineForeignForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, lcLaborCostLineForeignForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody LcLaborCostLineForeignForm lcLaborCostLineForeignForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<LcLaborCostLineForeignVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   lcLaborCostLineForeignQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<LcLaborCostLineForeignVo>> list(@Valid @RequestBody LcLaborCostLineForeignQueryForm lcLaborCostLineForeignQueryForm);


    /**
     *  POST----复杂查询
     * @param  lcLaborCostLineForeignQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<LcLaborCostLineForeignVo>> search(@Valid @RequestBody LcLaborCostLineForeignQueryForm lcLaborCostLineForeignQueryForm);
}
