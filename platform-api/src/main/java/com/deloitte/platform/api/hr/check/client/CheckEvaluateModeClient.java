package com.deloitte.platform.api.hr.check.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.deloitte.platform.api.hr.check.param.CheckEvaluateModeQueryForm;
import com.deloitte.platform.api.hr.check.vo.CheckEvaluateModeForm;
import com.deloitte.platform.api.hr.check.vo.CheckEvaluateModeVo;
import com.deloitte.platform.common.core.entity.vo.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author : woo
 * @Date : Create in 2019-04-11
 * @Description :  CheckEvaluateMode控制器接口
 * @Modified :
 */
public interface CheckEvaluateModeClient {

    public static final String path="/hr/check-evaluate-mode";


    /**
     *  POST---新增
     * @param checkEvaluateModeForm
     * @return
     */
    @PostMapping(value = path)
    Result add(@Valid @ModelAttribute CheckEvaluateModeForm checkEvaluateModeForm);

    /**
    *  Delete---删除
    * @param  id
    * @return
    */
    @DeleteMapping(value = path+"/{id}")
    Result delete(@PathVariable(value="id") long id);

    /**
     *  Patch----部分更新
     * @param  id, checkEvaluateModeForm
     * @return
     */
    @PatchMapping(value = path+"/{id}")
    Result update(@PathVariable(value="id") long id, @Valid @RequestBody CheckEvaluateModeForm checkEvaluateModeForm);

    /**
    *  GET----根据ID获取
    * @param  id
    * @return
    */
    @GetMapping(value = path+"/{id}")
    Result<CheckEvaluateModeVo> get(@PathVariable(value="id") long id);


    /**
     *  POST----列表查询
     * @param   checkEvaluateModeQueryForm
     * @return
     */
    @PostMapping(value = path+"/list/conditions")
    Result<List<CheckEvaluateModeVo>> list(@Valid @RequestBody CheckEvaluateModeQueryForm checkEvaluateModeQueryForm);


    /**
     *  POST----复杂查询
     * @param  checkEvaluateModeQueryForm
     * @return
     */
    @PostMapping(value = path+"/page/conditions")
    Result<IPage<CheckEvaluateModeVo>> search(@Valid @RequestBody CheckEvaluateModeQueryForm checkEvaluateModeQueryForm);
}
