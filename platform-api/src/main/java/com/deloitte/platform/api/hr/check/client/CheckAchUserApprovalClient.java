package com.deloitte.platform.api.hr.check.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckAchUserApprovalQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchUserApprovalForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchUserApprovalHistoryVo;
import com.deloitte.platform.api.hr.check.vo.CheckAchUserApprovalVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :  CheckAchUserApproval控制器接口
 * @Modified :
 */
public interface CheckAchUserApprovalClient {

    public static final String path="/hr/check-ach-user-approval";


    /**
     *  POST---新增
     * @param checkAchUserApprovalForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckAchUserApprovalForm checkAchUserApprovalForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkAchUserApprovalForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckAchUserApprovalForm checkAchUserApprovalForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckAchUserApprovalVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkAchUserApprovalQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckAchUserApprovalVo>> list(@Valid @RequestBody CheckAchUserApprovalQueryForm checkAchUserApprovalQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkAchUserApprovalQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckAchUserApprovalVo>> search(@Valid @RequestBody CheckAchUserApprovalQueryForm checkAchUserApprovalQueryForm);


    /**
     *  POST----获取业绩审批历史
     * @param  checkAchUserApprovalQueryForm
     * @return
     */
    @PostMapping(value = path+"/getApprovalHistory")
    Result<List<CheckAchUserApprovalHistoryVo>> getApprovalHistory(@Valid @RequestBody CheckAchUserApprovalQueryForm checkAchUserApprovalQueryForm);
}
