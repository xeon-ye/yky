package com.deloitte.platform.api.hr.employee.client;

import com.deloitte.platform.api.hr.employee.param.IncumproveApplyQueryForm;
import com.deloitte.platform.api.hr.employee.vo.IncumproveApplyForm;
import com.deloitte.platform.api.hr.employee.vo.IncumproveApplyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : ZhongJiang
 * @Date : Create in 2019-04-08
 * @Description :  IncumproveApply控制器接口
 * @Modified :
 */
public interface IncumproveApplyClient {

    public static final String path="/hr/incumprove-apply";


    /**
     *  POST---新增
     * @param incumproveApplyForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute IncumproveApplyForm incumproveApplyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, incumproveApplyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody IncumproveApplyForm incumproveApplyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<IncumproveApplyVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   incumproveApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<IncumproveApplyVo>> list(@Valid @RequestBody IncumproveApplyQueryForm incumproveApplyQueryForm);


    /**
     *  POST----复杂查询
     * @param  incumproveApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<IncumproveApplyVo>> search(@Valid @RequestBody IncumproveApplyQueryForm incumproveApplyQueryForm);
}
