package com.deloitte.platform.api.hr.retireRehiring.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.retireRehiring.param.RetireApplyQueryForm;
import com.deloitte.platform.api.hr.retireRehiring.param.RetireRehiringQueryForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RemindVo;
import com.deloitte.platform.api.hr.retireRehiring.vo.RetireApplyForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RetireApplyProcessForm;
import com.deloitte.platform.api.hr.retireRehiring.vo.RetireApplyVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : tankui
 * @Date : Create in 2019-05-13
 * @Description :  RetireApply控制器接口
 * @Modified :
 */
public interface RetireApplyClient {

    public static final String path="/hr/retire-apply";


    /**
     *  POST---新增
     * @param retireApplyForm
     * @return
     */
    @PostMapping(value = path)
    Result addOrUpdate(@Valid @ModelAttribute RetireApplyForm retireApplyForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value = "id") long id);

    /**
     *  Patch----部分更新
     * @param  id, retireApplyForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value = "id") long id, @Valid @RequestBody RetireApplyForm retireApplyForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<RetireApplyVo> get(@PathVariable(value = "id") long id);


    /**
     *  POST----列表查询
     * @param   retireApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/list")
    Result<List<RetireApplyVo>> list(@Valid @RequestBody RetireApplyQueryForm retireApplyQueryForm);


    /**
     *  POST----复杂查询
     * @param  retireApplyQueryForm
     * @return
     */
    @PostMapping(value = path+"/page")
    Result<IPage<RetireApplyVo>> search(@Valid @RequestBody RetireApplyQueryForm retireApplyQueryForm);

    @PostMapping(value = path+"/getReminder")
    Result<RemindVo> getReminder(@Valid @RequestBody RetireRehiringQueryForm queryForm);

    @PostMapping(value = path+"/retireApproveProcess")
    Result retireApproveProcess(@Valid @RequestBody @ApiParam(name = "RemindProcessForm", value = "流程提交参数", required = true) RetireApplyProcessForm retireApplyProcessForm);
}
