package com.deloitte.platform.api.hr.check.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckAchEvaluateUserQueryForm;
import com.deloitte.platform.api.hr.check.param.CheckResultRelationQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchEvaluateUserForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchEvaluateUserModifyForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchEvaluateUserVo;
import com.deloitte.platform.api.hr.check.vo.CheckRelationResultVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :  CheckAchEvaluateUser控制器接口
 * @Modified :
 */
public interface CheckAchEvaluateUserClient {

    public static final String path="/hr/check-ach-evaluate-user";


    /**
     *  POST---新增
     * @param checkAchEvaluateUserForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckAchEvaluateUserForm checkAchEvaluateUserForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkAchEvaluateUserForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckAchEvaluateUserForm checkAchEvaluateUserForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckAchEvaluateUserVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkAchEvaluateUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckAchEvaluateUserVo>> list(@Valid @RequestBody CheckAchEvaluateUserQueryForm checkAchEvaluateUserQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkAchEvaluateUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckAchEvaluateUserVo>> search(@Valid @RequestBody CheckAchEvaluateUserQueryForm checkAchEvaluateUserQueryForm);

    /**
     *  POST---批量修改状态
     * @param checkAchEvaluateUserModifyForm
     * @return
     */
    @PostMapping(value = path+"/batchModifyStatus")
    Result batchModifyStatus(@Valid @RequestBody CheckAchEvaluateUserModifyForm checkAchEvaluateUserModifyForm);

    /**
     *  Patch----部分更新
     * @param   ids
     * @return
     */
    @PostMapping(value = path+"/batchDelete")
    Result batchDelete(@Valid @RequestBody List<String> ids);

    /**
     * 查询结果计算中考核关系列表
     * @param queryForm
     * @return
     */
    @PostMapping(value = path+"/getResultRelation")
    Result< IPage<CheckRelationResultVo>> getResultRelation(@Valid @RequestBody  CheckResultRelationQueryForm queryForm);
}
