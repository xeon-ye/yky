package com.deloitte.platform.api.fssc.engine.automatic;

import com.deloitte.platform.api.fssc.engine.automatic.param.AvChartOfAccountQueryForm;
import com.deloitte.platform.api.fssc.engine.automatic.vo.AvChartOfAccountForm;
import com.deloitte.platform.api.fssc.engine.automatic.vo.AvChartOfAccountVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : chenx
 * @Date : Create in 2019-03-23
 * @Description :  AvChartOfAccount控制器接口
 * @Modified :
 */
public interface AvChartOfAccountClient {

    public static final String path="/fssc/av-chart-of-account";


    /**
     *  POST---新增
     * @param avChartOfAccountForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute AvChartOfAccountForm avChartOfAccountForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, avChartOfAccountForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody AvChartOfAccountForm avChartOfAccountForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<AvChartOfAccountVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   avChartOfAccountQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<AvChartOfAccountVo>> list(@Valid @RequestBody AvChartOfAccountQueryForm avChartOfAccountQueryForm);


    /**
     *  POST----复杂查询
     * @param  avChartOfAccountQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<AvChartOfAccountVo>> search(@Valid @RequestBody AvChartOfAccountQueryForm avChartOfAccountQueryForm);
}
