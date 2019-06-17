package com.deloitte.platform.api.fssc.labor;

import com.deloitte.platform.api.fssc.labor.param.LcLaborCostLineChinaQueryForm;
import com.deloitte.platform.api.fssc.labor.vo.LcLaborCostLineChinaForm;
import com.deloitte.platform.api.fssc.labor.vo.LcLaborCostLineChinaVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : qiliao
 * @Date : Create in 2019-03-25
 * @Description :  LcLaborCostLineChina控制器接口
 * @Modified :
 */
public interface LcLaborCostLineChinaClient {

    public static final String path="/labor/lc-labor-cost-line-china";


    /**
     *  POST---新增
     * @param lcLaborCostLineChinaForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute LcLaborCostLineChinaForm lcLaborCostLineChinaForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, lcLaborCostLineChinaForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody LcLaborCostLineChinaForm lcLaborCostLineChinaForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<LcLaborCostLineChinaVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   lcLaborCostLineChinaQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<LcLaborCostLineChinaVo>> list(@Valid @RequestBody LcLaborCostLineChinaQueryForm lcLaborCostLineChinaQueryForm);


    /**
     *  POST----复杂查询
     * @param  lcLaborCostLineChinaQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<LcLaborCostLineChinaVo>> search(@Valid @RequestBody LcLaborCostLineChinaQueryForm lcLaborCostLineChinaQueryForm);
}
