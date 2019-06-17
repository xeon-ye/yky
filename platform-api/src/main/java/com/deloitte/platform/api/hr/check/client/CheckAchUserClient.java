package com.deloitte.platform.api.hr.check.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckAchUserListVoQueryForm;
import com.deloitte.platform.api.hr.check.param.CheckAchUserQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchUserForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchUserListVo;
import com.deloitte.platform.api.hr.check.vo.CheckAchUserModifyForm;
import com.deloitte.platform.api.hr.check.vo.CheckAchUserVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :  CheckAchUser控制器接口
 * @Modified :
 */
public interface CheckAchUserClient {

    public static final String path="/hr/check-ach-user";


    /**
     *  POST---新增
     * @param checkAchUserForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckAchUserForm checkAchUserForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkAchUserForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckAchUserForm checkAchUserForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckAchUserVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkAchUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckAchUserVo>> list(@Valid @RequestBody CheckAchUserQueryForm checkAchUserQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkAchUserQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckAchUserVo>> search(@Valid @RequestBody CheckAchUserQueryForm checkAchUserQueryForm);

    /**
     *  POST---批量修改状态
     * @param checkAchUserModifyForm
     * @return
     */
    @PostMapping(value = path+"/batchModifyStatus")
    Result batchModifyStatus(@Valid @RequestBody CheckAchUserModifyForm checkAchUserModifyForm);

    /**
     *  Patch----部分更新
     * @param   ids
     * @return
     */
    @PostMapping(value = path+"/batchDelete")
    Result batchDelete(@Valid @RequestBody List<String> ids);

    /**
     * 获取个人业绩考核列表
     * @param checkAchUserListVoQueryForm
     * @return
     */
    @PostMapping(value = path+"/getEvaluateUserList")
    Result<List<CheckAchUserListVo>>   getEvaluateUserList(@Valid @RequestBody CheckAchUserListVoQueryForm checkAchUserListVoQueryForm);

}
