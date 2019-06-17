package com.deloitte.platform.api.hr.retireRehiring.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.employee.vo.EmployeeBaseVo;
import com.deloitte.platform.api.hr.retireRehiring.param.DelayApplyQueryForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.DelayApplyForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.DelayApplyVo;
import com.deloitte.platform.api.hr.retireRehiring.vo.RemindProcessForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RetireApplyProcessForm;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description :  RetireDelayApply控制器接口
 * @Modified :
 */
public interface DelayApplyClient {

    public static final String path="/hr/retire-delay-apply";


    /**
     *  POST---新增
     * @param retireDelayApplyForm
     * @return
     */
    @PostMapping(value = path)
    Result addOrUpdate(@Valid @ModelAttribute DelayApplyForm retireDelayApplyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, retireDelayApplyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody DelayApplyForm retireDelayApplyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<DelayApplyVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   retireDelayApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list")
    Result<List<DelayApplyVo>> list(@Valid @RequestBody DelayApplyQueryForm retireDelayApplyQueryForm);


    /**
     *  POST----复杂查询
     * @param  retireDelayApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page")
    Result<IPage<DelayApplyVo>> search(@Valid @RequestBody DelayApplyQueryForm retireDelayApplyQueryForm);

    @GetMapping(value = path+"/getLoginUser")
    @ApiOperation(value = "获取当前登录用户", notes = "获取当前登录用户")
    Result<EmployeeBaseVo> getLoginUser();

    @PostMapping(value = path + "/approveProcess")
    Result approveProcess(@Valid @RequestBody @ApiParam(name = "RemindProcessForm", value = "流程提交参数", required = true) RetireApplyProcessForm retireApplyProcessForm);
}
