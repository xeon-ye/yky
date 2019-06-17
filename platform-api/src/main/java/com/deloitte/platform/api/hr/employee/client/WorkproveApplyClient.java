package com.deloitte.platform.api.hr.employee.client;

import com.deloitte.platform.api.hr.employee.param.WorkproveApplyQueryForm;
import com.deloitte.platform.api.hr.employee.vo.WorkproveApplyForm;
import com.deloitte.platform.api.hr.employee.vo.WorkproveApplyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-08
 * @Description :  WorkproveApply控制器接口
 * @Modified :
 */
public interface WorkproveApplyClient {

    public static final String path="/hr/workprove-apply";


    /**
     *  POST---新增
     * @param workproveApplyForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute WorkproveApplyForm workproveApplyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, workproveApplyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody WorkproveApplyForm workproveApplyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<WorkproveApplyVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   workproveApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<WorkproveApplyVo>> list(@Valid @RequestBody WorkproveApplyQueryForm workproveApplyQueryForm);


    /**
     *  POST----复杂查询
     * @param  workproveApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<WorkproveApplyVo>> search(@Valid @RequestBody WorkproveApplyQueryForm workproveApplyQueryForm);
}
