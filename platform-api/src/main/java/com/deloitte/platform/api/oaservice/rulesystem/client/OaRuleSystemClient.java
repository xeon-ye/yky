package com.deloitte.platform.api.oaservice.rulesystem.client;

import com.deloitte.platform.api.oaservice.rulesystem.param.OaRuleSystemQueryForm;
import com.deloitte.platform.api.oaservice.rulesystem.vo.OaRuleSystemForm;
import com.deloitte.platform.api.oaservice.rulesystem.vo.OaRuleSystemVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @Author : fuqiao
 * @Date : Create in 2019-04-15
 * @Description :  OaRuleSystem控制器接口
 * @Modified :
 */
public interface OaRuleSystemClient {

    public static final String path="/oaservice/oa-rule-system";


    /**
     *  POST---新增
     * @param oaRuleSystemForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute OaRuleSystemForm oaRuleSystemForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, oaRuleSystemForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody OaRuleSystemForm oaRuleSystemForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<OaRuleSystemVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   oaRuleSystemQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<OaRuleSystemVo>> list(@Valid @RequestBody OaRuleSystemQueryForm oaRuleSystemQueryForm);


    /**
     *  POST----复杂查询
     * @param  oaRuleSystemQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<OaRuleSystemVo>> search(@Valid @RequestBody OaRuleSystemQueryForm oaRuleSystemQueryForm, String token);

    /**
     *  POST----复杂查询
     * @param  oaRuleSystemQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/permission/conditions")
    Result<IPage<OaRuleSystemVo>> searchWithPermission(@Valid @RequestBody OaRuleSystemQueryForm oaRuleSystemQueryForm, String token);

    /**
     *  GET---首页查询
     * @param num
     * @return
     */
    @GetMapping(value = path+"/list/home")
    Result<List<OaRuleSystemVo>> homeList(@RequestParam(value = "num", defaultValue = "3") Integer num, String token);
}
