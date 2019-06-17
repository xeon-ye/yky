package com.deloitte.platform.api.hr.check.client;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckRuleContentQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckRuleContentForm;
import com.deloitte.platform.api.hr.check.vo.CheckRuleContentVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-13
 * @Description :  CheckRuleContent控制器接口
 * @Modified :
 */
public interface CheckRuleContentClient {

    public static final String path="/hr/check-rule-content";


    /**
     *  POST---新增
     * @param checkRuleContentForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckRuleContentForm checkRuleContentForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkRuleContentForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckRuleContentForm checkRuleContentForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckRuleContentVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkRuleContentQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckRuleContentVo>> list(@Valid @RequestBody CheckRuleContentQueryForm checkRuleContentQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkRuleContentQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckRuleContentVo>> search(@Valid @RequestBody CheckRuleContentQueryForm checkRuleContentQueryForm);
}
