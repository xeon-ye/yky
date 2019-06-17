package com.deloitte.platform.api.hr.employee.client;

import com.deloitte.platform.api.hr.employee.param.CorptoprivateApplyQueryForm;
import com.deloitte.platform.api.hr.employee.vo.CorptoprivateApplyForm;
import com.deloitte.platform.api.hr.employee.vo.CorptoprivateApplyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-08
 * @Description :  CorptoprivateApply控制器接口
 * @Modified :
 */
public interface CorptoprivateApplyClient {

    public static final String path="/hr/corptoprivate-apply";


    /**
     *  POST---新增
     * @param corptoprivateApplyForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CorptoprivateApplyForm corptoprivateApplyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, corptoprivateApplyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody CorptoprivateApplyForm corptoprivateApplyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CorptoprivateApplyVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   corptoprivateApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CorptoprivateApplyVo>> list(@Valid @RequestBody CorptoprivateApplyQueryForm corptoprivateApplyQueryForm);


    /**
     *  POST----复杂查询
     * @param  corptoprivateApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CorptoprivateApplyVo>> search(@Valid @RequestBody CorptoprivateApplyQueryForm corptoprivateApplyQueryForm);
}
