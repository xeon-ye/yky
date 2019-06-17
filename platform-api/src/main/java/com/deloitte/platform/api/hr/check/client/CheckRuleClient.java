package com.deloitte.platform.api.hr.check.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckRuleQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckPracticalRuleVo;
import com.deloitte.platform.api.hr.check.vo.CheckRuleForm;
import com.deloitte.platform.api.hr.check.vo.CheckRuleVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-01
 * @Description :  CheckRule控制器接口
 * @Modified :
 */
public interface CheckRuleClient {

    public static final String path="/hr/check-rule";


    /**
     *  POST---新增
     * @param checkRuleForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckRuleForm checkRuleForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkRuleForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckRuleForm checkRuleForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckRuleVo> get(@PathVariable(value="id") long id);

    /**
     *  GET----根据ID获取
     * @param  id
     * @return
     */
    @GetMapping(value = path+"/getRuleAndRuleContent/{id}")
    Result<CheckPracticalRuleVo> getRuleAndRuleContent(@PathVariable(value="id") long id);

    /**
     *  POST----列表查询
     * @param   checkRuleQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckRuleVo>> list(@Valid @RequestBody CheckRuleQueryForm checkRuleQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkRuleQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckRuleVo>> search(@Valid @RequestBody CheckRuleQueryForm checkRuleQueryForm);

    /**
     *  Patch----部分更新
     * @param   ids
     * @return
     */
    @PostMapping(value = path+"/batchDelete")
    Result batchDelete(@Valid @RequestBody List<String> ids);
}
